import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 云梦归遥
 * @date 2021/11/24 11:26
 * @description 获取指定路径下所有的文件，包括子目录中的所有嵌套目录和文件，而且统计文件和目录的数量。
 */
public class test2 {
    public static int fileNum = 0;
    public static int directoryNum = 0;

    public static void show(File file) {
        File[] files = file.listFiles();
        if (null != files) {
            for (int i = 0; i < files.length; i++) {
                String result = files[i].isFile()? "一个文件": "一个目录";
                System.out.println(files[i] + "\t" + result);
                if ("一个目录".equals(result)) {
                    test2.directoryNum++;
                    show(files[i]);
                } else {
                    test2.fileNum++;
                }
            }
        }
    }

    public static void main(String[] args) {

        String path = "D:/测试/11" ;
        Scanner sc = new Scanner(path);
        // 如果文件路径名有空格会发生异常，需要使用 nextLine 去获取输入的内容
        String pathName = sc.nextLine();
        System.out.println(pathName);
        File file = new File(pathName);
        System.out.println("当前路径的文件是否存在：" + file.exists());
        System.out.println("文件名称：" + file.getName());
        System.out.println("文件的长度：" + file.length());
        System.out.println("文件最后一次修改的时间：" + file.lastModified());
        System.out.println("文件的绝对路径：" + file.getAbsolutePath());
        System.out.println("=============================");
        // 循环遍历当前目录下所有的文件，包括子目录中的所有文件
        show(file);
        System.out.println("=============================");
        System.out.println("总计：文件数量：" + test2.fileNum + "; 目录数量：" + test2.directoryNum);
    }
}
