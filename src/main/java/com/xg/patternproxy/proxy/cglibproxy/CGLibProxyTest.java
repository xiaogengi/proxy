package com.xg.patternproxy.proxy.cglibproxy;

public class CGLibProxyTest {


    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Demo instance = (Demo) new CglibMeipo().getInstance(Demo.class);
        instance.demo();
    }

}
