package com.haojing.bo.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@ApiModel(value = "用户对象BO",description = "从客户端，由用户输入的数据封装到entity")
public class CenterUserBO {
    @NotBlank(message = "用户名不能为空")
    @Length(max = 12,message = "用户名的长度不能超过12位")
    @ApiModelProperty(value = "用户名",name = "username",required = true)
    private String username;

    @Length(min = 6,message = "密码长度不能低于6位")
    @ApiModelProperty(value = "密码",name = "password",required = true)
    private String password;
    @ApiModelProperty(value = "确认密码",name = "confirmPassword",required = false)
    private String confirmPassword;
    private String nickname;
    private String realname;

    private String mobile;
    private String email;
    private Integer sex;
    private Date birthday;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

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
