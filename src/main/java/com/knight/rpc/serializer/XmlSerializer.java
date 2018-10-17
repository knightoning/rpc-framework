package com.knight.rpc.serializer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlSerializer implements ISerializer {

    private static final XStream xStream = new XStream(new DomDriver());
    /**
     * 序列化
     *
     * @param obj
     * @return
     */
    @Override
    public <T> byte[] serialize(T obj) {
        return xStream.toXML(obj).getBytes();
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

        String xml = new String(data);
        return (T)xStream.fromXML(xml);
    }
}
