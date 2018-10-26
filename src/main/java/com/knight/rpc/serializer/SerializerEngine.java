package com.knight.rpc.serializer;

import com.google.common.collect.Maps;

import java.util.Map;

public class SerializerEngine {

    public static final Map<SerializeType,ISerializer> serializeMap = Maps.newConcurrentHashMap();

    //注册序列化工具类到serializerMap

    static {
        serializeMap.put(SerializeType.DefaultJavaSerializer,new DefaultJavaSerializer());

        serializeMap.put(SerializeType.HessianSerializer,new HessianSerializer());

        serializeMap.put(SerializeType.JSONSerializer,new JSONSerializer());

        serializeMap.put(SerializeType.MarshallingSerializer,new MarshallingSerializer());

    }

    /**
     * 序列化
     *
     * @param obj
     * @param serializeType
     * @param <T>
     * @return
     */
    public static <T> byte[] serialize(T obj,String serializeType){

        SerializeType serialize = SerializeType.queryByType(serializeType);
        if(serialize == null){
            throw new RuntimeException("serialize is null");
        }

        ISerializer serializer = serializeMap.get(serialize);
        if(serializer == null){
            throw new RuntimeException("serialize error");
        }

        try {

            return serializer.serialize(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static <T>  T deserialize(byte[] data, Class<T> clazz,String serializeType){

        SerializeType serialize = SerializeType.queryByType(serializeType);
        if(serialize == null){
            throw new RuntimeException("serialize is null");
        }

        ISerializer serializer = serializeMap.get(serialize);
        if(serializer == null){
            throw new RuntimeException("serialize error");
        }

        try{

            return serializer.deserialize(data,clazz);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
