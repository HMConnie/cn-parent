package cn.connie.goods.to;

import cn.connie.common.type.CashierBusinessType;
import cn.connie.common.type.ClientType;
import com.sgcai.commons.lang.base.BasicTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class EncryptionParameter extends BasicTO {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端类型
     */
    private ClientType clientType;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 订单描述
     */
    private String description;

    /**
     * 业务订单号
     */
    private String businessOrderNo;

    /**
     * 业务类型
     */
    private CashierBusinessType businessType;


    /**
     * 失效时间
     */
    private Date expiryTime;

    public BigDecimal getExpiryTimeMinute() {
        BigDecimal bd = new BigDecimal(expiryTime.getTime() - System.currentTimeMillis());
        bd = bd.setScale(0, RoundingMode.DOWN);
        bd = bd.divide(new BigDecimal(1000), RoundingMode.DOWN);
        bd = bd.divide(new BigDecimal(60), RoundingMode.DOWN);
        return bd;
    }



    /**
     * 原交易订单号
     */
    private String originOrderNo;

    /**
     * 跳转地址
     */
    private String redirectUrl;


    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusinessOrderNo() {
        return businessOrderNo;
    }

    public void setBusinessOrderNo(String businessOrderNo) {
        this.businessOrderNo = businessOrderNo;
    }


    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public CashierBusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(CashierBusinessType businessType) {
        this.businessType = businessType;
    }


    public String getOriginOrderNo() {
        return originOrderNo;
    }

    public void setOriginOrderNo(String originOrderNo) {
        this.originOrderNo = originOrderNo;
    }


    public ClientType getClientType() {
        if (clientType == null){
            clientType = ClientType.MASTER_PC;
        }
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
}
