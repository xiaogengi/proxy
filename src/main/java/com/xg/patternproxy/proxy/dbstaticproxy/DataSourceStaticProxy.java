package com.xg.patternproxy.proxy.dbstaticproxy;

public class DataSourceStaticProxy implements IDb{

    private FinalDataSource dataSource;


    public DataSourceStaticProxy(FinalDataSource dataSource){
        this.dataSource = dataSource;
    }


    @Override
    public void thisDataSource(String dataSource) {

        front();
        this.dataSource.thisDataSource(dataSource);
        after();
    }

    private void front() {
        System.out.println("开始选择数据源！！！");
    }

    private void after() {
        System.out.println("选择数据源完毕！！！");
    }
}
