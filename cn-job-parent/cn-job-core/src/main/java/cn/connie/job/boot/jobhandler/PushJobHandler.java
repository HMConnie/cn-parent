package cn.connie.job.boot.jobhandler;

import cn.connie.common.model.PushMessage;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
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
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundListOperations;
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


    @Override
    public ReturnT<String> execute(String s) throws Exception {
        BoundListOperations<String, String> listOps = redisTemplate.boundListOps(PUSH_STORE_KEY);
        Long listOpsSize = listOps.size();
        String json = null;
        try {
            for (int i = 0; i < listOpsSize; i++) {
                json = listOps.leftPop();
                pushMessage(json);
            }
        } catch (Exception e) {
            LOGGER.info("jpush message failed:", e);
            // 如果发送失败，把这次pop出来的消息，重新push到redis中，防止推送消息丢失
            if (!StringUtils.isBlank(json)) {
                listOps.rightPush(json);
            }
            return ReturnT.FAIL;
        }

        return ReturnT.SUCCESS;
    }

    /**
     * 发送极光推送
     *
     * @param json
     * @throws APIConnectionException
     * @throws APIRequestException
     */
    private void pushMessage(String json) throws APIConnectionException, APIRequestException {
        if (StringUtils.isBlank(json)) return;

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
        LOGGER.info("jpush send status:", result.statusCode);
    }
}
