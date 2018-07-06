package cn.connie.community.core.dao;

import cn.connie.community.core.entity.CommunityComment;
import cn.connie.community.core.entity.CommunityCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommunityCommentMapper {
    long countByExample(CommunityCommentExample example);

    int deleteByExample(CommunityCommentExample example);

    int deleteByPrimaryKey(String id);

    int insert(CommunityComment record);

    int insertSelective(CommunityComment record);

    List<CommunityComment> selectByExample(CommunityCommentExample example);

    CommunityComment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CommunityComment record, @Param("example") CommunityCommentExample example);

    int updateByExample(@Param("record") CommunityComment record, @Param("example") CommunityCommentExample example);

    int updateByPrimaryKeySelective(CommunityComment record);

    int updateByPrimaryKey(CommunityComment record);

    CommunityComment lockSelectByPrimaryKey(String id);
}