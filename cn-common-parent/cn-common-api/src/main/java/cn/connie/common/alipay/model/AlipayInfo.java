package cn.connie.common.alipay.model;

/**
 * Created by zxg on 2017/5/10.
 */
public class AlipayInfo {

  // 合作身份者ID，以2088开头由16位纯数字组成的字符串
  public String partner;

  // 商户的私钥
  public String key;

  // 字符编码格式 目前支持 gbk 或 utf-8
  public String input_charset;

  // 签名方式 不需修改
  public String sign_type;

  // 付款方账号
  public String email;

  // 付款方账号
  public String account_name;

  // 调用pc的接口名
  public String servicePC;

  // 调用h5的接口名
  public String serviceH5;

  // 调用退款的接口名
  public String serviceRefund;

  // 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
  public String seller_id;

  // 支付类型 ，无需修改
  public String payment_type;

  // 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
  public String exter_invoke_ip;


  public String service_name;


  public String getService_name() {
    return service_name;
  }

  public void setService_name(String service_name) {
    this.service_name = service_name;
  }

  public String getPartner() {
    return partner;
  }

  public void setPartner(String partner) {
    this.partner = partner;
  }

  public String getKey() {
    return key;
  }

  public String getServiceRefund() {
    return serviceRefund;
  }

  public void setServiceRefund(String serviceRefund) {
    this.serviceRefund = serviceRefund;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getInput_charset() {
    return input_charset;
  }

  public void setInput_charset(String input_charset) {
    this.input_charset = input_charset;
  }

  public String getSign_type() {
    return sign_type;
  }

  public void setSign_type(String sign_type) {
    this.sign_type = sign_type;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAccount_name() {
    return account_name;
  }

  public void setAccount_name(String account_name) {
    this.account_name = account_name;
  }

  public String getServicePC() {
    return servicePC;
  }

  public void setServicePC(String servicePC) {
    this.servicePC = servicePC;
  }

  public String getServiceH5() {
    return serviceH5;
  }

  public void setServiceH5(String serviceH5) {
    this.serviceH5 = serviceH5;
  }

  public String getSeller_id() {
    return seller_id;
  }

  public void setSeller_id(String seller_id) {
    this.seller_id = seller_id;
  }

  public String getPayment_type() {
    return payment_type;
  }

  public void setPayment_type(String payment_type) {
    this.payment_type = payment_type;
  }

  public String getExter_invoke_ip() {
    return exter_invoke_ip;
  }

  public void setExter_invoke_ip(String exter_invoke_ip) {
    this.exter_invoke_ip = exter_invoke_ip;
  }
}
