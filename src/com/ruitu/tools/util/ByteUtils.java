package com.ruitu.tools.util;

/**
 * 格式转换工具
 * Created by gaoxiang
 */
public class ByteUtils {

    /**
     * String 转byte数组
     * @param value 数值
     * @return byte 数组
     */
    public static byte[] fromString(String value,Integer length){
        if(value != null && !value.trim().equals("")){
            return full0(value.getBytes(),length);
        }
        return new byte[]{};
    }

    /**
     * int 数组转byte数组
     * @param value int 数组
     * @return byte 数组
     */
    public static byte[] fromInts(Integer[] value,Integer length){
        byte[] bytes = new byte[0];
        if (value != null && value.length > 0) {
            bytes = new byte[value.length];
            for (int i = 0; i < value.length; i++) {
                bytes[i] = value[i].byteValue();
            }
            return full0(bytes,length);
        }
        return full0(bytes,length);
    }


    /**
     * 填充0
     * @return
     */
    public static byte[] full0(byte[] bytes,Integer length){
        byte[] byteLength = new byte[length];
        for (int i = 0 ; i < bytes.length ; i++) {
            byteLength[i] = bytes[i];
        }
        return byteLength;
    }

    /**
     * 合并两个byte数组
     * @param byte_1 数组1
     * @param byte_2 数组3
     * @return 合并结果
     */
    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){
        byte[] byte_3 = new byte[byte_1.length+byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }

}
