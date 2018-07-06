package cn.connie.goods.to;

import com.sgcai.commons.lang.base.BasicTO;

import java.util.Date;

public class GoodsDescTO extends BasicTO {
    private String goodsId;

    private Date created;

    private Date updated;

    private String goodsDesc;

    private static final long serialVersionUID = 1L;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc == null ? null : goodsDesc.trim();
    }

    @Override
    public String toString() {
        return "GoodsDescTO{" +
                "goodsId='" + goodsId + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", goodsDesc='" + goodsDesc + '\'' +
                '}';
    }
}
