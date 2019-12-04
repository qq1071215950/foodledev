package com.haojing.controller;

import com.haojing.bo.UserBO;
import com.haojing.pojo.Users;
import com.haojing.service.UserService;
import com.haojing.utils.CookieUtils;
import com.haojing.utils.JSONresult;
import com.haojing.utils.JsonUtils;
import com.haojing.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "注册登录",tags = {"用户注册登录相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {

    private final static Logger logger = LoggerFactory.getLogger(PassportController.class);
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "POST")
    @GetMapping("usernameIsExit")
    public JSONresult usernameIsExit(@RequestParam String username) {
        // 1 判断用户名不能为空
        if (StringUtils.isBlank(username)) {
            return JSONresult.errorMsg("用户不能为空");
        }
        // 用户名是否存在
        boolean isExit = userService.queryUsernameIsExit(username);
        if (isExit) {
            return JSONresult.errorMsg("用户名已经存在");
        }
        return JSONresult.ok();
    }

    /**
     * 用户注册
     *
     * @param userBO
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("regist")
    public JSONresult regist(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        // 用户名和密码不能为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPwd)) {
            return JSONresult.errorMsg("用户名或者密码不呢为空");
        }
        // 用户名是否存在
        boolean isExit = userService.queryUsernameIsExit(username);
        if (isExit) {
            return JSONresult.errorMsg("用户名已经存在");
        }
        // 密码长度不能为小于6
        if (password.length() < 6) {
            return JSONresult.errorMsg("密码长度不能小于6");
        }
        // 两次密码是否为一直
        if (!password.equals(confirmPwd)) {
            return JSONresult.errorMsg("两次密码不一致");
        }
        // todo 生成用户token 存入redis会话中
        // todo 同步购车数据
        // 实现注册
        Users userResult = userService.createUser(userBO);
        userResult = setNullProperty(userResult);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);

        return JSONresult.ok();
    }


    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("login")
    public JSONresult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        // 用户名和密码不能为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return JSONresult.errorMsg("用户名或者密码不呢为空");
        }
        Users userResult = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));
        if (userResult == null) {
            return JSONresult.errorMsg("用户名或者密码不正确");
        }
        // todo 生成用户token 存入redis会话中
        // todo 同步购车数据
        userResult = setNullProperty(userResult);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);
        return JSONresult.ok(userResult);
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

    @ApiOperation(value = "用户退出", notes = "用户推出", httpMethod = "POST")
    @PostMapping("logout")
    public JSONresult logout(@RequestParam String userId,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        // 清楚用户相关的信息
        CookieUtils.deleteCookie(request, response, "user");
        // todo 用户推出登录，需要清空购物车
        // todo 分布式会话中，需要清空用户数据
        return JSONresult.ok();
    }


}
