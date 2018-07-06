package cn.connie.business.to;

import com.sgcai.commons.lang.base.BasicTO;

import java.util.List;

public class OrderDetailBTO extends BasicTO {
    public OrderBTO order;
    public List<GoodsBTO> goodsList;

    public OrderBTO getOrder() {
        return order;
    }

    public void setOrder(OrderBTO order) {
        this.order = order;
    }

    public List<GoodsBTO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsBTO> goodsList) {
        this.goodsList = goodsList;
    }
}
