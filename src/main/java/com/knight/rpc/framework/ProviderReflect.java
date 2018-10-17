package com.knight.rpc.framework;

import org.apache.commons.lang3.reflect.MethodUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProviderReflect {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 服务发布
     * @param service
     * @param port
     * @throws Exception
     */
    public static void provider(final Object service,int port) throws Exception{

        ServerSocket serverSocket = new ServerSocket(port);
        while (true){
            final Socket socket = serverSocket.accept();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {

                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                        try {

                            //方法名称
                            String methodName = inputStream.readUTF();
                            //方法参数
                            Object[] arguments = (Object[])inputStream.readObject();

                            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                            try {
                                //方法引用
                                Object result = MethodUtils.invokeExactMethod(service,methodName,arguments);

                                outputStream.writeObject(result);

                            }catch (Throwable t){
                                outputStream.writeObject(t);
                            }finally {
                                outputStream.close();
                            }

                        }finally {
                            inputStream.close();
                        }

                    }catch (Exception e){

                    }finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        }

    }
}
