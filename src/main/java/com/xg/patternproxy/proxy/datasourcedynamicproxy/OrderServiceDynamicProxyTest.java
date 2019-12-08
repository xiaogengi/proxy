package com.xg.patternproxy.proxy.datasourcedynamicproxy;

import java.io.FileWriter;
import java.nio.charset.Charset;

public class OrderServiceDynamicProxyTest {

    public static void main(String[] args) {
        Order order = new Order();
        order.setCreateTime("2019");
        IOrderService o =(IOrderService) new OrderServiceDynamicProxy().InfoDynamicProxy(new OrderService());
        o.insertOrder(order);
    }


    public static void main1(String[] args) throws Exception {
    }

}
