package com.xg.patternproxy.proxy.datasourcedynamicproxy;

import com.xg.patternproxy.proxy.datasourcedynamicproxy.db.DynamicDataSource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderServiceDynamicProxy implements InvocationHandler {
    
    private SimpleDateFormat sim = new SimpleDateFormat("yyyy");
    
    private Object targer;
    
    public Object OrderServiceDynamicProxy(Object targer){
        this.targer = targer;
        Class<?> clazz = targer.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }
    
    
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (Object arg : args) {
            System.out.println(":::" + arg);
        }
        before(args[0]);
        Object invoke = method.invoke(targer,args);
        after();
        return invoke;
    }

    private void after() {
        System.out.println("切换数据源结束！！");
        DynamicDataSource.restor();
    }

    private void before(Object arg) {
        System.out.println("开始切换数据源！！");
        try {
            System.out.println(arg.getClass());
            String yer =(String) arg.getClass().getMethod("getCreateTime").invoke(arg);
            System.out.println("已分派到【DB_"+ yer +"】数据源");
            DynamicDataSource.set("DB_" + yer);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
