package cn.connie.business.service;

import cn.connie.common.exception.CustomException;
import cn.connie.common.type.SearchBusinessType;
import com.sgcai.commons.lang.base.CollectionTO;

public interface SearchBusinessService {

    CollectionTO searchGoods(String name, SearchBusinessType searchBusinessType, int pageNo, int pageSize) throws CustomException;
}
