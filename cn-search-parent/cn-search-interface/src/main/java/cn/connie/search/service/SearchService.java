package cn.connie.search.service;

import cn.connie.common.type.SearchBusinessType;
import cn.connie.search.criteria.SearchCriteria;
import cn.connie.search.from.SearchFrom;
import cn.connie.search.to.SearchTO;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

public interface SearchService {
    /**
     * 查询
     */
    SearchTO query(SearchCriteria searchCriteria) throws UnknownHostException;


    /**
     * 添加
     */
    void insertOrUpdate(SearchFrom searchEntity) throws IOException;


    /**
     * 删除
     */
    void delete(String id, SearchBusinessType searchBusinessType) throws UnknownHostException;

}
