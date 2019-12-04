package com.xg.patternproxy.proxy.staticproxy;

import com.xg.patternproxy.proxy.Person;

public class Fater implements Person {

    private Son son;

    public Fater(Son son){
        this.son = son;
    }

    @Override
    public void findLove() {

        front();
        son.findLove();
        afert();

    }

    void front(){
        System.out.println("开始物色对象！！！！！");
    }

    void afert(){
        System.out.println("儿子相亲完毕！！！！！");
    }

}
