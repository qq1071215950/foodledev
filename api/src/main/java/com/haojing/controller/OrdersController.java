package com.haojing.controller;

import com.haojing.bo.SubmitOrderBO;
import com.haojing.enums.OrderStatusEnum;
import com.haojing.enums.PayMethod;
import com.haojing.service.OrderService;
import com.haojing.utils.JSONresult;
import com.haojing.vo.MerchantOrdersVO;
import com.haojing.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "订单相关",tags = {"订单相关的接口"})
@RequestMapping("orders")
@RestController
public class OrdersController extends BaseController{

    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;
    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public JSONresult create(@RequestBody SubmitOrderBO submitOrderBO,
                             HttpServletRequest request,
                             HttpServletResponse response){
        // 1 创建订单
        if (submitOrderBO.getPayMethod() != PayMethod.WEIXIN.type && submitOrderBO.getPayMethod() != PayMethod.ALIPAY.type){
            return JSONresult.errorMsg("支付方式不支持");
        }
        OrderVO orderVO = orderService.createOrder(submitOrderBO);
        // todo 支付处理的请求
        String orderId = orderVO.getOrderId();

        // 2 创建订单后，移除购物车中提交的商品
        // todo 整合redis后，完善购物车中的已结算商品清楚，同步到前端的cookie中
        //CookieUtils.setCookie(request,response,FOODLE_SHOPCART,"",true);
        // 3 向支付中心发送当前订单，用于保存支付中心的订单数据 构建http请求
        // todo 可以跳过支付模块的处理
        /*MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(payReturnUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("imoocUserId","imooc");
        headers.add("password","imooc");
        HttpEntity<MerchantOrdersVO> entity = new HttpEntity<>(merchantOrdersVO,headers);
        ResponseEntity<JSONresult> responseEntity =
                restTemplate.postForEntity(paymentUrl,entity,JSONresult.class);
        JSONresult paymentResult = responseEntity.getBody();
        if (paymentResult.getStatus() != 200){
            return JSONresult.errorMsg("支付失败，订单创建失败");
        }*/
        // todo 可以跳过支付模块的处理
        return JSONresult.ok(orderId);
    }

    @ApiOperation(value = "支付回调地址returnUrl", notes = "支付回调地址returnUrl", httpMethod = "POST")
    @PostMapping("/notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(@RequestParam String merchantOrderId){
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELUVER.type);
        return HttpStatus.OK.value();
    }
}
