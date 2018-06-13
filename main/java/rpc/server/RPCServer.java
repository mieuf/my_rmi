package rpc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RPCServer {

    //定义线程池
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(final Object service,int port) {
        ServerSocket serverSocket = null;
        try {
            //启动监听
            serverSocket = new ServerSocket(port);

            while (true) {
                //获取请求
                Socket socket = serverSocket.accept();
                System.out.println("a connection connected");
                /*  方法execute()没有返回值，而submit()方法可以有返回值（通过Callable和Future接口）
                 *  方法execute()在默认情况下异常直接抛出（即打印堆栈信息），不能捕获，但是可以通过自定义ThreadFactory的方式进行捕获（通过setUncaughtExceptionHandler方法设置），而submit()方法在默认的情况下可以捕获异常
                 *  方法execute()提交的未执行的任务可以通过remove(Runnable)方法删除，而submit()提交的任务即使还未执行也不能通过remove(Runnable)方法删除
                 */
                executorService.submit(new PocessorHandler(service,socket));
//                executorService.execute(new PocessorHandler(service,socket));
                /*
                 * 每次访问都会新建一个线程，每个连接都会用新的线程去接请求，导致资源浪费
                 */
//                Thread thread = new Thread(new PocessorHandler(service,socket));
//                thread.start();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
