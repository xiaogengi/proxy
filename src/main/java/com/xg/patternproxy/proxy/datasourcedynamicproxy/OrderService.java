package com.xg.patternproxy.proxy.datasourcedynamicproxy;

public class OrderService implements IOrderService {
    @Override
    public void insertOrder(Order order,Order1 o) {
        System.out.println("已保存订单");
    }
}
