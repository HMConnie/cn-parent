package cn.connie.job.boot.impl;

import cn.connie.common.model.PushMessage;
import cn.connie.job.service.PushRemoteService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PushRemoteServiceImpl implements PushRemoteService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${push.store.key}")
    private String PUSH_STORE_KEY;

    @Override
    public void push(String userId, String appId, String content, PushMessage.PushMessageType messageType, String refId) {
        PushMessage message = new PushMessage();
        message.setAppId(appId);
        message.setContent(content);
        message.setMessageType(messageType);
        message.setUserId(userId);
        if (StringUtils.isNotBlank(refId)) {
            message.setRefId(refId);
        }
        redisTemplate.boundListOps(PUSH_STORE_KEY).rightPush(JSON.toJSONString(message));

    }
}
