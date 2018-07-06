package cn.connie.community.core.impl;

import cn.connie.community.core.dao.CommunityCommentMapper;
import cn.connie.community.core.dao.CommunityMapper;
import cn.connie.community.core.entity.Community;
import cn.connie.community.core.entity.CommunityComment;
import cn.connie.community.core.entity.CommunityCommentExample;
import cn.connie.community.core.entity.CommunityExample;
import cn.connie.community.from.CommunityCommentFrom;
import cn.connie.community.from.CommunityFrom;
import cn.connie.community.service.CommunityService;
import cn.connie.community.to.CommunityCommentTO;
import cn.connie.community.to.CommunityTO;
import com.sgcai.commons.lang.utils.BeanConvertUtils;
import com.sgcai.commons.lang.utils.Dui1DuiStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private CommunityCommentMapper communityCommentMapper;

    @Override
    public void insert(CommunityFrom communityFrom) {
        Community community = BeanConvertUtils.convert(communityFrom, Community.class);
        community.setId(Dui1DuiStringUtils.generateUUID());
        community.setState(new Byte("1"));
        community.setCreated(new Date());
        community.setUpdated(new Date());
        communityMapper.insert(community);
    }

    @Override
    public void insertComment(CommunityCommentFrom communityCommentFrom) {
        CommunityComment communityComment = BeanConvertUtils.convert(communityCommentFrom, CommunityComment.class);
        communityComment.setId(Dui1DuiStringUtils.generateUUID());
        communityComment.setState(new Byte("1"));
        communityComment.setCreated(new Date());
        communityCommentMapper.insert(communityComment);
    }

    @Override
    public boolean existCommunity(String communityId) {
        CommunityExample example = new CommunityExample();
        example.createCriteria().andIdEqualTo(communityId);
        return communityMapper.countByExample(example) > 0;
    }

    @Override
    public boolean existCommunityComment(String communityId, String parentCommentId) {
        CommunityCommentExample example = new CommunityCommentExample();
        CommunityCommentExample.Criteria criteria = example.createCriteria();
        criteria.andCommunityIdEqualTo(communityId);
        criteria.andIdEqualTo(parentCommentId);
        return communityCommentMapper.countByExample(example) > 0;
    }

    @Override
    public List<CommunityTO> getCommunityList(int pageNo, int pageSize) {
        int offset = pageNo > 0 ? (pageNo - 1) * pageSize : 0;
        CommunityExample example = new CommunityExample();
        example.setLimit(pageSize);
        example.setOffset(offset);
        List<Community> communities = communityMapper.selectByExample(example);
        return BeanConvertUtils.convert(communities, CommunityTO.class);
    }

    @Override
    public long getCommunityCount() {
        CommunityExample example = new CommunityExample();
        return communityMapper.countByExample(example);
    }

    @Override
    public List<CommunityCommentTO> getCommentByCommunityId(String communityId) {
        CommunityCommentExample example = new CommunityCommentExample();
        CommunityCommentExample.Criteria criteria = example.createCriteria();
        criteria.andCommunityIdEqualTo(communityId);
        criteria.andParentCommentIdIsNull();
        List<CommunityComment> communityComments = communityCommentMapper.selectByExample(example);
        return BeanConvertUtils.convert(communityComments, CommunityCommentTO.class);
    }

    @Override
    public long getCommentByCommunityIdCount(String communityId) {
        CommunityCommentExample example = new CommunityCommentExample();
        CommunityCommentExample.Criteria criteria = example.createCriteria();
        criteria.andCommunityIdEqualTo(communityId);
        criteria.andParentCommentIdIsNull();
        return communityCommentMapper.countByExample(example);
    }

    @Override
    public List<CommunityCommentTO> getSubCommentByCommunityIdAndCommentId(String communityId, String parentCommentId) {
        CommunityCommentExample example = new CommunityCommentExample();
        CommunityCommentExample.Criteria criteria = example.createCriteria();
        criteria.andCommunityIdEqualTo(communityId);
        criteria.andParentCommentIdEqualTo(parentCommentId);
        List<CommunityComment> communityComments = communityCommentMapper.selectByExample(example);
        return BeanConvertUtils.convert(communityComments, CommunityCommentTO.class);
    }

    public long getSubCommentByCommunityIdAndCommentIdCount(String communityId, String parentCommentId) {
        CommunityCommentExample example = new CommunityCommentExample();
        CommunityCommentExample.Criteria criteria = example.createCriteria();
        criteria.andCommunityIdEqualTo(communityId);
        criteria.andParentCommentIdEqualTo(parentCommentId);
        return communityCommentMapper.countByExample(example);
    }

}
