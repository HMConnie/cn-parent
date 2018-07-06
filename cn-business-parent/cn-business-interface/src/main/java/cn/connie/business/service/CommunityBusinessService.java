package cn.connie.business.service;

import cn.connie.business.from.CommunityBFrom;
import cn.connie.business.from.CommunityCommentBFrom;
import cn.connie.business.to.CommunityBTO;
import cn.connie.business.to.CommunityCommentBTO;
import cn.connie.common.exception.CustomException;
import com.sgcai.commons.lang.base.CollectionTO;

public interface CommunityBusinessService {

    void saveCommunity(CommunityBFrom communityBFrom);

    CollectionTO<CommunityBTO> getCommunity(int pageNo, int pageSize);

    void saveCommunityComment(CommunityCommentBFrom communityCommentBFrom) throws CustomException;

    void saveCommunityCommentParentComment(CommunityCommentBFrom communityCommentBFrom) throws CustomException;

    CollectionTO<CommunityCommentBTO> getCommunityCommentList(String communityId, int pageNo, int pageSize) throws CustomException;

    CollectionTO<CommunityCommentBTO> getSubCommentList(String communityId, String parentCommentId, int pageNo, int pageSize);
}
