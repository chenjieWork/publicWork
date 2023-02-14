import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by 13096 on 2022/10/28.
 */
public class testAtoB {
    private static LinkedList<String> linkedList1 = new LinkedList<String>();
    private static LinkedList<String> linkedList2= new LinkedList<String>();
    private static String pathA = "D:/测试/11";
    private static String pathB = "D:/测试/22";

    public static void main(String[] args) {
        HashMap resultTest = new HashMap();
        showPath1(pathA);
       // System.out.println(linkedList1);
        showPath2(pathB);
        LinkedList<String> compareResult  = compare(pathA,pathB);
        System.out.println(compareResult);

    }

    public static LinkedList<String> compare(String pathA , String pathB) {
        HashMap result = new HashMap();

        Iterator<String> it1 = linkedList1.iterator();
        while (it1.hasNext()) {
            String s1 = it1.next();
            s1 = s1.replaceAll("\\\\","/");
            // System.out.println(s1);
            File f1 = new File(s1);
            for (int i = 0; i < linkedList2.size(); i++) {
                String s2 = linkedList2.get(i);
                s2 = s2.replaceAll("\\\\","/");
                File f2 = new File(s2);
                if (s1.replace(pathA, "").equals(s2.replace(pathB, ""))){
                    if(f1.length()==(f2.length())){
                        if(f1.isDirectory() || f2.isDirectory()){
                            it1.remove();
                            //linkedList2.remove(i);
                            break;
                        }else{
                            if(getMD5File(f1).equals(getMD5File(f2))){
                                it1.remove();
                                //linkedList2.remove(i);
                                break;
                            }
                        }
                    }
                }
            }
        }
        //linkedList1.add("===============================以上为文件夹1的差异文件，以下为文件夹2的差异文件===============================");
        //linkedList1.addAll(linkedList2);

        return linkedList1;
    }

    private static void showPath1(String path){
        File root  =  new File(path);
        if (root.exists()) {
            if (root.isDirectory()) {
                File[] files = root.listFiles();
                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        if (files[i].isFile()) {
                            linkedList1.addLast(files[i].toString());
                        } else if (files[i].isDirectory()) {
                            linkedList1.addLast(files[i].toString());
                            showPath1(files[i].toString());
                        }
                        files[i] = null;
                    }
                }
                files = null;
            } else if (root.isFile()) {
                linkedList1.addLast(root.toString());
            }
        }
    }

    private static void showPath2(String path){
        File  root  =  new File(path);
        if (root.exists()) {
            if (root.isDirectory()) {
                File[] files = root.listFiles();
                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        if (files[i].isFile()) {
                            linkedList2.addLast(files[i].toString());
                        } else if (files[i].isDirectory()) {
                            linkedList2.addLast(files[i].toString());
                            showPath2(files[i].toString());
                        }
                        files[i] = null;
                    }
                }
                files = null;
            } else if (root.isFile()) {
                linkedList2.addLast(root.toString());
            }
        }
    }
    /**
     * 获取文件MD5
     */
    public static String getMD5File(File file) {

        String inputFile = file.getPath();
        String result = "";
        // 缓冲区大小
        int bufferSize = 256 * 1024;
        FileInputStream fis = null;
        DigestInputStream dis = null;

        try {
            // 拿到一个MD5转换器
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 使用DigestInputStream
            fis = new FileInputStream(inputFile);
            dis = new DigestInputStream(fis, md);

            // read 过程中进行MD5处理，直到读完文件
            byte[] buffer = new byte[bufferSize];
            while (dis.read(buffer) > 0)

                // 获取最终的MessageDigest
                md = dis.getMessageDigest();
            // 拿到结果，也是字节数组，包含16个元素
            byte[] resultByteArray = md.digest();
            // 同样，把字节组转换成字符串
            result = byteArrayToHex(resultByteArray);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // 将字节数组转换成字符串
    private static String byteArrayToHex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int i = 0; i < b.length; i++) {
            stmp = (Integer.toHexString(b[i] & 0xFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
            if (i < b.length - 1) {
                hs = hs + "";
            }
        }
        return hs;
    }



}
