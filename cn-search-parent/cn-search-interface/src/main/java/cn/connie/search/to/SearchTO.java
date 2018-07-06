package cn.connie.search.to;

import com.sgcai.commons.lang.base.BasicTO;

import java.util.List;

public class SearchTO extends BasicTO {
    private static final long serialVersionUID = 1L;
    /**
     * 总数据量
     */
    private long totalHits;
    /**
     * 返回查询数据
     */
    private List data;

    public long getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(long totalHits) {
        this.totalHits = totalHits;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
