package com.haojing.service;

import com.haojing.bo.AddressBO;
import com.haojing.pojo.UserAddress;

import java.util.List;

/**
 * 地址接口服务
 */
public interface AddressService {

    /**
     * 根据用户id查询收货地址列表
     * @param userId
     * @return
     */
    public List<UserAddress> queryAll(String userId);

    public void addNewAddress(AddressBO addressBO);

    public void updateUserAddress(AddressBO addressBO);

    public void deleteUserAddress(String userId, String addressId);

    public void updateUserAddressToBeDefault(String userId, String addressId);

    public UserAddress queryUserAddress(String userId, String addressId);
}
