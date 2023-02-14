import java.io.File;
import java.util.LinkedList;

/**
 * Created by 13096 on 2022/10/27.
 */
public class test4 {

    private static  LinkedList<String> linkedList = new LinkedList<String>();
    public static void main(String[] args) {


        String  path = "D:/测试/11";
        showPath(path);
        System.out.println("当前路径下的所有文件及文件夹为：" + linkedList);

    }

    private static void showPath(String path){
        File  root  =  new File(path);
        if (root.exists()) {
            if (root.isDirectory()) {
                File[] files = root.listFiles();
                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        if (files[i].isFile()) {
                            linkedList.addLast(files[i].toString());
                        } else if (files[i].isDirectory()) {
                            linkedList.addLast(files[i].toString());
                            showPath(files[i].toString());
                        }
                        files[i] = null;
                    }
                }
                files = null;
            } else if (root.isFile()) {
                linkedList.addLast(root.toString());
            }
        }
    }

}
