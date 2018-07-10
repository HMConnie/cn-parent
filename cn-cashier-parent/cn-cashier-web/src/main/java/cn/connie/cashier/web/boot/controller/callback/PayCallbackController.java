package cn.connie.cashier.web.boot.controller.callback;

import cn.connie.common.alipay.AlipayNotify;
import com.sgcai.commons.lang.utils.Dui1DuiStringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付宝和微信的回调必须是在外网带域名的情况下才能回调
 */
@Controller
@RequestMapping("/callback")
public class PayCallbackController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayCallbackController.class);

    @RequestMapping("/weiChatNotifyUrl.html")
    public void notifyRefundUrl(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info(body);
        Document document = DocumentHelper.parseText(body);
        Element rootElement = document.getRootElement();

        String return_code = rootElement.element("return_code").getStringValue();
        String out_trade_no = rootElement.element("out_trade_no").getStringValue();
        String transaction_id = rootElement.element("transaction_id").getStringValue();
        if ("SUCCESS".equals(return_code)) {
            String result_code = rootElement.element("result_code").getStringValue();
            if ("SUCCESS".equals(result_code)) {
//                NotifyDataBTO notifyDataBTO = new NotifyDataBTO();
//                notifyDataBTO.setOrderNo(out_trade_no);
//                notifyDataBTO.setThirdPartyOrderNo(transaction_id);
//                notifyDataBTO.setTradeStatus(TradeStatus.SUCCESS);
//                cashierTradeBusiness.dealNotifyUrl(notifyDataBTO);
            } else {
//                NotifyDataBTO notifyDataBTO = new NotifyDataBTO();
//                notifyDataBTO.setOrderNo(out_trade_no);
//                notifyDataBTO.setThirdPartyOrderNo(transaction_id);
//                notifyDataBTO.setTradeStatus(TradeStatus.FAIL);
//                cashierTradeBusiness.dealNotifyUrl(notifyDataBTO);
            }
        }

        String returns = ""
                + "<xml>"
                + "  <return_code><![CDATA[SUCCESS]]></return_code>"
                + "  <return_msg><![CDATA[OK]]></return_msg>"
                + "</xml>";
        PrintWriter out = response.getWriter();
        out.print(returns);
        out.close();
    }


    @RequestMapping("/alipayNotifyUrl.html")
    public void alipayNotifyUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String outTradeNo = new String(request.getParameter("batch_no").getBytes("ISO-8859-1"), "UTF-8");
            LOGGER.info("outTradeNo：" + outTradeNo);
//            CashierBusinessType cashierBusinessType= cashierTradeBusiness.getCashierBusinessTypeByTradeFlowOrderNo(outTradeNo);
            if (verifySignature(request)) {// 验证成功
                LOGGER.info("****************验签成功");
                //收银台主订单号
                String tradeOrderNo = null;
                // 商户订单号
                String batch_no = new String(request.getParameter("batch_no").getBytes("ISO-8859-1"), "UTF-8");

                String success_num = new String(request.getParameter("success_num").getBytes("ISO-8859-1"), "UTF-8");
                LOGGER.info("****************主订单号" + tradeOrderNo);
                LOGGER.info("****************退款批次号" + batch_no);
                LOGGER.info("****************支付宝交易号00" + Dui1DuiStringUtils.generateOrderNo());
                LOGGER.info("****************退款成功总数" + new String(request.getParameter("success_num").getBytes("ISO-8859-1"), "UTF-8"));
                LOGGER.info("****************退款结果明细" + new String(request.getParameter("result_details").getBytes("ISO-8859-1"), "UTF-8"));
                if (Integer.parseInt(success_num) > 0) {
                    LOGGER.info("****************调用收银台回调接口成功");
                }
                out.print("success"); // 请不要修改或删除
            } else {// 验证失败
                LOGGER.info("****************验签失败");
                out.print("fail");
            }
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


    /**
     * 调用支付宝的验签方法
     *
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    private boolean verifySignature(HttpServletRequest request) throws UnsupportedEncodingException {
        // 获取支付宝反馈过来的信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iterator = requestParams.keySet().iterator(); iterator.hasNext(); ) {
            String name = (String) iterator.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            if (!"redirectUrls".equals(name)) {
                params.put(name, valueStr);
            }
        }

        return AlipayNotify.verify(params);
    }
}
