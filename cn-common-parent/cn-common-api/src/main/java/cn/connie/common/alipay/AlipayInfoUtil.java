package cn.connie.common.alipay;


import cn.connie.common.alipay.model.AlipayInfo;

/**
 * Created by zxg on 2017/5/10.
 */
public class AlipayInfoUtil {

    public static AlipayInfo getPlipayConfig() {
        AlipayInfo alipayInfo = new AlipayInfo();
        alipayInfo.setServicePC("create_direct_pay_by_user");
        alipayInfo.setServiceH5("alipay.wap.create.direct.pay.by.user");
        alipayInfo.setServiceRefund("refund_fastpay_by_platform_nopwd");
        alipayInfo.setPartner("2088101846074913");
        alipayInfo.setKey("l2a1m57st1urldekjb9t2686ck7f34vd");
        alipayInfo.setInput_charset("utf-8");
        alipayInfo.setSign_type("MD5");
        alipayInfo.setEmail("score@shanggucai.com");
        alipayInfo.setAccount_name("北京上古彩科技股份有限公司");
        alipayInfo.setSeller_id(alipayInfo.getPartner());
        alipayInfo.setPayment_type("1");
        alipayInfo.setExter_invoke_ip("");
        alipayInfo.setService_name("[上古彩]");
        return alipayInfo;
    }

}
