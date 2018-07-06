package cn.connie.api.controller;

import cn.connie.business.from.SubmitOrderBFrom;
import cn.connie.business.service.GoodsBusinessService;
import cn.connie.business.to.OnlineUserBTO;
import cn.connie.business.to.OrderBTO;
import cn.connie.business.to.OrderDetailBTO;
import cn.connie.business.to.SubmitResultBTO;
import cn.connie.common.annotation.NeedLogin;
import cn.connie.common.exception.CustomException;
import cn.connie.common.utils.ResponseUtils;
import com.sgcai.commons.lang.base.CollectionTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class GoodsController {


    @Resource
    private GoodsBusinessService goodsBusinessService;

    @RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
    @ResponseBody
    @NeedLogin
    public String submitOrder(OnlineUserBTO onlineUserBTO,
                              @RequestParam("mobile") String mobile,
                              @RequestParam("address") String address,
                              @RequestParam("name") String name,
                              @RequestParam("goodsList") String goodsList) throws CustomException {
        SubmitOrderBFrom submitOrderBFrom = new SubmitOrderBFrom();
        submitOrderBFrom.setUserId(onlineUserBTO.getUserId());
        submitOrderBFrom.setName(name);
        submitOrderBFrom.setAddress(address);
        submitOrderBFrom.setMobile(mobile);
        submitOrderBFrom.setGoodsList(goodsList);
        SubmitResultBTO submitResultBTO = goodsBusinessService.submitOrder(submitOrderBFrom);
        return ResponseUtils.toSuccessResponse(submitResultBTO);
    }

    @RequestMapping(value = "/order/list", method = RequestMethod.GET)
    @ResponseBody
    @NeedLogin
    public String orderList(OnlineUserBTO onlineUserBTO,
                            @RequestParam("pageNo") int pageNo,
                            @RequestParam("pageSize") int pageSize,
                            @RequestParam("status") int status) {
        String userId = onlineUserBTO.getUserId();
        Byte bStatus = new Byte(String.valueOf(status));
        CollectionTO<OrderBTO> collectionTO = goodsBusinessService.getOrderList(userId, bStatus, pageNo, pageSize);
        return ResponseUtils.toSuccessResponse(collectionTO);
    }

    @RequestMapping(value = "/order/detail", method = RequestMethod.GET)
    @ResponseBody
    @NeedLogin
    public String orderDetail(String orderId) throws CustomException {
        OrderDetailBTO orderDetail = goodsBusinessService.getOrderDetail(orderId);
        return ResponseUtils.toSuccessResponse(orderDetail);
    }
}
