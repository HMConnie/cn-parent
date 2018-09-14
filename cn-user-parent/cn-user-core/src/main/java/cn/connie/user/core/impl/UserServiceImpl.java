package cn.connie.user.core.impl;

import cn.connie.common.utils.EmojiUtil;
import cn.connie.common.utils.MiscUtils;
import cn.connie.user.core.dao.UserMapper;
import cn.connie.user.core.entity.User;
import cn.connie.user.core.entity.UserExample;
import cn.connie.user.from.UserFrom;
import cn.connie.user.service.UserService;
import cn.connie.user.to.UserTO;
import com.sgcai.commons.lang.utils.BeanConvertUtils;
import com.sgcai.commons.lang.utils.Dui1DuiStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service层一般不做业务判断，只做数据的增删改查
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserTO findById(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        String emojiConverterToAlias = EmojiUtil.emojiConverterDecode(user.getNickname());
        user.setNickname(emojiConverterToAlias);
        return BeanConvertUtils.convert(user, UserTO.class);
    }

    @Override
    public void insert(UserFrom userFrom) {
        User user = BeanConvertUtils.convert(userFrom, User.class);
        user.setId(Dui1DuiStringUtils.generateUUID());
        String encodePwd = MiscUtils.encodePassword(userFrom.getPassword());
        user.setPassword(encodePwd);
        user.setCreated(new Date());
        user.setUpdated(new Date());

        if (StringUtils.isNotBlank(user.getNickname())) {
            String emojiConverterUnicodeStr = EmojiUtil.emojiConverterEncode(user.getNickname());
            user.setNickname(emojiConverterUnicodeStr);
        }
        userMapper.insert(user);
    }

    @Override
    public boolean userExists(String mobile) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(mobile);
        List<User> users = userMapper.selectByExample(example);
        return !users.isEmpty();
    }

    @Override
    public void update(UserFrom userFrom) {
        User user = userMapper.selectByPrimaryKey(userFrom.getId());
        if (userFrom.getAge() != null) {
            user.setAge(userFrom.getAge());
        }
        if (userFrom.getGender() != null) {
            user.setGender(userFrom.getGender());
        }
        if (StringUtils.isNotBlank(userFrom.getAddress())) {
            user.setAddress(userFrom.getAddress());
        }
        if (StringUtils.isNotBlank(userFrom.getNickname())) {
            String emojiConverterUnicodeStr = EmojiUtil.emojiConverterEncode(userFrom.getNickname());
            user.setNickname(emojiConverterUnicodeStr);
        }
        if (StringUtils.isNotBlank(userFrom.getHeadPortrait())) {
            user.setHeadPortrait(userFrom.getHeadPortrait());
        }
        if (userFrom.getBirthday() != null) {
            user.setBirthday(userFrom.getBirthday());
        }
        if (StringUtils.isNotBlank(userFrom.getSignature())) {
            user.setSignature(userFrom.getSignature());
        }
        user.setUpdated(new Date());
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public UserTO getUserByMobileWithPwd(UserFrom userFrom) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(userFrom.getMobile());
        String encodePwd = MiscUtils.encodePassword(userFrom.getPassword());
        criteria.andPasswordEqualTo(encodePwd);
        List<User> users = userMapper.selectByExample(example);
        if (!users.isEmpty()) {
            User user = users.get(0);
            String emojiConverterToAlias = EmojiUtil.emojiConverterDecode(user.getNickname());
            user.setNickname(emojiConverterToAlias);
            return BeanConvertUtils.convert(user, UserTO.class);
        }
        return null;
    }
}
