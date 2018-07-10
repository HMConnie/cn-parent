package cn.connie.cashier.service;

import cn.connie.cashier.to.PayResultTO;
import cn.connie.common.alipay.model.AlipayModel;
import cn.connie.common.weichat.model.WeiChatModel;

public interface PayService {

    PayResultTO aliPay(AlipayModel alipayModel);

    PayResultTO weiChatPay(WeiChatModel weiChatModel);
}
