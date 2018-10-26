package com.knight.rpc.serializer;

import org.jboss.marshalling.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class MarshallingSerializer implements ISerializer {

    final static MarshallingConfiguration  marshallingConfiguration = new MarshallingConfiguration();

    //获取序列化工厂对象，参数serial标识创建的是java序列化工厂对象
    final static MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");

    static{
        marshallingConfiguration.setVersion(5);
    }
    /**
     * 序列化
     *
     * @param obj
     * @return
     */
    @Override
    public <T> byte[] serialize(T obj) {

        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{

            final Marshaller marshaller = marshallerFactory.createMarshaller(marshallingConfiguration);

            marshaller.start(Marshalling.createByteOutput(byteArrayOutputStream));

            marshaller.writeObject(obj);

            marshaller.finish();

        }catch (Exception e){

        }
        return byteArrayOutputStream.toByteArray();
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

        try {

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);

            final Unmarshaller unmarshaller = marshallerFactory.createUnmarshaller(marshallingConfiguration);

            unmarshaller.start(Marshalling.createByteInput(byteArrayInputStream));

            Object object = unmarshaller.readObject();

            unmarshaller.finish();

            return (T)object;
        }catch (Exception e){

        }
        return null;
    }
}
