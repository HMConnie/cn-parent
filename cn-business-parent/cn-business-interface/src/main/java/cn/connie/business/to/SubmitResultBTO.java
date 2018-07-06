package cn.connie.business.to;

import com.sgcai.commons.lang.base.BasicTO;

import java.math.BigDecimal;

public class SubmitResultBTO extends BasicTO {

    private String orderId;
    private String orderNo;
    private BigDecimal orderPracticalPrice;
    private String encryptionStr;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getOrderPracticalPrice() {
        return orderPracticalPrice;
    }

    public void setOrderPracticalPrice(BigDecimal orderPracticalPrice) {
        this.orderPracticalPrice = orderPracticalPrice;
    }

    public String getEncryptionStr() {
        return encryptionStr;
    }

    public void setEncryptionStr(String encryptionStr) {
        this.encryptionStr = encryptionStr;
    }
}
