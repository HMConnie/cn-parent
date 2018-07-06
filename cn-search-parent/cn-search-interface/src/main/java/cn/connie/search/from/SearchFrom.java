package cn.connie.search.from;

import cn.connie.common.type.SearchBusinessType;
import com.alibaba.fastjson.JSONObject;
import com.sgcai.commons.lang.base.BasicTO;

public class SearchFrom extends BasicTO {

    private static final long serialVersionUID = 1L;
    /**
     * 业务数据id，需要是唯一的uuid，不可重复
     */
    private String id;

    /**
     * 搜索内容
     */
    private String key;

    /**
     * 搜索业务类型
     */
    private SearchBusinessType searchBusinessType;

    /**
     * 实体对象
     */
    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = JSONObject.toJSONString(object);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SearchBusinessType getSearchBusinessType() {
        return searchBusinessType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setSearchBusinessType(SearchBusinessType searchBusinessType) {
        this.searchBusinessType = searchBusinessType;
    }
}
