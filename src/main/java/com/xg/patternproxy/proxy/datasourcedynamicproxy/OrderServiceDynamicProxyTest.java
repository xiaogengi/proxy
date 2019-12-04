package com.xg.patternproxy.proxy.datasourcedynamicproxy;

public class OrderServiceDynamicProxyTest {

    public static void main(String[] args) {
        Order order = new Order();
        order.setCreateTime("2019");
        IOrderService o =(IOrderService) new OrderServiceDynamicProxy().OrderServiceDynamicProxy(new OrderService());
        o.insertOrder(order,new Order1());
    }

}
