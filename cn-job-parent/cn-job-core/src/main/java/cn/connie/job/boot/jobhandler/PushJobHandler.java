package cn.connie.job.boot.jobhandler;

import cn.connie.common.model.PushMessage;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSON;
import com.sgcai.zookeeper.lib.DistributedLock;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@JobHandler(value = "pushJobHandler")
@Component
public class PushJobHandler extends IJobHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushJobHandler.class);
    private static final String IOS_SOUND = "notify";

    @Value("${push.store.key}")
    private String PUSH_STORE_KEY;

    @Autowired
    private JPushClient jPushClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private DistributedLock distributedLock;

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        String json = redisTemplate.boundListOps(PUSH_STORE_KEY).leftPop();
        if (StringUtils.isBlank(json)) {
            return ReturnT.SUCCESS;
        } else {
            boolean isLock = false;
            try {
                isLock = distributedLock.getLock(this.getClass().getName());
                if (isLock) {
                    PushMessage message = JSON.parseObject(json, PushMessage.class);
                    Map<String, String> params = new HashMap<>();
                    params.put("refId", message.getRefId());
                    params.put("messageType", message.getMessageType().name());

                    PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.all())
                            .setAudience(Audience.alias(message.getUserId()))
                            .setNotification(Notification.newBuilder()
                                    .addPlatformNotification(AndroidNotification.newBuilder()
                                            .addExtras(params)
                                            .setAlert(message.getContent())
                                            .build())
                                    .addPlatformNotification(
                                            IosNotification.newBuilder()
                                                    .addExtras(params)
                                                    .setBadge(1)
                                                    .setAlert(message.getContent())
                                                    .setSound(IOS_SOUND)
                                                    .build()).build())
                            .setOptions(Options.newBuilder()
                                    .setApnsProduction(true)
                                    .build()).build();
                    PushResult result = jPushClient.sendPush(payload);
                    LOGGER.info("jpush send status:", result.getResponseCode());
                    return ReturnT.SUCCESS;
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                if (isLock) {
                    distributedLock.releaseLock();
                }
            }
            return ReturnT.FAIL;
        }


    }
}
