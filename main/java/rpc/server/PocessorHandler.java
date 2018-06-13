package rpc.server;

import rpc.connectiontools.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class PocessorHandler implements Runnable {

    private Object service;//发布的服务
    private Socket socket;

    public PocessorHandler(Object service, Socket socket) {
        this.service = service;
        this.socket = socket;
    }

    @Override
    public void run() {
        //TODO 处理请求
        ObjectInputStream inputStream = null;
        try {
            System.out.println("current thread : " + Thread.currentThread().getId());
            inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();

            Object result = invoke(rpcRequest);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过工具类转化请求调用的接口并驱动对应的具体实现方法
     * 请求过来的参数是:方法名，方法参数
     * @param rpcRequest
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private Object invoke(RpcRequest rpcRequest) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Object[] parmeters = rpcRequest.getParmeters();
        Class<?>[] types = new Class[parmeters.length];
        for(int i=0; i<parmeters.length; i++){
            types[i] = parmeters[i].getClass();
        }
        Method method = this.service.getClass().getMethod(rpcRequest.getMethodName(),types);
        return method.invoke(service,parmeters);
    }

}
