package cn.connie.user.service;

import cn.connie.user.from.UserFrom;
import cn.connie.user.to.UserTO;

public interface UserService {

    UserTO findById(String id);

    void insert(UserFrom userFrom);

    boolean userExists(String mobile);

    void update(UserFrom userFrom);

    UserTO getUserByMobileWithPwd(UserFrom userFrom);
}
