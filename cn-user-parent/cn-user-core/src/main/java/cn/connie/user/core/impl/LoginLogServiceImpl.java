package cn.connie.user.core.impl;

import cn.connie.user.core.dao.LoginUserMapper;
import cn.connie.user.core.entity.LoginUser;
import cn.connie.user.from.LoginUserFrom;
import cn.connie.user.service.LoginLogService;
import com.sgcai.commons.lang.utils.BeanConvertUtils;
import com.sgcai.commons.lang.utils.Dui1DuiStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Override
    public void insert(LoginUserFrom loginUserFrom) {
        LoginUser loginUser = BeanConvertUtils.convert(loginUserFrom, LoginUser.class);
        loginUser.setId(Dui1DuiStringUtils.generateUUID());
        loginUser.setLoginTime(new Date());
        loginUserMapper.insert(loginUser);
    }
}
