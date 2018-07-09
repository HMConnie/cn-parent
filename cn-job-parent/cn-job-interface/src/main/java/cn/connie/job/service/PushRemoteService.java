package cn.connie.job.service;


import cn.connie.common.model.PushMessage;

public interface PushRemoteService {

    void push(String userId, String appId, String content, PushMessage.PushMessageType messageType, String refId);
}
