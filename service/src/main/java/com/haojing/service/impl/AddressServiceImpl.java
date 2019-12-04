package com.haojing.service.impl;

import com.haojing.bo.AddressBO;
import com.haojing.enums.YesOrNo;
import com.haojing.mapper.UserAddressMapper;
import com.haojing.pojo.UserAddress;
import com.haojing.service.AddressService;
import com.haojing.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> queryAll(String userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        return userAddressMapper.select(userAddress);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addNewAddress(AddressBO addressBO) {
        Integer isDefault = 0;
        List<UserAddress> list = this.queryAll(addressBO.getUserId());
        if (list == null || list.isEmpty() || list.size() == 0){
            isDefault = 1;
        }
        String addressId = sid.nextShort();
        UserAddress address = new UserAddress();
        BeanUtils.copyProperties(addressBO,address);
        address.setId(addressId);
        address.setIsDefault(isDefault);
        address.setCreatedTime(new Date());
        address.setUpdatedTime(new Date());
        userAddressMapper.insert(address);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserAddress(AddressBO addressBO) {
        String addressId = addressBO.getAddressId();
        UserAddress address = new UserAddress();
        BeanUtils.copyProperties(addressBO,address);
        address.setId(addressId);
        address.setUpdatedTime(new Date());
        userAddressMapper.updateByPrimaryKeySelective(address);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUserAddress(String userId, String addressId) {
        UserAddress address = new UserAddress();
        address.setUserId(userId);
        address.setId(addressId);
        userAddressMapper.delete(address);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void updateUserAddressToBeDefault(String userId, String addressId) {
        UserAddress queryAddress = new UserAddress();
        queryAddress.setUserId(userId);
        queryAddress.setIsDefault(YesOrNo.YES.type);
        List<UserAddress> list=userAddressMapper.select(queryAddress);
        for (UserAddress userAddress : list){
            userAddress.setIsDefault(YesOrNo.NO.type);
            userAddressMapper.updateByPrimaryKeySelective(userAddress);
        }
        UserAddress defaultAddress = new UserAddress();
        defaultAddress.setId(addressId);
        defaultAddress.setUserId(userId);
        defaultAddress.setIsDefault(YesOrNo.YES.type);
        userAddressMapper.updateByPrimaryKeySelective(defaultAddress);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserAddress queryUserAddress(String userId, String addressId) {
        UserAddress simgleAddress = new UserAddress();
        simgleAddress.setId(addressId);
        simgleAddress.setUserId(userId);
        return userAddressMapper.selectOne(simgleAddress);
    }
}
