package com.haojing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;

@Controller
public class BaseController {
    public static final String FOODLE_SHOPCART = "shopcart";
    public static final Integer COMMENT_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

    /**
     * 用户头像上传位置
     * \\workspaces\\images\\foodle\\faces
     */
    public static final String IMAGE_USER_FACE_LOCATION = File.separator+"workspaces"+
            File.separator+"images"+
            File.separator+"foodle"+
            File.separator+"faces";

    public static final String IMAGE_SERVER_URL = "http://localhost:8088/foodle/faces";
    /**
     * 支付地址
     */
    public static final String paymentUrl = "http://payment.t.mukewang.com/foodie-payment";
    /**
     * 支付成功的回调地址
     */
    public static final String payReturnUrl = "http://localhost:8088/orders/notifyMerchantOrderPaid";
}
