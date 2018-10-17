package com.knight.rpc.invoke;

import com.knight.rpc.framework.ConsumerProxy;
import com.knight.rpc.service.HelloService;

public class RpcConsumerMain {

    public static void main(String[] args) {

        try {
            HelloService service = ConsumerProxy.consume(HelloService.class,"127.0.0.1",8083);

            for (int i = 0; i < 1000; i++){
                String hello = service.sayHello("knight_" + i);
                System.out.println(hello);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
