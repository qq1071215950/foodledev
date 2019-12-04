package com.haojing.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 自定义相应数据结果
 */
public class JSONresult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    @JsonIgnore
    private String ok;	// 不使用

    public static JSONresult build(Integer status, String msg, Object data) {
        return new JSONresult(status, msg, data);
    }

    public static JSONresult build(Integer status, String msg, Object data, String ok) {
        return new JSONresult(status, msg, data, ok);
    }

    public static JSONresult ok(Object data) {
        return new JSONresult(data);
    }

    public static JSONresult ok() {
        return new JSONresult(null);
    }

    public static JSONresult errorMsg(String msg) {
        return new JSONresult(500, msg, null);
    }

    public static JSONresult errorMap(Object data) {
        return new JSONresult(501, "error", data);
    }

    public static JSONresult errorTokenMsg(String msg) {
        return new JSONresult(502, msg, null);
    }

    public static JSONresult errorException(String msg) {
        return new JSONresult(555, msg, null);
    }

    public static JSONresult errorUserQQ(String msg) {
        return new JSONresult(556, msg, null);
    }

    public JSONresult() {

    }

    public JSONresult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public JSONresult(Integer status, String msg, Object data, String ok) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ok = ok;
    }

    public JSONresult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

}
