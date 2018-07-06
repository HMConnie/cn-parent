package cn.connie.api.controller;

import cn.connie.business.service.SearchBusinessService;
import cn.connie.business.to.GoodsBTO;
import cn.connie.common.exception.CustomException;
import cn.connie.common.type.SearchBusinessType;
import cn.connie.common.utils.ResponseUtils;
import com.sgcai.commons.lang.base.CollectionTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Resource
    private SearchBusinessService searchBusinessService;

    @RequestMapping("/goods")
    @ResponseBody
    public String searchGoods(@RequestParam("name") String name, @RequestParam("type") String type
            , @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) throws CustomException {
        CollectionTO collectionTO = searchBusinessService.searchGoods(name, SearchBusinessType.valueOf(type), pageNo, pageSize);
        return ResponseUtils.toSuccessResponse(collectionTO);
    }
}
