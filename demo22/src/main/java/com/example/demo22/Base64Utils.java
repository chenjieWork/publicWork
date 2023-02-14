package com.example.demo22;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.DeflaterOutputStream;

/**
 * Created by 13096 on 2022/12/8.
 */
public class Base64Utils {
        //  压缩字符串
        public static String compressData(String data) throws UnsupportedEncodingException {
            //要传输的字符串
            try {
                //压缩后写入字节流发送
                ByteArrayOutputStream
                        box = new ByteArrayOutputStream();
                DeflaterOutputStream
                        dos = new DeflaterOutputStream(box);
                // 压缩并将压缩后的内容输出到字节输出流box中
                dos.write(data.getBytes());
                dos.close();
                return getenBASE64inCodec(box.toByteArray());
            } catch (Exception ex) {
                ex.printStackTrace();
                return "error";
            }
        }
        //为了压缩后的内容能够在网络上传输，一般采用Base64编码
        public static String getenBASE64inCodec(byte [] b) {
            if (b == null) return null;
            return new String(Base64.encodeBase64(b, false));
        }
}
