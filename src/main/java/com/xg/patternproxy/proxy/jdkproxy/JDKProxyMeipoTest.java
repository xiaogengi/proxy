package com.xg.patternproxy.proxy.jdkproxy;

import com.xg.patternproxy.proxy.Person;

public class JDKProxyMeipoTest {

    public static void main(String[] args) {
        System.out.println(Xjj.class);
        Person jdkMeipoInfo = (Person)new JDKMeipo().getJDKMeipoInfo(new Xjj());
        jdkMeipoInfo.findLove();

        System.out.println("-------------------------------------------------");

        System.out.println(Xgg.class);
        Person jdkMeipoInfo1 = (Person) new JDKMeipo().getJDKMeipoInfo(new Xgg());
        jdkMeipoInfo1.findLove();
    }

}
