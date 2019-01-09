package example.liumin.mystudy.jni;

/**
 * Created by Administrator on 2018-10-26.
 */

public class JniUtils {

    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("multi");
    }

    public static  native  String getString();
    public static  native  String getFormatString(String temp);
    public static native String getMultiString(int a,int b);

}
