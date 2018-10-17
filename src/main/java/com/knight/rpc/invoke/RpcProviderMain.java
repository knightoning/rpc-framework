package com.knight.rpc.invoke;

import com.knight.rpc.framework.ProviderReflect;
import com.knight.rpc.service.HelloService;
import com.knight.rpc.service.HelloServiceImpl;

public class RpcProviderMain {

    public static void main(String[] args) {

        try {
            HelloService service = new HelloServiceImpl();
            ProviderReflect.provider(service,8083);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
