package com.haojing.center;

import com.haojing.pojo.Users;
import com.haojing.service.center.CenterUserService;
import com.haojing.utils.JSONresult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户中心",tags = {"用户中心相关接口"})
@RestController
@RequestMapping("center")
public class CenterController {

    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息",httpMethod = "GET")
    @GetMapping("userInfo")
    public JSONresult userInfo(@RequestParam String userId){
        Users user = centerUserService.queryUserInfo(userId);
        return JSONresult.ok(user);
    }
}
