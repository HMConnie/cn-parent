package cn.connie.cashier.web.boot.controller;

import cn.connie.cashier.service.PayService;
import cn.connie.cashier.to.PayResultTO;
import cn.connie.common.alipay.model.AlipayModel;
import cn.connie.common.utils.ResponseUtils;
import cn.connie.common.weichat.model.WeiChatModel;
import com.sgcai.commons.lang.utils.Dui1DuiStringUtils;
import com.sgcai.commons.lang.utils.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
@RequestMapping("/cashier")
public class PayController {

    @Resource
    private PayService payService;

    @RequestMapping(value = "/appPay", method = RequestMethod.POST)
    @ResponseBody
    public String appPay(HttpServletRequest request, @RequestParam("price") String price, @RequestParam("type") Integer type) {
        PayResultTO payResultTO;
        String expireTime = getExpireTime();
        String ip = WebUtils.getIp(request);
        if (type == 1) {
            AlipayModel alipayModel = new AlipayModel();
            alipayModel.setTotalFee(price);
            alipayModel.setFailureTimeMinutes(expireTime);
            alipayModel.setIp(ip);
            alipayModel.setAlipayTradeNo(Dui1DuiStringUtils.generateOrderNo());
            alipayModel.setOutTradeNo(Dui1DuiStringUtils.generateOrderNo());
            payResultTO = payService.aliPay(alipayModel);
        } else {
            WeiChatModel weiChatModel = new WeiChatModel();
            weiChatModel.setTotal_price(price);
            weiChatModel.setTime_expire(expireTime);
            weiChatModel.setIp(ip);
            weiChatModel.setBody("");
            payResultTO = payService.weiChatPay(weiChatModel);
        }
        return ResponseUtils.toSuccessResponse(payResultTO);
    }

    private String getExpireTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(Calendar.MINUTE);
        calendar.set(Calendar.MINUTE, minute + 10);
        return simpleDateFormat.format(calendar.getTime());
    }

}
