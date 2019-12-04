package com.xg.patternproxy.proxy.datasourcedynamicproxy.db;

public class DynamicDataSource {

    private static final String DEFAULT_DATASOURCE = null;

    private final static ThreadLocal<String> local = new ThreadLocal();

    private DynamicDataSource(){}

    public static String get(){
        return local.get();
    }

    public static void set(String arg){
        local.set(arg);
    }
    public static void restor(){
        local.set(DEFAULT_DATASOURCE);
    }



}
