package com.xg.patternproxy.proxy.datasourcedynamicproxy;

import com.xg.patternproxy.proxy.datasourcedynamicproxy.db.DynamicDataSource;
import com.xg.patternproxy.proxy.datasourcedynamicproxy.handwriting.XgClassLoader;
import com.xg.patternproxy.proxy.datasourcedynamicproxy.handwriting.XgInvocationHandler;
import com.xg.patternproxy.proxy.datasourcedynamicproxy.handwriting.XgProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;

public class OrderServiceDynamicProxy implements XgInvocationHandler {
    
    private SimpleDateFormat sim = new SimpleDateFormat("yyyy");
    
    private Object targer;
    
    public Object InfoDynamicProxy(Object targer){
        this.targer = targer;
        Class<?> clazz = targer.getClass();
        return XgProxy.newProxyInstance(new XgClassLoader(),clazz.getInterfaces(),this);
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
