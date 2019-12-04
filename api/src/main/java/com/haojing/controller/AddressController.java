package com.haojing.controller;

import com.haojing.bo.AddressBO;
import com.haojing.pojo.UserAddress;
import com.haojing.service.AddressService;
import com.haojing.utils.JSONresult;
import com.haojing.utils.MobileEmailUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "地址相关",tags = {"地址相关的接口"})
@RequestMapping("address")
@RestController
public class AddressController {
    /**
     * 用户在确认订单页面的时候，可以针对收货地址做如下的操作
     * 1 查询用户的收货地址列表
     * 2 新增收获地址
     * 3 删除收货地址
     * 4 修改收货地址
     */

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "查询收货地址列表", notes = "查询收货地址列表", httpMethod = "POST")
    @PostMapping("/list")
    public JSONresult list(
            @ApiParam(name = "userId",value = "用户id", required = true)
            @RequestParam String userId){
        if (StringUtils.isBlank(userId)){
            return JSONresult.errorMsg("");
        }
        List<UserAddress> list =addressService.queryAll(userId);
        return JSONresult.ok(list);
    }


    @ApiOperation(value = "用户新增地址", notes = "用户新增地址", httpMethod = "POST")
    @PostMapping("/add")
    public JSONresult add(@RequestBody AddressBO addressBO){
        JSONresult checkRes = checkAddress(addressBO);
        if (checkRes.getStatus() != 200){
            return checkRes;
        }
        addressService.addNewAddress(addressBO);
        return JSONresult.ok();
    }

    @ApiOperation(value = "用户修稿地址", notes = "用户修稿地址", httpMethod = "POST")
    @PostMapping("/update")
    public JSONresult update(@RequestBody AddressBO addressBO){
        if (StringUtils.isBlank(addressBO.getAddressId())){
            return JSONresult.errorMsg("修改地址失败，addressid不能为空");
        }
        JSONresult checkRes = checkAddress(addressBO);
        if (checkRes.getStatus() != 200){
            return checkRes;
        }
        addressService.updateUserAddress(addressBO);
        return JSONresult.ok();
    }
    @ApiOperation(value = "用户删除地址", notes = "用户删除地址", httpMethod = "POST")
    @PostMapping("/delete")
    public JSONresult delete(@RequestParam String userId, @RequestParam String addressId){
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)){
            return JSONresult.errorMsg("");
        }
        addressService.deleteUserAddress(userId,addressId);
        return JSONresult.ok();
    }

    @ApiOperation(value = "用户设置默认地址", notes = "用户设置默认地址", httpMethod = "POST")
    @PostMapping("/setDefalut")
    public JSONresult setDefault(@RequestParam String userId, @RequestParam String addressId){
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)){
            return JSONresult.errorMsg("");
        }
        addressService.updateUserAddressToBeDefault(userId,addressId);
        return JSONresult.ok();
    }
    private JSONresult checkAddress(AddressBO addressBO){
        String receiver = addressBO.getReceiver();
        if (StringUtils.isBlank(receiver)){
            return JSONresult.errorMsg("收货人不能为空");
        }
        String mobile = addressBO.getMobile();
        if (StringUtils.isBlank(mobile)){
            return JSONresult.errorMsg("手机号不能为空");
        }
        if (mobile.length() != 11){
            return JSONresult.errorMsg("手机号码长度不正确");
        }
        boolean isMobileOk = MobileEmailUtils.checkMobileIsOk(mobile);
        if (! isMobileOk){
            return JSONresult.errorMsg("手机号码格式不正确");
        }
        String province = addressBO.getProvince();
        String city = addressBO.getCity();
        String district = addressBO.getDistrict();
        String detail = addressBO.getDetail();
        if (StringUtils.isBlank(province) ||
            StringUtils.isBlank(city) ||
            StringUtils.isBlank(district) ||
            StringUtils.isBlank(detail)){
            return JSONresult.errorMsg("收货地址不能为空");
        }
        return JSONresult.ok();
    }
}
