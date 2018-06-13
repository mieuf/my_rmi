package rpc.server.service;

import rpc.inter.IHelloService;

public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String msg) {
        return "hello, " + msg;
    }
}
