package cn.connie.goods.service;

import cn.connie.goods.from.OrderFrom;
import cn.connie.goods.from.OrderItemFrom;
import cn.connie.goods.to.EncryptionParameter;
import cn.connie.goods.to.OrderItemTO;
import cn.connie.goods.to.OrderTO;
import com.sgcai.commons.lang.base.CollectionTO;

import java.util.List;

public interface OrderService {
    void insert(OrderFrom orderFrom);

    void insert(OrderItemFrom[] orderItemFroms);

    CollectionTO<OrderTO> getOrderList(String userId, Byte status, int pageNo, int pageSize);

    List<OrderItemTO> getOrderItemList(String orderId);

    String encryptionParameters(EncryptionParameter json);

    OrderTO getOrderById(String orderId);
}
