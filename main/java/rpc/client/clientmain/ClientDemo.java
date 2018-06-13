package rpc.client.clientmain;

import rpc.client.connectiontools.RpcClientProxy;
import rpc.inter.IHelloService;

public class ClientDemo {

    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        IHelloService helloService = rpcClientProxy.clientProxy(IHelloService.class,"127.0.0.1",8888);
        System.out.println(helloService.sayHello("rmi"));
        IHelloService helloService1 = rpcClientProxy.clientProxy(IHelloService.class,"127.0.0.1",8889);
        System.out.println(helloService.sayHello("rmi01"));
    }
}
