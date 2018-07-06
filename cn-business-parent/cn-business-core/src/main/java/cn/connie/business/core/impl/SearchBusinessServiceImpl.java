package cn.connie.business.core.impl;

import cn.connie.business.service.SearchBusinessService;
import cn.connie.common.exception.CustomException;
import cn.connie.common.type.SearchBusinessType;
import cn.connie.search.criteria.SearchCriteria;
import cn.connie.search.service.SearchService;
import cn.connie.search.to.SearchTO;
import com.sgcai.commons.lang.base.CollectionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

@Service
public class SearchBusinessServiceImpl implements SearchBusinessService {

    @Autowired
    private SearchService searchService;

    @Override
    public CollectionTO searchGoods(String name, SearchBusinessType searchBusinessType, int pageNo, int pageSize) throws CustomException {
        try {
            SearchCriteria searchCriteria = new SearchCriteria();
            searchCriteria.setKey(name);
            searchCriteria.setSearchBusinessType(searchBusinessType);
            searchCriteria.setPageNo(pageNo);
            int offset = pageNo > 1 ? (pageNo - 1) * pageSize : 0;
            searchCriteria.setOffset(offset);
            SearchTO searchTO = searchService.query(searchCriteria);
            return new CollectionTO<>(searchTO.getData(), searchTO.getTotalHits(), pageSize);
        } catch (UnknownHostException e) {
            throw new CustomException("search query failed", "查询失败");
        }
    }
}
