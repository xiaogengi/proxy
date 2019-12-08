package com.xg.patternproxy.proxy.datasourcedynamicproxy;

public class OrderService implements IOrderService {
    @Override
    public void insertOrder(Order order) {
        System.out.println("已保存订单");
    }
}
