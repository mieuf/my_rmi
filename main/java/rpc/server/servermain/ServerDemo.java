package rpc.server.servermain;

import rpc.inter.IHelloService;
import rpc.server.service.HelloServiceImpl;
import rpc.server.RPCServer;

public class ServerDemo {
    public static void main(String[] args) {
        IHelloService iHelloService = new HelloServiceImpl();
        RPCServer rpcServer = new RPCServer();
        rpcServer.publisher(iHelloService,8888);
        RPCServer rpcServer1 = new RPCServer();
        rpcServer.publisher(iHelloService,8889);
    }
}
