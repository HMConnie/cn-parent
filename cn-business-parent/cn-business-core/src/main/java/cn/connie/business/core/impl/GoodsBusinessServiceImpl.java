package cn.connie.business.core.impl;

import cn.connie.business.from.SubmitOrderBFrom;
import cn.connie.business.service.GoodsBusinessService;
import cn.connie.business.to.GoodsBTO;
import cn.connie.business.to.OrderBTO;
import cn.connie.business.to.OrderDetailBTO;
import cn.connie.business.to.SubmitResultBTO;
import cn.connie.common.exception.CustomException;
import cn.connie.common.type.CashierBusinessType;
import cn.connie.common.type.ClientType;
import cn.connie.goods.from.OrderFrom;
import cn.connie.goods.from.OrderItemFrom;
import cn.connie.goods.service.GoodsService;
import cn.connie.goods.service.OrderService;
import cn.connie.goods.to.EncryptionParameter;
import cn.connie.goods.to.GoodsTO;
import cn.connie.goods.to.OrderItemTO;
import cn.connie.goods.to.OrderTO;
import com.sgcai.commons.lang.base.CollectionTO;
import com.sgcai.commons.lang.utils.BeanConvertUtils;
import com.sgcai.commons.lang.utils.Dui1DuiStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class GoodsBusinessServiceImpl implements GoodsBusinessService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    /**
     * @param submitOrderBFrom
     * @return
     * @throws CustomException
     */
    @Override
    public SubmitResultBTO submitOrder(SubmitOrderBFrom submitOrderBFrom) throws CustomException {
        String orderNo = Dui1DuiStringUtils.generateOrderNo();
        String orderId = Dui1DuiStringUtils.generateUUID();
        OrderFrom orderFrom = new OrderFrom();
        orderFrom.setAddress(submitOrderBFrom.getAddress());
        orderFrom.setMobile(submitOrderBFrom.getMobile());
        orderFrom.setNickname(submitOrderBFrom.getName());
        orderFrom.setUserId(submitOrderBFrom.getUserId());
        BigDecimal totalFee = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_UP);
        List<OrderItemFrom> orderItemFromList = new ArrayList<>();
        for (SubmitOrderBFrom.SubmitOrderGoods submitOrderGoods : submitOrderBFrom.getSubmitOrderGoodsList()) {
            GoodsTO goodsTO = goodsService.getGoodsByIds(submitOrderGoods.getId());
            if (goodsTO == null) {
                throw new CustomException("not find goods", "商品不存在");
            }
            BigDecimal price = goodsTO.getPrice().multiply(new BigDecimal(submitOrderGoods.getNum()));
            //商品价格累加
            totalFee = totalFee.add(price);
            OrderItemFrom orderItemFrom = new OrderItemFrom();
            orderItemFrom.setGoodsId(submitOrderGoods.getId());
            orderItemFrom.setOrderId(orderId);
            orderItemFrom.setNum(submitOrderGoods.getNum());
            orderItemFromList.add(orderItemFrom);
        }
        orderFrom.setGoodsSumFee(totalFee);
        orderFrom.setStatus(new Byte("1"));
        orderFrom.setOrderNo(orderNo);
        orderFrom.setId(orderId);
        orderService.insert(orderFrom);
        orderService.insert(orderItemFromList.toArray(new OrderItemFrom[]{}));


        SubmitResultBTO submitResultBTO = new SubmitResultBTO();
        submitResultBTO.setOrderId(orderNo);
        submitResultBTO.setOrderPracticalPrice(totalFee);
        EncryptionParameter encryptionParameter = new EncryptionParameter();
        encryptionParameter.setOriginOrderNo(orderNo);
        encryptionParameter.setBusinessType(CashierBusinessType.BENBEN_BUY);
        encryptionParameter.setUserId(orderFrom.getUserId());
        Calendar calendar = Calendar.getInstance();//追加10分钟
        calendar.add(Calendar.MINUTE, 10);
        encryptionParameter.setExpiryTime(calendar.getTime());
        encryptionParameter.setClientType(ClientType.CUSTOMER_ANDROID);
        String encryptionParameters = orderService.encryptionParameters(encryptionParameter);
        submitResultBTO.setEncryptionStr(encryptionParameters);

        return submitResultBTO;
    }

    @Override
    public CollectionTO<OrderBTO> getOrderList(String userId, Byte status, int pageNo, int pageSize) {
        CollectionTO<OrderTO> collectionTO = orderService.getOrderList(userId, status, pageNo, pageSize);
        return BeanConvertUtils.convert(collectionTO, OrderBTO.class);
    }

    @Override
    public OrderDetailBTO getOrderDetail(String orderId) throws CustomException {
        OrderTO orderTO = orderService.getOrderById(orderId);
        if (orderTO == null) {
            throw new CustomException("order not exist", "订单不存在");
        }
        List<OrderItemTO> orderItemList = orderService.getOrderItemList(orderId);
        String[] goodsIds = new String[orderItemList.size()];
        for (int i = 0; i < orderItemList.size(); i++) {
            goodsIds[i] = orderItemList.get(i).getGoodsId();
        }
        List<GoodsTO> goodsByIds = goodsService.getGoodsByIds(goodsIds);
        List<GoodsBTO> goodsBTOS = BeanConvertUtils.convert(goodsByIds, GoodsBTO.class);
        OrderBTO orderBTO = BeanConvertUtils.convert(orderTO, OrderBTO.class);
        OrderDetailBTO orderDetailBTO = new OrderDetailBTO();
        orderDetailBTO.setGoodsList(goodsBTOS);
        orderDetailBTO.setOrder(orderBTO);
        return orderDetailBTO;
    }
}
