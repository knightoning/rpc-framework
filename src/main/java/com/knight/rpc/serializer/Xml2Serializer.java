package com.knight.rpc.serializer;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Xml2Serializer implements ISerializer {


    /**
     * 序列化
     *
     * @param obj
     * @return
     */
    @Override
    public <T> byte[] serialize(T obj) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(outputStream,"utf-8",true,0);
        encoder.writeObject(obj);
        encoder.close();

        return outputStream.toByteArray();
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

        XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(data));

        Object object = decoder.readObject();

        decoder.close();

        return (T)object;
    }
}
