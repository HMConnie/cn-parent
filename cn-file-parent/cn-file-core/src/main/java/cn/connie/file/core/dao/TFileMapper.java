package cn.connie.file.core.dao;

import cn.connie.file.core.entity.TFile;
import cn.connie.file.core.entity.TFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TFileMapper {
    long countByExample(TFileExample example);

    int deleteByExample(TFileExample example);

    int deleteByPrimaryKey(String id);

    int insert(TFile record);

    int insertSelective(TFile record);

    List<TFile> selectByExample(TFileExample example);

    TFile selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TFile record, @Param("example") TFileExample example);

    int updateByExample(@Param("record") TFile record, @Param("example") TFileExample example);

    int updateByPrimaryKeySelective(TFile record);

    int updateByPrimaryKey(TFile record);

    TFile lockSelectByPrimaryKey(String id);
}