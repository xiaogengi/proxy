package com.xg.patternproxy.proxy.dbstaticproxy;

public class DataSourceStaticProxyTest {

    public static void main(String[] args) {
        DataSourceStaticProxy dataSourceStaticProxy = new DataSourceStaticProxy(new FinalDataSource());
        dataSourceStaticProxy.thisDataSource("c3p0");
    }
}
