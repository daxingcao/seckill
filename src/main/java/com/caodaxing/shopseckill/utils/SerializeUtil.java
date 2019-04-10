package com.caodaxing.shopseckill.utils;

import java.io.*;

/**
 * @author daxing.cao
 * @version 0.0.1
 * @description 序列化工具类
 */
public class SerializeUtil {

    /**
     * 序列化方法
     * @param obj 序列化对象，必须实现Serializable接口
     * @return byte数组
     */
    public static byte[] serialize(Object obj){
        if(obj == null){
            return null;
        }
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.flush();
        } catch (IOException e) {
            return null;
        }
        return baos.toByteArray();
    }

    /**
     * 反序列化方法
     * @param bytes byte数组
     * @return 反序列化对象
     */
    public static Object deSerialize(byte[] bytes){
        if(bytes == null){
            return null;
        }
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

}
