package cn.connie.goods.core.dao;

import cn.connie.goods.core.entity.GoodsDesc;
import cn.connie.goods.core.entity.GoodsDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsDescMapper {
    long countByExample(GoodsDescExample example);

    int deleteByExample(GoodsDescExample example);

    int deleteByPrimaryKey(String goodsId);

    int insert(GoodsDesc record);

    int insertSelective(GoodsDesc record);

    List<GoodsDesc> selectByExampleWithBLOBs(GoodsDescExample example);

    List<GoodsDesc> selectByExample(GoodsDescExample example);

    GoodsDesc selectByPrimaryKey(String goodsId);

    int updateByExampleSelective(@Param("record") GoodsDesc record, @Param("example") GoodsDescExample example);

    int updateByExampleWithBLOBs(@Param("record") GoodsDesc record, @Param("example") GoodsDescExample example);

    int updateByExample(@Param("record") GoodsDesc record, @Param("example") GoodsDescExample example);

    int updateByPrimaryKeySelective(GoodsDesc record);

    int updateByPrimaryKeyWithBLOBs(GoodsDesc record);

    int updateByPrimaryKey(GoodsDesc record);

    GoodsDesc lockSelectByPrimaryKey(String goodsId);
}