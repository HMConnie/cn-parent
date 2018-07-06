package cn.connie.goods.core.impl;

import cn.connie.goods.core.dao.OrderItemMapper;
import cn.connie.goods.core.dao.OrderMapper;
import cn.connie.goods.core.entity.Order;
import cn.connie.goods.core.entity.OrderExample;
import cn.connie.goods.core.entity.OrderItem;
import cn.connie.goods.core.entity.OrderItemExample;
import cn.connie.goods.from.OrderFrom;
import cn.connie.goods.from.OrderItemFrom;
import cn.connie.goods.service.OrderService;
import cn.connie.goods.to.EncryptionParameter;
import cn.connie.goods.to.OrderItemTO;
import cn.connie.goods.to.OrderTO;
import com.alibaba.fastjson.JSON;
import com.sgcai.commons.lang.base.CollectionTO;
import com.sgcai.commons.lang.utils.AESUtils;
import com.sgcai.commons.lang.utils.BeanConvertUtils;
import com.sgcai.commons.lang.utils.Dui1DuiStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;


    @Override
    public void insert(OrderFrom orderFrom) {
        Order order = BeanConvertUtils.convert(orderFrom, Order.class);
        order.setCreateTime(new Date());
        orderMapper.insert(order);
    }

    @Override
    public void insert(OrderItemFrom[] orderItemFroms) {
        for (OrderItemFrom orderItemFrom : orderItemFroms) {
            OrderItem orderItem = BeanConvertUtils.convert(orderItemFrom, OrderItem.class);
            orderItem.setId(Dui1DuiStringUtils.generateUUID());
            orderItem.setCreateTime(new Date());
            orderItemMapper.insert(orderItem);
        }
    }

    @Override
    public CollectionTO<OrderTO> getOrderList(String userId, Byte status, int pageNo, int pageSize) {
        int offset = pageNo > 0 ? (pageNo - 1) * pageSize : 0;
        OrderExample example = new OrderExample();
        example.setOffset(offset);
        example.setLimit(pageSize);
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andStatusEqualTo(status);
        List<Order> orders = orderMapper.selectByExample(example);
        long count = orderMapper.countByExample(example);
        return new CollectionTO<>(BeanConvertUtils.convert(orders, OrderTO.class), count, pageSize);
    }

    @Override
    public List<OrderItemTO> getOrderItemList(String orderId) {
        OrderItemExample example = new OrderItemExample();
        OrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
        return BeanConvertUtils.convert(orderItems, OrderItemTO.class);
    }

    @Override
    public String encryptionParameters(EncryptionParameter encryptionParameter) {
        String json = JSON.toJSONString(encryptionParameter);
        String encrypionStr = null;
        try {
            encrypionStr = AESUtils.encrypt(json, "65543m2154yy2304");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypionStr;
    }

    @Override
    public OrderTO getOrderById(String orderId) {
        return BeanConvertUtils.convert(orderMapper.selectByPrimaryKey(orderId), OrderTO.class);
    }

}
