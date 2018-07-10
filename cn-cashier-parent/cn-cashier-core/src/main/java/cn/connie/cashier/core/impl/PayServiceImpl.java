package cn.connie.cashier.core.impl;

import cn.connie.cashier.service.PayService;
import cn.connie.cashier.to.PayResultTO;
import cn.connie.common.alipay.AlipayCore;
import cn.connie.common.alipay.AlipayInfoUtil;
import cn.connie.common.alipay.MD5;
import cn.connie.common.alipay.model.AlipayInfo;
import cn.connie.common.alipay.model.AlipayModel;
import cn.connie.common.weichat.model.WeiChatModel;
import cn.connie.common.weichat.utils.HttpsClientUtils;
import cn.connie.common.weichat.utils.SignUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sgcai.commons.lang.utils.Dui1DuiStringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class PayServiceImpl implements PayService {

    @Value("${weixin.notify.url}")
    private String WEIXIN_NOTIFY_URL;

    @Value("${alipay.gateway.new}")
    private String ALIPAY_GATEWAY_NEW;
    @Value("${alipay.notify.url}")
    private String ALIPAY_NOTIFY_URL;
    @Value("${alipay.return.url}")
    private String ALIPAY_RETURN_URL;

    /**
     * 微信开放平台审核通过的应用APPID
     */
    private static String APP_ID = "wx6a229df49360a04c";
    /**
     * 微信支付分配的商户号
     */
    private static String MCH_ID = "1494367982";

    @Override
    public PayResultTO aliPay(AlipayModel alipayModel) {
        PayResultTO result = new PayResultTO();
        AlipayInfo plipayConfig = AlipayInfoUtil.getPlipayConfig();
        try {
            // 调用支付接口
            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = alipayModel.getOutTradeNo();

            //订单名称，必填
            String subject = plipayConfig.getService_name() + alipayModel.getSubject();

            //付款金额，必填
            String total_fee = alipayModel.getTotalFee();

            //商品描述，可空
            String body = alipayModel.getBody();

            String extraCommonParam = alipayModel.getExtraCommonParam();

            String service = plipayConfig.serviceH5;


            //把请求参数打包成数组
            Map<String, String> sParaTemp = new HashMap<>();
            sParaTemp.put("service", service);
            sParaTemp.put("partner", plipayConfig.partner);
            sParaTemp.put("seller_id", plipayConfig.seller_id);
            sParaTemp.put("_input_charset", plipayConfig.input_charset);
            sParaTemp.put("payment_type", plipayConfig.payment_type);
            sParaTemp.put("notify_url", ALIPAY_NOTIFY_URL);
            sParaTemp.put("return_url", ALIPAY_RETURN_URL);
            sParaTemp.put("anti_phishing_key", "");
            sParaTemp.put("exter_invoke_ip", "");
            sParaTemp.put("out_trade_no", out_trade_no);
            sParaTemp.put("subject", subject);
            sParaTemp.put("total_fee", total_fee);
            sParaTemp.put("body", body);
            sParaTemp.put("extra_common_param", extraCommonParam);
            sParaTemp.put("it_b_pay", alipayModel.getFailureTimeMinutes());
            sParaTemp.put("app_pay", "Y");//启用此参数可唤起钱包APP支付

            //除去数组中的空值和签名参数
            Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
            //生成签名结果

            String prestr = AlipayCore.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            String mysign = MD5.sign(prestr, plipayConfig.key, plipayConfig.input_charset);

            //签名结果与签名方式加入请求提交参数组中
            sPara.put("sign", mysign);
            sPara.put("sign_type", plipayConfig.sign_type);

            StringBuilder sb = new StringBuilder();

            for (String key : sPara.keySet()) {
                if (sb.length() != 0) {
                    sb.append("&");
                }
                sb.append(key).append("=").append(URLEncoder.encode(sPara.get(key), "UTF-8"));
            }
            result.setResult(true);
            result.setRedirectURL(ALIPAY_GATEWAY_NEW + sb.toString());
            result.setClientParam(sb.toString());
        } catch (Exception e) {
            result.setResult(false);
        }
        return result;
    }

    /**
     * 字段名	变量名	必填	类型	示例值	描述
     * 应用ID	appid	是	String(32)	wxd678efh567hg6787	微信开放平台审核通过的应用APPID
     * 商户号	mch_id	是	String(32)	1230000109	微信支付分配的商户号
     * 随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法
     * 签名	sign	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法
     * 商品描述	body	是	String(128)	腾讯充值中心-QQ会员充值商品描述交易字段格式根据不同的应用场景按照以下格式：APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
     * 商户订单号	out_trade_no	是	String(32)	20150806125346	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
     * 总金额	total_fee	是	Int	888	订单总金额，单位为分，详见支付金额
     * 终端IP	spbill_create_ip	是	String(16)	123.12.12.123	用户端实际ip
     * 通知地址	notify_url	是	String(256)	http://www.weixin.qq.com/wxpay/pay.php	接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
     * 交易类型	trade_type	是	String(16)	APP	支付类型
     */
    @Override
    public PayResultTO weiChatPay(WeiChatModel weiChatModel) {
        PayResultTO payResult = new PayResultTO();
        try {
            BigDecimal bigDecimal = new BigDecimal(weiChatModel.getTotal_price());
            BigDecimal multiply = bigDecimal.multiply(new BigDecimal("100"));
            SortedMap<String, String> sort = new TreeMap<>();
            sort.put("appid", APP_ID);
            sort.put("mch_id", MCH_ID);
            sort.put("nonce_str", Dui1DuiStringUtils.generateUUID());
            sort.put("body", (weiChatModel.getBody() == null || "".equals(weiChatModel.getBody())) ? "[benben]交易" : "[benben]" + weiChatModel.getBody());
            sort.put("attach", (weiChatModel.getBody() == null || "".equals(weiChatModel.getBody())) ? "[benben]交易" : "[benben]" + weiChatModel.getBody());
            sort.put("out_trade_no",Dui1DuiStringUtils.generateOrderNo());
            sort.put("total_fee", "" + multiply.longValue());
            sort.put("spbill_create_ip", weiChatModel.getIp());
            sort.put("notify_url", WEIXIN_NOTIFY_URL);
            sort.put("time_expire", weiChatModel.getTime_expire());
            sort.put("trade_type", "APP");
            sort.put("sign", SignUtils.sign(sort));

            HttpsClientUtils hcu = new HttpsClientUtils();
            String s = hcu.doPost("https://api.mch.weixin.qq.com/pay/unifiedorder", SignUtils.xml(sort), "utf-8");

            Document document = DocumentHelper.parseText(s);
            Element rootElement = document.getRootElement();
            Element return_code = rootElement.element("return_code");
            if ("SUCCESS".equals(return_code.getStringValue())) {
                payResult.setResult(true);
                Map<String, String> returnBodyJson = new HashMap<>();
                returnBodyJson.put("appid", rootElement.element("appid").getText());
                returnBodyJson.put("partnerid", rootElement.element("mch_id").getText());
                returnBodyJson.put("prepayid", rootElement.element("prepay_id").getText());
                returnBodyJson.put("package", "Sign=WXPay");
                returnBodyJson.put("noncestr", Dui1DuiStringUtils.generateUUID());
                returnBodyJson.put("timestamp", String.valueOf(System.currentTimeMillis()).substring(0, 10));
                returnBodyJson.put("sign", SignUtils.sign(returnBodyJson));
                payResult.setClientParam(JSONObject.toJSONString(returnBodyJson, SerializerFeature.WriteMapNullValue));
            } else {
                payResult.setResult(false);
            }
        } catch (Exception e) {
            payResult.setResult(false);
        }
        return payResult;
    }
}
