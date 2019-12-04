package com.xg.patternproxy.proxy.jdkproxy;

import com.xg.patternproxy.proxy.Person;

public class Xjj implements Person {
    @Override
    public void findLove() {
        System.out.println("要求：必须20cm");
    }
}
