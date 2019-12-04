package com.xg.patternproxy.proxy.staticproxy;

import com.xg.patternproxy.proxy.Person;

public class Son implements Person {
    @Override
    public void findLove() {
        System.out.println("儿子要求：胸大无脑");
    }
}
