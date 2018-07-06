package cn.connie.api.controller;

import cn.connie.business.from.CommunityBFrom;
import cn.connie.business.from.CommunityCommentBFrom;
import cn.connie.business.service.CommunityBusinessService;
import cn.connie.business.to.CommunityBTO;
import cn.connie.business.to.CommunityCommentBTO;
import cn.connie.business.to.OnlineUserBTO;
import cn.connie.common.annotation.NeedLogin;
import cn.connie.common.exception.CustomException;
import cn.connie.common.utils.ResponseUtils;
import com.sgcai.commons.lang.base.CollectionTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/community")
public class CommunityController {

    @Resource
    private CommunityBusinessService communityBusinessService;

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    @ResponseBody
    @NeedLogin
    public void publish(OnlineUserBTO onlineUserBTO,
                        @RequestParam("images") String images,
                        @RequestParam("content") String content) {
        CommunityBFrom communityBFrom = new CommunityBFrom();
        communityBFrom.setContent(content);
        communityBFrom.setImages(images);
        communityBFrom.setUserId(onlineUserBTO.getUserId());
        communityBusinessService.saveCommunity(communityBFrom);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public String getCommunityList(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        CollectionTO<CommunityBTO> collectionTO = communityBusinessService.getCommunity(pageNo, pageSize);
        return ResponseUtils.toSuccessResponse(collectionTO);
    }

    @RequestMapping(value = "/comment/list", method = RequestMethod.GET)
    @ResponseBody
    public String getCommunityCommentList(@RequestParam("communityId") String communityId,
                                          @RequestParam("pageNo") int pageNo,
                                          @RequestParam("pageSize") int pageSize) throws CustomException {
        CollectionTO<CommunityCommentBTO> collectionTO = communityBusinessService.getCommunityCommentList(communityId, pageNo, pageSize);
        return ResponseUtils.toSuccessResponse(collectionTO);
    }

    @RequestMapping(value = "/commentParentComment/list", method = RequestMethod.GET)
    @ResponseBody
    public String getSubCommentList(@RequestParam("communityId") String communityId,
                                    @RequestParam("parentCommentId") String parentCommentId,
                                    @RequestParam("pageNo") int pageNo,
                                    @RequestParam("pageSize") int pageSize) throws CustomException {
        CollectionTO<CommunityCommentBTO> collectionTO = communityBusinessService.getSubCommentList(communityId, parentCommentId, pageNo, pageSize);
        return ResponseUtils.toSuccessResponse(collectionTO);
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    @NeedLogin
    public void commentCommunity(OnlineUserBTO onlineUserBTO,
                                 @RequestParam("communityId") String communityId,
                                 @RequestParam("content") String content) throws CustomException {
        CommunityCommentBFrom communityCommentBFrom = new CommunityCommentBFrom();
        communityCommentBFrom.setCommunityId(communityId);
        communityCommentBFrom.setUserId(onlineUserBTO.getUserId());
        communityCommentBFrom.setContent(content);
        communityBusinessService.saveCommunityComment(communityCommentBFrom);
    }

    @RequestMapping(value = "/commentParentComment", method = RequestMethod.POST)
    @ResponseBody
    @NeedLogin
    public void commentParentComment(OnlineUserBTO onlineUserBTO,
                                     @RequestParam("communityId") String communityId,
                                     @RequestParam("parentCommentId") String parentCommentId,
                                     @RequestParam("content") String content) throws CustomException {
        CommunityCommentBFrom communityCommentBFrom = new CommunityCommentBFrom();
        communityCommentBFrom.setCommunityId(communityId);
        communityCommentBFrom.setParentCommentId(parentCommentId);
        communityCommentBFrom.setContent(content);
        communityCommentBFrom.setUserId(onlineUserBTO.getUserId());
        communityBusinessService.saveCommunityCommentParentComment(communityCommentBFrom);
    }
}
