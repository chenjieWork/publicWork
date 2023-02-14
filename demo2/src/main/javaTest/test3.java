import io.xjar.XCryptos;

/**
 * Created by 13096 on 2022/10/26.
 */
public class test3 {

    public static void main(String[] args) throws Exception {
        XCryptos.encryption()
                .from("D:/xiangmu/antlr-2.7.2.jar")
                .use("1qaz2wsx3edc")
                .include("/**/*.class")
                .include("/**/*.yml")
                .include("/**/*.xml")
                .to("D:\\sercetJar\\springboot.jar");
    }

}
