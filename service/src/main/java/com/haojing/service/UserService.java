package com.haojing.service;


import com.haojing.bo.UserBO;
import com.haojing.pojo.Users;

/**
 * 用户接口服务
 */
public interface UserService {

    /**
     *  判断用户名是否存在
     * @param username
     * @return
     */
    public boolean queryUsernameIsExit(String username);

    /**
     * 创建用户
     * @return
     */
    public Users createUser(UserBO userBO);

    /**
     * 检索用户名和密码用于用户登录
     * @param username
     * @param password
     * @return
     */
    public Users queryUserForLogin(String username, String password);
}
