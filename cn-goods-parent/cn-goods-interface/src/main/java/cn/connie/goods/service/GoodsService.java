package cn.connie.goods.service;

import cn.connie.common.exception.CustomException;
import cn.connie.goods.from.GoodsCategoryFrom;
import cn.connie.goods.from.GoodsDescFrom;
import cn.connie.goods.from.GoodsFrom;
import cn.connie.goods.to.GoodsCategoryTO;
import cn.connie.goods.to.GoodsDescTO;
import cn.connie.goods.to.GoodsTO;

import java.util.List;

public interface GoodsService {

    /**
     * 不能够传入List集合或Map集合，只能传入数组
     *
     * @param goodsCategoryFromList
     */
    void insertCategory(GoodsCategoryFrom[] goodsCategoryFromList);

    void insertGoods(GoodsFrom[] goodsFroms);

    void insertGoodsDesc(GoodsDescFrom goodsDescForm);

    List<GoodsCategoryTO> getGoodsCategoryByTimeDesc();

    List<GoodsTO> getGoodsByCategoryWithTimeDesc(String categoryId);

    GoodsDescTO getGoodsDescById(String goodsId);

    List<GoodsTO> getGoodsByIds(String[] goodsIds) throws CustomException;

    GoodsTO getGoodsByIds(String goodsId) throws CustomException;

}
