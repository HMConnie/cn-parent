package cn.connie.business.service;

import cn.connie.business.from.SubmitOrderBFrom;
import cn.connie.business.to.OrderBTO;
import cn.connie.business.to.OrderDetailBTO;
import cn.connie.business.to.SubmitResultBTO;
import cn.connie.common.exception.CustomException;
import com.sgcai.commons.lang.base.CollectionTO;

import java.util.Collection;

public interface GoodsBusinessService {

    SubmitResultBTO submitOrder(SubmitOrderBFrom submitOrderBFrom) throws CustomException;


    CollectionTO<OrderBTO> getOrderList(String userId, Byte status, int pageNo, int pageSize);

    OrderDetailBTO getOrderDetail(String orderId) throws CustomException;
}
