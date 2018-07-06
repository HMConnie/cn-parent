package cn.connie.business.from;

import cn.connie.common.utils.FastJsonUtils;
import com.sgcai.commons.lang.base.BasicTO;

import java.util.List;

public class SubmitOrderBFrom extends BasicTO {
    private String userId;
    private String mobile;
    private String address;
    private String name;
    private String goodsList;
    private List<SubmitOrderGoods> submitOrderGoodsList;

    public static class SubmitOrderGoods extends BasicTO {
        private String id;
        private Integer num;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }
    }

    public List<SubmitOrderGoods> getSubmitOrderGoodsList() {
        return submitOrderGoodsList;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(String goodsList) {
        this.goodsList = goodsList;
        this.submitOrderGoodsList = FastJsonUtils.toList(goodsList, SubmitOrderGoods.class);
    }
}
