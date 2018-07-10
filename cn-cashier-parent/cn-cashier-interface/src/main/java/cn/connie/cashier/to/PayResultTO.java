package cn.connie.cashier.to;

import com.sgcai.commons.lang.base.BasicTO;

public class PayResultTO extends BasicTO {
    /**
     * 支付结果
     */
    private boolean result;

    /**
     * 第三方订单编号
     */
    private String thirdtyOrderNo;


    private String redirectURL;

    /**
     * APP客户端需要参数
     */
    private String clientParam;

    public String getClientParam() {
        return clientParam;
    }

    public void setClientParam(String clientParam) {
        this.clientParam = clientParam;
    }

    public String getRedirectURL() {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getThirdtyOrderNo() {
        return thirdtyOrderNo;
    }

    public void setThirdtyOrderNo(String thirdtyOrderNo) {
        this.thirdtyOrderNo = thirdtyOrderNo;
    }

}
