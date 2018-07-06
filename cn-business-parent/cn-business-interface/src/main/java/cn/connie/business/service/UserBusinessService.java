package cn.connie.business.service;

import cn.connie.business.from.ModifyPwdFrom;
import cn.connie.business.from.UserBFrom;
import cn.connie.business.to.LoginBTO;
import cn.connie.business.to.OnlineUserBTO;
import cn.connie.business.to.UserBTO;
import cn.connie.common.exception.CustomException;

public interface UserBusinessService {
    UserBTO getUserById(String id) throws CustomException;

    void register(UserBFrom userBFrom) throws CustomException;

    void modify(UserBFrom userBFrom) throws CustomException;

    LoginBTO login(UserBFrom userBFrom) throws CustomException;

    UserBTO getUserByAccessToken(String accessToken);

    void modifyPassword(ModifyPwdFrom modifyPwdFrom) throws CustomException;

    void logout(OnlineUserBTO onlineUser);
}
