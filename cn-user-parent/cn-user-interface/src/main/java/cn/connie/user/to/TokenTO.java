package cn.connie.user.to;

import com.sgcai.commons.lang.base.BasicTO;

public class TokenTO extends BasicTO {

    private static final long serialVersionUID=1L;

    /**
     * 用户ID
     */
    private String userId;


    /**
     * 登录令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 过期时间
     */
    private long expiredIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken=accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken=refreshToken;
    }

    public long getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(long expiredIn) {
        this.expiredIn=expiredIn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId=userId;
    }

}
