package cn.connie.goods.from;

import com.sgcai.commons.lang.base.BasicTO;

import java.math.BigDecimal;
import java.util.Date;

public class OrderFrom extends BasicTO {
    private String id;
    private String paymentNo;

    private String userId;

    private String orderNo;

    private BigDecimal payment;

    private Byte paymentType;

    private BigDecimal goodsSumFee;

    private BigDecimal postFee;

    private Byte status;

    private Date paymentTime;

    private String address;

    private String mobile;

    private String nickname;

    private Byte sendStatus;

    private String refundNo;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo == null ? null : paymentNo.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Byte getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Byte paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getGoodsSumFee() {
        return goodsSumFee;
    }

    public void setGoodsSumFee(BigDecimal goodsSumFee) {
        this.goodsSumFee = goodsSumFee;
    }

    public BigDecimal getPostFee() {
        return postFee;
    }

    public void setPostFee(BigDecimal postFee) {
        this.postFee = postFee;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Byte getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Byte sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo == null ? null : refundNo.trim();
    }
}
