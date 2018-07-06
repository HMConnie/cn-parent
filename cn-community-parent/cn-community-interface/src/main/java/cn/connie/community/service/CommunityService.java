package cn.connie.community.service;

import cn.connie.community.from.CommunityCommentFrom;
import cn.connie.community.from.CommunityFrom;
import cn.connie.community.to.CommunityCommentTO;
import cn.connie.community.to.CommunityTO;

import java.util.List;

public interface CommunityService {
    void insert(CommunityFrom communityFrom);

    void insertComment(CommunityCommentFrom communityCommentFrom);

    boolean existCommunity(String communityId);

    boolean existCommunityComment(String communityId, String parentCommentId);

    List<CommunityTO> getCommunityList(int pageNo, int pageSize);

    long getCommunityCount();

    List<CommunityCommentTO> getCommentByCommunityId(String communityId);

    long getCommentByCommunityIdCount(String communityId);

    List<CommunityCommentTO> getSubCommentByCommunityIdAndCommentId(String communityId, String parentCommentId);

    long getSubCommentByCommunityIdAndCommentIdCount(String communityId, String parentCommentId);

}
