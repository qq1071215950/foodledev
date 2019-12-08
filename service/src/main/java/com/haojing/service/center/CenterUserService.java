package com.haojing.service.center;


import com.haojing.bo.center.CenterUserBO;
import com.haojing.pojo.Users;

/**
 * 用户接口服务
 */
public interface CenterUserService {

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    public Users queryUserInfo(String userId);

    /**
     * 用户修改个人信息
     * @param userId
     * @param centerUserBO
     */
    public Users updateUserInfo(String userId, CenterUserBO centerUserBO);

    /**
     * 保存用户头像url
     * @param userId
     * @param faceUrl
     * @return
     */
    public Users updateUserFace(String userId, String faceUrl);

}
