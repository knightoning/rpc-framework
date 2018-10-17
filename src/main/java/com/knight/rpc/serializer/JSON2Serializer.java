package com.knight.rpc.serializer;

import com.alibaba.fastjson.JSON;

public class JSON2Serializer implements ISerializer {
    /**
     * 序列化
     *
     * @param obj
     * @return
     */
    @Override
    public <T> byte[] serialize(T obj) {

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        return new byte[0];
    }

    /**
     * 反序列化
     *
     * @param data
     * @param clazz
     * @return
     */
    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return null;
    }
}
