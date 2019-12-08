package com.xg.patternproxy.proxy.datasourcedynamicproxy.handwriting;

import java.lang.reflect.Method;

public interface XgInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}
