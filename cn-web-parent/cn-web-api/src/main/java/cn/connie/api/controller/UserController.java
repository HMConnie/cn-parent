package cn.connie.api.controller;

import cn.connie.business.from.ModifyPwdFrom;
import cn.connie.business.from.UserBFrom;
import cn.connie.business.service.UserBusinessService;
import cn.connie.business.to.LoginBTO;
import cn.connie.business.to.OnlineUserBTO;
import cn.connie.business.to.UserBTO;
import cn.connie.common.annotation.NeedLogin;
import cn.connie.common.exception.CustomException;
import cn.connie.common.model.JsonModel;
import cn.connie.common.type.Gender;
import cn.connie.common.utils.DateUtils;
import cn.connie.common.utils.ResponseUtils;
import com.sgcai.commons.lang.utils.WebUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserBusinessService userBusinessService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(@RequestParam("id") String id) throws CustomException {
        UserBTO userBTO = userBusinessService.getUserById(id);
        return ResponseUtils.toSuccessResponse(userBTO);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public void register(@RequestParam("mobile") String mobile,
                         @RequestParam("password") String password) throws CustomException {
        UserBFrom userFrom = new UserBFrom();
        userFrom.setMobile(mobile);
        userFrom.setPassword(password);
        userBusinessService.register(userFrom);
    }


    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    @NeedLogin
    public void modify(OnlineUserBTO onlineUserBTO,
                       @RequestParam(value = "nickname", required = false) String nickname,
                       @RequestParam(value = "headPortrait", required = false) String headPortrait,
                       @RequestParam(value = "gender", required = false) String gender,
                       @RequestParam(value = "age", required = false) Integer age,
                       @RequestParam(value = "address", required = false) String address,
                       @RequestParam(value = "birthday", required = false) String birthday,
                       @RequestParam(value = "signature", required = false) String signature) throws CustomException {
        UserBFrom userFrom = new UserBFrom();
        userFrom.setId(onlineUserBTO.getUserId());
        userFrom.setHeadPortrait(headPortrait);
        userFrom.setNickname(nickname);
        userFrom.setAge(age);
        userFrom.setAddress(address);
        if (!StringUtils.isBlank(gender)) {
            userFrom.setGender(Gender.valueOf(gender));
        }
        userFrom.setBirthday(DateUtils.str2Date(birthday));
        userFrom.setSignature(signature);
        userBusinessService.modify(userFrom);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam(value = "mobile") String mobile,
                        @RequestParam(value = "password") String password,
                        HttpServletRequest request) throws CustomException {
        String ip = WebUtils.getIp(request);
        UserBFrom userFrom = new UserBFrom();
        userFrom.setMobile(mobile);
        userFrom.setPassword(password);
        userFrom.setLoginIp(ip);
        LoginBTO loginBTO = userBusinessService.login(userFrom);
        return ResponseUtils.toSuccessResponse(loginBTO);
    }

    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    @ResponseBody
    @NeedLogin
    public void modifyPassword(OnlineUserBTO onlineUserBTO,
                               @RequestParam(value = "oldPwd") String oldPwd,
                               @RequestParam(value = "newPwd") String newPwd) throws CustomException {
        ModifyPwdFrom modifyPwdFrom = new ModifyPwdFrom();
        modifyPwdFrom.setId(onlineUserBTO.getUserId());
        modifyPwdFrom.setOldPassword(oldPwd);
        modifyPwdFrom.setNewPassword(newPwd);
        modifyPwdFrom.setIp(onlineUserBTO.getIp());
        userBusinessService.modifyPassword(modifyPwdFrom);
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    @ResponseBody
    @NeedLogin
    public String useInfo(OnlineUserBTO onlineUserBTO) throws CustomException {
        UserBTO userBTO = userBusinessService.getUserById(onlineUserBTO.getUserId());
        return ResponseUtils.toSuccessResponse(userBTO);
    }


    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @NeedLogin
    public void loginOut(OnlineUserBTO onlineUser) {
        userBusinessService.logout(onlineUser);
    }


    @RequestMapping(value = "/applicationJson", method = RequestMethod.POST)
    @ResponseBody
    public String applicationJson(@RequestBody JsonModel jsonModel) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", jsonModel.getName());
        map.put("age", jsonModel.getAge());
        return ResponseUtils.toSuccessResponse(map);
    }

    @RequestMapping(value = "/formUrlencoded", method = RequestMethod.POST)
    @ResponseBody
    public String formUrlencoded(@RequestParam("name") String name, @RequestParam("age") int age) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        return ResponseUtils.toSuccessResponse(map);
    }
}
