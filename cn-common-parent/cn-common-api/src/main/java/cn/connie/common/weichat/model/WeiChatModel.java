package cn.connie.common.weichat.model;

import com.sgcai.commons.lang.base.BasicTO;

public class WeiChatModel extends BasicTO {
    private String merchants_no;
    private String total_price;
    private String ip;
    private String redirect_url;
    private String trade_order_no;
    private String time_expire;
    private String body;

    public String getMerchants_no() {
        return merchants_no;
    }

    public void setMerchants_no(String merchants_no) {
        this.merchants_no = merchants_no;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public String getTrade_order_no() {
        return trade_order_no;
    }

    public void setTrade_order_no(String trade_order_no) {
        this.trade_order_no = trade_order_no;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
