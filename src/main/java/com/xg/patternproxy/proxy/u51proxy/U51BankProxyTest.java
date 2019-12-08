package com.xg.patternproxy.proxy.u51proxy;

public class U51BankProxyTest {

    public static void main(String[] args) {
        U51Service instance =(U51Service) new U51BankCGLib().getInstance(U51Service.class);
        instance.U51Bank("");
    }
}
