package cn.connie.common.model;

import com.sgcai.commons.lang.base.BasicTO;

public class ContentConsumerResultMQ extends BasicTO {

    public String refId;
    public String msgType;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }
}
