package com.xg.patternproxy.proxy.dbstaticproxy;

public class FinalDataSource implements IDb {

    @Override
    public void thisDataSource(String dataSource) {
        System.out.println("当前数据源：" + dataSource);
    }
}
