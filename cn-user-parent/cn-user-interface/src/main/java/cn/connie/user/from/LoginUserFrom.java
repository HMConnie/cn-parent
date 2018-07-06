package cn.connie.user.from;

import com.sgcai.commons.lang.base.BasicTO;

public class LoginUserFrom extends BasicTO {
    private static final long serialVersionUID = 1L;
    private String loginIp;

    private String loginUser;

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }
}
