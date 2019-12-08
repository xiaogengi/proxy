package com.xg.patternproxy.proxy.u51proxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class U51BankCGLib implements MethodInterceptor {

    public Object getInstance(Class param){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(param);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        before();
        Object o1 = methodProxy.invokeSuper(o, objects);
        after();
        return o1;
    }

    private void after() {
        System.out.println("生成签名。。。。");
    }

    private void before() {
        System.out.println("验签中。。。。");
    }
}
