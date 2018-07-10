package cn.connie.cashier.web.boot.controller.callback;

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
import java.io.PrintWriter;

@Controller
@RequestMapping("/callback")
public class WeChatPayCallbackController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatPayCallbackController.class);

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

}
