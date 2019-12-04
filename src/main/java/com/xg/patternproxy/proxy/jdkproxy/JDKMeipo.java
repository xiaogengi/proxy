package com.xg.patternproxy.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKMeipo implements InvocationHandler {

    private Object targer;

    public Object getJDKMeipoInfo(Object targer){
        this.targer = targer;

        Class<?> aClass = targer.getClass();
        return Proxy.newProxyInstance(aClass.getClassLoader(),aClass.getInterfaces(),this);
    }




    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(args);
        Object invoke = method.invoke(targer, args);
        after();
        return invoke;
    }

    private void after() {
        System.out.println("找对象完毕！！！！");
    }

    private void before(Object[] args) {
//        for (Object arg : args) {
//            System.out.println("before :" + arg);
//        }
        System.out.println("开始找对象！！！");
    }



}
