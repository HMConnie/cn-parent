package cn.connie.file.from;

import com.sgcai.commons.lang.base.BasicTO;

public class TFileFrom extends BasicTO {
    private static final long serialVersionUID = 1L;

    private String md5;

    private String url;


    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
