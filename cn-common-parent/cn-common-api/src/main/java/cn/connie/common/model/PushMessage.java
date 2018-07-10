package cn.connie.common.model;

import com.sgcai.commons.lang.base.BasicTO;

public class PushMessage extends BasicTO {

    private static final long serialVersionUID = 1L;

    private String content;

    private String userId;

    private String appId;

    private PushMessageType messageType;

    private String refId; // 业务参数

    public enum PushMessageType {
        CUSTOMER, //客服
        PRAISE, //点赞
        NOTICE,  //通知
        AFFICHE, //公告
        BROADCAST,//团长广播
        OTHER
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public PushMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(PushMessageType messageType) {
        this.messageType = messageType;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }
}
