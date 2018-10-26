package com.knight.rpc.serializer;

import org.apache.commons.lang3.StringUtils;

public enum SerializeType {

    DefaultJavaSerializer("DefaultJavaSerializer"),

    HessianSerializer("HessianSerializer"),

    JSONSerializer("JSONSerializer"),

    XMLSerializer("XMLSerializer"),

    MarshallingSerializer("MarshallingSerializer"),
    ;

    private String serializeType;

    public String getSerializeType() {
        return serializeType;
    }

    private SerializeType(String serializeType){
        this.serializeType = serializeType;
    }

    public static SerializeType queryByType(String serializeType){

        if(StringUtils.isBlank(serializeType)){
            return null;
        }

        for(SerializeType serialize : SerializeType.values()){
            if(StringUtils.equals(serializeType,serialize.getSerializeType())){
                return serialize;
            }
        }

        return null;
    }
}
