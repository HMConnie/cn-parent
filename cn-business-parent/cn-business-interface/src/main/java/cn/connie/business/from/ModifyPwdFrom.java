package cn.connie.business.from;

import com.sgcai.commons.lang.base.BasicTO;

public class ModifyPwdFrom extends BasicTO {

    private static final long serialVersionUID = 1L;
    private String id;
    private String oldPassword;
    private String newPassword;
    private String ip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
