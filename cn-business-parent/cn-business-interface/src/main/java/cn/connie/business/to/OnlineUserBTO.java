package cn.connie.business.to;

import com.sgcai.commons.lang.base.BasicTO;
import org.apache.commons.lang.StringUtils;

import java.security.Principal;

public class OnlineUserBTO extends BasicTO implements Principal {

    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String portrait;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * accessToken
     */
    private String accessToken;

    /**
     * 当前用户IP
     */
    private String ip;


    public boolean isLogined() {
        return StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(accessToken);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getName() {
        return "onlineUser";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
