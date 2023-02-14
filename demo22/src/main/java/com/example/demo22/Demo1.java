package com.example.demo22;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by 13096 on 2022/12/8.
 */
public class Demo1  {
    public static void main(String[] args) throws Exception {
        System.out.println("开始时间："+ System.currentTimeMillis());
        String sss = doGet("223132321assssssssssssssssssssssssssssssssssssssssssssssssssss" +
                "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssdddddddddddddddddddddddddddddddddddddddddd" +
                "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        System.out.println("结束时间："+ System.currentTimeMillis());
        System.out.println(sss);
        byte[] ssss =  unGZip(sss);
        System.out.println(ssss);
    }

    public static  String  doGet( String  test)
            throws ServletException, IOException {
        String data = test;

        System.out.println("原始数据大小："+data.getBytes().length);

        ByteArrayOutputStream bout =new ByteArrayOutputStream();  //缓冲流

        GZIPOutputStream gout =new GZIPOutputStream(bout);   //压缩流
        gout.write(data.getBytes());      //获取到数据自动压缩，压缩到缓冲流中
        gout.close();				//压缩流一关就会进入到缓冲流中

        byte gzip[] =bout.toByteArray(); //得到压缩后的数据
        System.out.println("压缩后的数据大小："+gzip.length);

    /*    //通知浏览器数据采用的压缩格式（设置Http响应中的消息头）
        response.setHeader("Content-Encoding", "gzip");

        //通知浏览器回送压缩后数据的长度（设置Http响应中的消息头）
        response.setHeader("Content-Length",gzip.length+"");

        response.getOutputStream().write(gzip);*/
        return gzip.toString();
    }
    public static byte[] unGZip(String str1) {
        byte[] bw = str1.getBytes();
        byte[] b = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bw);
            GZIPInputStream gzip = new GZIPInputStream(bis);
            byte[] buf = new byte[1024];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            baos.flush();
            baos.close();
            gzip.close();
            bis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("恢复数据大小："+b.length);
        return b;
    }

    public static String uncompress(String str1) {
        byte[] bytes = str1.getBytes();
        if (bytes == null || bytes.length == 0) {

            return null;

        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        try {

            GZIPInputStream ungzip = new GZIPInputStream(in);

            byte[] buffer = new byte[256];

            int n;

            while ((n = ungzip.read(buffer)) >= 0) {

                out.write(buffer, 0, n);

            }

        } catch (IOException e) {

        }
        System.out.println("恢复数据大小："+out.toByteArray().length);
        return out.toByteArray().toString();

    }
}
