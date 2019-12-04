package com.haojing.service;

import com.haojing.bo.SubmitOrderBO;
import com.haojing.vo.OrderVO;

public interface OrderService {

    /**
     * @param submitOrderBO
     * 创建订单相关信息
     */
    public OrderVO createOrder(SubmitOrderBO submitOrderBO);

    /**
     * 修改订单状态
     * @param orderId
     * @param orderStatus
     */
    public void updateOrderStatus(String orderId, Integer orderStatus);
}
