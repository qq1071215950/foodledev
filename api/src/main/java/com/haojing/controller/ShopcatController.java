package com.haojing.controller;

import com.haojing.bo.ShopcartBO;
import com.haojing.utils.JSONresult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "购物车接口",tags = {"购物车相关接口"})
@RequestMapping("shopcart")
@RestController
public class ShopcatController {

    @ApiOperation(value = "加入购物车", notes = "加入购物车", httpMethod = "POST")
    @PostMapping("/add")
    public JSONresult add(@RequestParam String userId,
                      @RequestBody ShopcartBO shopcartBO,
                      HttpServletRequest request,
                      HttpServletResponse response){
        if (StringUtils.isBlank(userId)){
            return JSONresult.errorMsg("");
        }
        System.out.println(shopcartBO);
        // todo 前端用户在登录的状态下，添加商品到购物车，会同时在后端同步到redis中
        return JSONresult.ok();
    }


    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("/del")
    public JSONresult del(@RequestParam String userId,
                          @RequestParam String itemSpecId,
                          HttpServletRequest request,
                          HttpServletResponse response){
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)){
            return JSONresult.errorMsg("参数不能为空");
        }
        // todo 用户在登录状态下，删除购物车中商品，需要同步删除后端中的数据
        return JSONresult.ok();
    }
}
