package cn.connie.goods.core.dao;

import cn.connie.goods.core.entity.GoodsCategory;
import cn.connie.goods.core.entity.GoodsCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsCategoryMapper {
    long countByExample(GoodsCategoryExample example);

    int deleteByExample(GoodsCategoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    List<GoodsCategory> selectByExample(GoodsCategoryExample example);

    GoodsCategory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GoodsCategory record, @Param("example") GoodsCategoryExample example);

    int updateByExample(@Param("record") GoodsCategory record, @Param("example") GoodsCategoryExample example);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);

    GoodsCategory lockSelectByPrimaryKey(String id);
}