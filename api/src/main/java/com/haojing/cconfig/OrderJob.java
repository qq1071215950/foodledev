package com.haojing.cconfig;

import com.haojing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单定时任务
 */
@Component
public class OrderJob {

    @Autowired
    private OrderService orderService;

    // 1 存在时间差， 2 不支持集群 3 对数据库进行全表搜索，性能差 适用小型项目
    // 采用mq

    @Scheduled(cron = "0/10 * * * * ? ")
    public void autoCloseOrder(){
        orderService.closeOrder();
    }
}
