package cn.connie.business.core.impl;

import cn.connie.business.from.CommunityBFrom;
import cn.connie.business.from.CommunityCommentBFrom;
import cn.connie.business.service.CommunityBusinessService;
import cn.connie.business.to.CommunityBTO;
import cn.connie.business.to.CommunityCommentBTO;
import cn.connie.common.exception.CustomException;
import cn.connie.community.from.CommunityCommentFrom;
import cn.connie.community.from.CommunityFrom;
import cn.connie.community.service.CommunityService;
import cn.connie.community.to.CommunityCommentTO;
import cn.connie.community.to.CommunityTO;
import com.sgcai.commons.lang.base.CollectionTO;
import com.sgcai.commons.lang.utils.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityBusinessServiceImpl implements CommunityBusinessService {

    @Autowired
    private CommunityService communityService;

    @Override
    public void saveCommunity(CommunityBFrom communityBFrom) {
        CommunityFrom communityFrom = BeanConvertUtils.convert(communityBFrom, CommunityFrom.class);
        communityService.insert(communityFrom);
    }

    @Override
    public CollectionTO<CommunityBTO> getCommunity(int pageNo, int pageSize) {
        List<CommunityTO> communityList = communityService.getCommunityList(pageNo, pageSize);
        long recordCnt = communityService.getCommunityCount();
        return new CollectionTO<>(BeanConvertUtils.convert(communityList, CommunityBTO.class), recordCnt, pageSize);
    }

    @Override
    public void saveCommunityComment(CommunityCommentBFrom communityCommentBFrom) throws CustomException {
        if (!communityService.existCommunity(communityCommentBFrom.getCommunityId())) {
            throw new CustomException("community comment not find", "这条朋友圈记录不存在");
        }
        CommunityCommentFrom communityCommentFrom = BeanConvertUtils.convert(communityCommentBFrom, CommunityCommentFrom.class);
        communityService.insertComment(communityCommentFrom);
    }

    @Override
    public void saveCommunityCommentParentComment(CommunityCommentBFrom communityCommentBFrom) throws CustomException {
        if (!communityService.existCommunityComment(communityCommentBFrom.getCommunityId(), communityCommentBFrom.getParentCommentId())) {
            throw new CustomException("community comment not find", "这条朋友圈评论记录不存在");
        }
        CommunityCommentFrom communityCommentFrom = BeanConvertUtils.convert(communityCommentBFrom, CommunityCommentFrom.class);
        communityService.insertComment(communityCommentFrom);
    }

    @Override
    public CollectionTO<CommunityCommentBTO> getCommunityCommentList(String communityId, int pageNo, int pageSize) throws CustomException {
        List<CommunityCommentTO> communityCommentTOS = communityService.getCommentByCommunityId(communityId);
        long recordCnt = communityService.getCommentByCommunityIdCount(communityId);
        return new CollectionTO<>(BeanConvertUtils.convert(communityCommentTOS, CommunityCommentBTO.class), recordCnt, pageSize);
    }

    @Override
    public CollectionTO<CommunityCommentBTO> getSubCommentList(String communityId, String parentCommentId, int pageNo, int pageSize) {
        List<CommunityCommentTO> communityCommentTOS = communityService.getSubCommentByCommunityIdAndCommentId(communityId, parentCommentId);
        long recordCnt = communityService.getSubCommentByCommunityIdAndCommentIdCount(communityId, parentCommentId);
        return new CollectionTO<>(BeanConvertUtils.convert(communityCommentTOS, CommunityCommentBTO.class), recordCnt, pageSize);
    }
}
