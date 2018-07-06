package cn.connie.goods.core.impl;

import cn.connie.common.exception.CustomException;
import cn.connie.goods.core.dao.GoodsCategoryMapper;
import cn.connie.goods.core.dao.GoodsDescMapper;
import cn.connie.goods.core.dao.GoodsMapper;
import cn.connie.goods.core.entity.*;
import cn.connie.goods.from.GoodsCategoryFrom;
import cn.connie.goods.from.GoodsDescFrom;
import cn.connie.goods.from.GoodsFrom;
import cn.connie.goods.service.GoodsService;
import cn.connie.goods.to.GoodsCategoryTO;
import cn.connie.goods.to.GoodsDescTO;
import cn.connie.goods.to.GoodsTO;
import com.sgcai.commons.lang.utils.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsDescMapper goodsDescMapper;

    @Override
    public void insertCategory(GoodsCategoryFrom[] goodsCategoryFromArray) {
        List<GoodsCategoryFrom> goodsCategoryFromList = Arrays.asList(goodsCategoryFromArray);
        List<GoodsCategory> goodsCategoryList = BeanConvertUtils.convert(goodsCategoryFromList, GoodsCategory.class);
        for (GoodsCategory goodsCategory : goodsCategoryList) {
            goodsCategoryMapper.insert(goodsCategory);
        }
    }

    @Override
    public void insertGoods(GoodsFrom[] goodsFromArray) {
        List<GoodsFrom> goodsFroms = Arrays.asList(goodsFromArray);
        List<Goods> goodsList = BeanConvertUtils.convert(goodsFroms, Goods.class);
        for (Goods goods : goodsList) {
            goodsMapper.insert(goods);
        }
    }

    @Override
    public void insertGoodsDesc(GoodsDescFrom goodsDescForm) {
        GoodsDesc goodsDesc = BeanConvertUtils.convert(goodsDescForm, GoodsDesc.class);
        goodsDescMapper.insert(goodsDesc);
    }

    @Override
    public List<GoodsCategoryTO> getGoodsCategoryByTimeDesc() {
        GoodsCategoryExample example = new GoodsCategoryExample();
        example.setOrderByClause("created DESC,updated DESC,sort_order DESC");
        List<GoodsCategory> goodsCategoryList = goodsCategoryMapper.selectByExample(example);
        return BeanConvertUtils.convert(goodsCategoryList, GoodsCategoryTO.class);
    }

    @Override
    public List<GoodsTO> getGoodsByCategoryWithTimeDesc(String categoryId) {
        GoodsExample example = new GoodsExample();
        example.setOrderByClause("created DESC,updated DESC");
        GoodsExample.Criteria criteria = example.createCriteria();
        criteria.andCidEqualTo(categoryId);
        criteria.andStatusEqualTo(new Byte("1"));
        List<Goods> goodsList = goodsMapper.selectByExample(example);
        return BeanConvertUtils.convert(goodsList, GoodsTO.class);
    }

    @Override
    public GoodsDescTO getGoodsDescById(String goodsId) {
        GoodsDescExample example = new GoodsDescExample();
        GoodsDescExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        List<GoodsDesc> goodsDescList = goodsDescMapper.selectByExampleWithBLOBs(example);
        if (goodsDescList.size() > 0 && goodsDescList.get(0) != null) {
            return BeanConvertUtils.convert(goodsDescList.get(0), GoodsDescTO.class);
        }
        return null;

    }

    @Override
    public List<GoodsTO> getGoodsByIds(String[] goodsIds) throws CustomException {
        List<GoodsTO> list = new ArrayList<>();
        for (String goodsId : goodsIds) {
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            if (goods == null) {
                throw new CustomException("not find goods", "商品不存在");
            }
            GoodsTO goodsTO = BeanConvertUtils.convert(goods, GoodsTO.class);
            list.add(goodsTO);
        }
        return list;
    }

    @Override
    public GoodsTO getGoodsByIds(String goodsId) throws CustomException {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        return BeanConvertUtils.convert(goods, GoodsTO.class);
    }
}
