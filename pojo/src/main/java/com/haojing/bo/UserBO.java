package com.haojing.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户对象BO",description = "从客户端，由用户输入的数据封装到entity")
public class UserBO {
    @ApiModelProperty(value = "用户名",name = "username",required = true)
    private String username;
    @ApiModelProperty(value = "密码",name = "password",required = true)
    private String password;
    @ApiModelProperty(value = "确认密码",name = "confirmPassword",required = false)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
