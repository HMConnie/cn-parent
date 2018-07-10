package cn.connie.common.alipay.model;

import com.sgcai.commons.lang.base.BasicTO;

public class AlipayModel extends BasicTO {

    // 商户订单号，必填
    private String outTradeNo;

    // 商品名称，必填
    private String subject;

    // 付款金额，必填
    private String totalFee;

    // 商品描述，可空
    private String body;

    // 收银台主订单号
    private String extraCommonParam;

//    // 来源(pc/h5)
//    private SourceType sourceType;

    //失效时间（分钟）可空
    private String failureTimeMinutes;

    // 同步回调地址
    private String returnUrl;

    // 异步回调地址
    private String notifyUrl;

//    /**
//     * 收银台业务类型
//     */
//    private CashierBusinessType businessType;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 原流水订单号退款时使用
     */
    private String alipayTradeNo;

    public String getAlipayTradeNo() {
        return alipayTradeNo;
    }

    public void setAlipayTradeNo(String alipayTradeNo) {
        this.alipayTradeNo = alipayTradeNo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

//    public CashierBusinessType getBusinessType() {
//        return businessType;
//    }

//    public void setBusinessType(CashierBusinessType businessType) {
//        this.businessType = businessType;
//    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public String getFailureTimeMinutes() {
        return failureTimeMinutes + "m";
    }

    public void setFailureTimeMinutes(String failureTimeMinutes) {
        this.failureTimeMinutes = failureTimeMinutes;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

//    public SourceType getSourceType() {
//        return sourceType;
//    }
//
//    public void setSourceType(SourceType sourceType) {
//        this.sourceType=sourceType;
//    }

    public String getExtraCommonParam() {
        return extraCommonParam;
    }

    public void setExtraCommonParam(String extraCommonParam) {
        this.extraCommonParam = extraCommonParam;
    }

}
