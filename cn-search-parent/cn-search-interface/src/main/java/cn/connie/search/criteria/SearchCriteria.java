package cn.connie.search.criteria;

import cn.connie.common.type.SearchBusinessType;
import com.sgcai.commons.lang.base.BasicCriteria;

/**
 * 查询请求实体
 */
public class SearchCriteria extends BasicCriteria {


  /**
   * 不可空:搜索关键字
   */
  private String key;
  /**
   * 不可空：搜索引擎业务类型
   */
  private SearchBusinessType searchBusinessType;
  /**
   * 可空：分页功能参数,偏移量
   */
  private int offset = 0;
  /**
   * 可空：分页功能参数,数据条数
   */
  private int size = 10;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public SearchBusinessType getSearchBusinessType() {
    return searchBusinessType;
  }

  public void setSearchBusinessType(SearchBusinessType searchBusinessType) {
    this.searchBusinessType = searchBusinessType;
  }
}
