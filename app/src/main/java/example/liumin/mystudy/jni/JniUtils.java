package example.liumin.mystudy.jni;

/**
 * Created by Administrator on 2018-10-26.
 */

public class JniUtils {

    static {
        System.loadLibrary("native-lib");
    }

    public static  native  String getString();
    public static  native  String getFormatString(String temp);

}
