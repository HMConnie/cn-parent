package cn.connie.business.core.impl;

import cn.connie.business.from.ModifyPwdFrom;
import cn.connie.business.from.UserBFrom;
import cn.connie.business.service.UserBusinessService;
import cn.connie.business.to.LoginBTO;
import cn.connie.business.to.OnlineUserBTO;
import cn.connie.business.to.UserBTO;
import cn.connie.common.exception.CustomException;
import cn.connie.common.utils.MiscUtils;
import cn.connie.user.from.LoginUserFrom;
import cn.connie.user.from.UserFrom;
import cn.connie.user.service.LoginLogService;
import cn.connie.user.service.TokenService;
import cn.connie.user.service.UserService;
import cn.connie.user.to.TokenTO;
import cn.connie.user.to.UserTO;
import com.sgcai.commons.lang.utils.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginLogService loginLogService;


    @Override
    public UserBTO getUserById(String id) throws CustomException {
        UserTO userTO = userService.findById(id);
        if (userTO == null) {
            throw new CustomException("user not find", "用户不存在");
        }
        return BeanConvertUtils.convert(userTO, UserBTO.class);
    }

    @Override
    public void register(UserBFrom userBFrom) throws CustomException {
        boolean exists = userService.userExists(userBFrom.getMobile());
        if (exists) {
            throw new CustomException("user exists", "用户已存在");
        }
        UserFrom userFrom = BeanConvertUtils.convert(userBFrom, UserFrom.class);
        userService.insert(userFrom);
    }

    @Override
    public void modify(UserBFrom userBFrom) throws CustomException {
        UserFrom userFrom = BeanConvertUtils.convert(userBFrom, UserFrom.class);
        UserTO userTO = userService.findById(userFrom.getId());
        if (userTO == null) {
            throw new CustomException("user not find", "用户不存在");
        }
        userService.update(userFrom);
    }

    @Override
    public LoginBTO login(UserBFrom userBFrom) throws CustomException {
        UserFrom userFrom = BeanConvertUtils.convert(userBFrom, UserFrom.class);
        UserTO userTO = userService.getUserByMobileWithPwd(userFrom);
        if (userTO == null) {
            throw new CustomException("user not find", "用户名或密码不存在");
        }

        /****加入登录日志*****/
        LoginUserFrom loginUserFrom = new LoginUserFrom();
        loginUserFrom.setLoginIp(userBFrom.getLoginIp());
        loginUserFrom.setLoginUser(userTO.getId());
        loginLogService.insert(loginUserFrom);

        TokenTO tokenTO = tokenService.createToken(userTO.getId());
        return BeanConvertUtils.convert(tokenTO, LoginBTO.class);
    }

    @Override
    public UserBTO getUserByAccessToken(String accessToken) {
        TokenTO tokenTO = tokenService.getToken(accessToken);
        if (tokenTO != null) {
            UserTO userTO = userService.findById(tokenTO.getUserId());
            return BeanConvertUtils.convert(userTO, UserBTO.class);
        }
        return null;
    }

    @Override
    public void modifyPassword(ModifyPwdFrom modifyPwdFrom) throws CustomException {
        UserTO userTO = userService.findById(modifyPwdFrom.getId());
        if (userTO == null) {
            throw new CustomException("user not find", "用户不存在");
        }
        if (!MiscUtils.matchPassword(modifyPwdFrom.getOldPassword(), userTO.getPassword())) {
            throw new CustomException("old password incorrect", "原始密码不正确");
        }

        String encodePassword = MiscUtils.encodePassword(modifyPwdFrom.getNewPassword());
        userTO.setPassword(encodePassword);

        UserFrom userFrom = BeanConvertUtils.convert(userTO, UserFrom.class);
        userService.update(userFrom);
    }

    @Override
    public void logout(OnlineUserBTO onlineUser) {
        tokenService.deleteToken(onlineUser.getAccessToken());
    }

}
