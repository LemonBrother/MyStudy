package example.liumin.mystudy.util;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.LocaleList;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.inputmethod.InputMethodManager;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Created by Administrator on 2018-04-14.
 */

public class CommonTools {
    //常量

    private static final String SPNAME = "parameters";                      //sharedpreference的名称
    private static final int SPMODE = Activity.MODE_PRIVATE;                 //sharedpreference的模式
    private static final int REQUESTPERMISSIONCODE=1816;                    //请求权限的请求码
    private static String DeviceId;                                         //设备ID
    private static CharSequence FormatDeviceId;                             //格式化后的设备ID
    private static   long T1=0,T2=0;                                    //用于判断点击按钮时间间隔的两个变量


    /**
     *  获取MD5值
     * @param string 要转换的字符串
     * @return md5字符串
     */
    public static String getmd5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length*2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 用于目标中的日期
     * @return  全数字的日期格式
     */
    public static int getDesDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date d = new Date();
        return Integer.valueOf(sdf.format(d));
    }

    public static int getDesDate(Calendar c){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date d = c.getTime();
        return Integer.valueOf(sdf.format(d));
    }


    /**
     * @return  yyyy-MM-dd
     */
    public static String getdate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        return String.valueOf(sdf.format(d));
    }

    /**
     *
     * @return HH:mm:ss
     */
    public static String gettime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date d = new Date();
        return String.valueOf(sdf.format(d));
    }

    /**根据指定的格式返回时间和日期
     * @return
     */
    public static String getdateandtime(){

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1=new Date();
        String timestr=format.format(d1);
        return timestr;

    }

    /**根据指定的格式返回时间和日期
     * @param time
     * @param rules
     * @return
     */
    public static String getdateandtime(long time,String rules){

        SimpleDateFormat format=new SimpleDateFormat(rules);
        Date d1=new Date(time);
        String timestr=format.format(d1);
        return timestr;

    }

    /**
     * 获取当前在今天的第几个小时
     * @return
     */
    public static int getnowofdayhours(){
        Calendar mCalendar=Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        return mCalendar.get(Calendar.HOUR_OF_DAY);

    }




    /**存储SharedPreferences
     * @param context
     * @param key
     * @param object
     * @return
     */
    public static boolean saveSP(Context context, String key, Object object){
        SharedPreferences share = context.getSharedPreferences( SPNAME,  SPMODE);
        SharedPreferences.Editor editor = share.edit();
        if (object instanceof String) {

            editor.putString(key, (String) object);

        } else if (object instanceof Integer) {

            editor.putInt(key, (Integer) object);

        } else if (object instanceof Boolean) {

            editor.putBoolean(key, (Boolean) object);

        } else if (object instanceof Float) {

            editor.putFloat(key, (Float) object);

        } else if (object instanceof Long) {

            editor.putLong(key, (Long) object);

        }


        try {
            return editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * dp转为px
     * @param context  上下文
     * @param dipValue dp值
     * @return
     */
    public static  int dip2px(Context context,float dipValue)
    {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }


    /**判断是否有外网连接（普通方法不能判断外网的网络是否连接，比如连接上局域网）
     * @return
     */
    public static final boolean ping(String hostip) {

        //总是提示连接失败，直接返回true试下



        try {
            String ip =hostip;// ping 的地址，可以换成任何一种可靠的外网
            Process p = Runtime.getRuntime().exec("ping -c 1 -w 1 " + ip);// ping网址1次,超时时间1000

            // ping的状态
            int status = p.waitFor();
            if (status == 0) {

                return true;
            } else {

            }
        } catch (IOException e) {

            Log.v("ping","IOException");
        } catch (InterruptedException e) {

            Log.v("ping","IOException");
        } finally {


        }

        return false;

    }




    /**  隐藏键盘
     * @param context
     */
    public static void hidekeyboard(Activity context){

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(context.getWindow().getDecorView().getWindowToken(), 0);
    }

    /**去掉float类型后边多余的0
     * @param s 要格式化的字符串
     * @return
     */
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
    public static String subZeroAndDot(float f){
        String s = String.valueOf(f);
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }



    /** 四舍五入到十位,用在图片显示时，正常使用需要再修改
     * @param imagewidth
     * @return
     */
    public  static  int getroundTen(double imagewidth) {
        int roundedInt = (int) Math.round(imagewidth);
        if (roundedInt % 10 <=7) {
            return (roundedInt / 10) * 10;
        }
        return (roundedInt / 10) * 10 + 10;

    }


    /**正则匹配是否正确的IP地址
     * @param addr  IP地址
     * @return
     */
    public static boolean iscorrectip(String addr){
        if(addr.length() < 7 || addr.length() > 15 || "".equals(addr))
        {
            return false;
        }
        //          判断IP格式和范围
        String rexp = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(addr);
        boolean ipAddress = mat.find();
        return ipAddress;

    }

    /**正在匹配是否纯数字
     * @param str 需要判断的字符串
     * @return
     */
    public static  boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 格式化日期，将当前日期至于0和24之间
     * @param cal
     * @param type
     */
    public static void formatCalendar(Calendar cal,int type){
        if(type == 0){
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 00);
            cal.set(Calendar.SECOND, 00);

        }
        else{
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
        }

    }

    /**
     * 返回本周周一0点到周日23.59.59秒的long值
     * @return
     */
    public static long[] getWeekLong(){


        /**
         *
         * 收获
         * 如果当天是周日的话，需要先进行判断，然后再设置第一天是周一
         * 程序初始认为周一已然是一周的第一天，所以，如果当天是周日，已经在下一周了。
         * 先减一周，然后再设置周一是第一天。就会得到我想要的正常日期
         */
        long[] temp = new long[2];
        Calendar cal =Calendar.getInstance();
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            cal.add(Calendar.WEEK_OF_YEAR,-1);

        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
        formatCalendar(cal,0);
        temp[0] = cal.getTimeInMillis();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        formatCalendar(cal,1);
        temp[1] = cal.getTimeInMillis();

        return  temp;
    }


    /**
     * 返回本月1号0点到月末24点的long值
     * @return
     */
    public static long[] getMonthLong(){
        long temp[] = new long[2];
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH,1);
        formatCalendar(c,0);
        temp[0] = c.getTimeInMillis();
        c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));
        formatCalendar(c,1);
        temp[1] = c.getTimeInMillis();

        return temp;
    }

    /**
     返回今天0点到24点的long值
     * @return
     */
    public static long[] getDayLong(){
        long temp[] = new long[2];
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE, 00);
        c.set(Calendar.SECOND, 00);


        temp[0] = c.getTimeInMillis();
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);

        temp[1] = c.getTimeInMillis();

        return temp;
    }


    /**
     * 返回本周工作日的long值区间
     * @return
     */
    public static long[][] getWeekDayLong(){
        long temp[][] = new long[7][3];
        Calendar c = Calendar.getInstance();

        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            c.add(Calendar.WEEK_OF_YEAR,-1);
        }

        c.setFirstDayOfWeek(Calendar.MONDAY);

        //从1开始，也就是从周日开始，因为常量值周日固定为1，所以数组顺序周日在前。
        for(int i = 0;i<7;i++){



            c.set(Calendar.DAY_OF_WEEK,i+1);
            c.set(Calendar.HOUR_OF_DAY,0);
            c.set(Calendar.MINUTE, 00);
            c.set(Calendar.SECOND, 00);


            temp[i][0] = c.getTimeInMillis();
            c.set(Calendar.HOUR_OF_DAY,23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);

            temp[i][1] = c.getTimeInMillis();

            temp[i][2] = Long.valueOf(getdateandtime(c.getTimeInMillis(),"yyyyMMdd"));



        }






        return temp;
    }

    /**
     返回本周周一到周末日期
     * @return
     */
    public static int[] getweekdate(){
        int[]  temp= new int[2];
        Calendar c = Calendar.getInstance();

        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            c.add(Calendar.WEEK_OF_YEAR,-1);

        }
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期

        temp[0] = Integer.valueOf( CommonTools.getDesDate(c) );
        c.add(Calendar.WEEK_OF_YEAR,1);
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        temp[1] = Integer.valueOf( CommonTools.getDesDate(c));
        return temp;
    }

    public static int[] getmonthdate(){
        int[]  temp= new int[2];
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH,1);

        temp[0] = Integer.valueOf( CommonTools.getDesDate(c));
        c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));


        temp[1] = Integer.valueOf(  CommonTools.getDesDate(c));
        return temp;
    }

    /**
     * 申请运行时权限，SD卡读写权限
     * @param activity
     */
    public static boolean requestSDpermission(Activity activity){
        //检查权限,
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            String[] pers = new String[2];


            //超过23需要动态申请
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions( activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUESTPERMISSIONCODE);
                return false;
            }else{
                return true;
            }


        }else{
            return true;
        }

    }




    /**
     * 获取机器唯一码，并进行存储
     * @param context
     * @return
     */
    public static  String getUDID(Context context )
    {
                if( TextUtils.isEmpty( DeviceId)) {
                    final String id = context.getSharedPreferences( SPNAME, SPMODE).getString("DeviceId","");
                    if (!TextUtils.isEmpty(id)) {
                        // Use the ids previously computed and stored in the prefs file
                         DeviceId= id;
                    } else {
                        // Use the Android ID unless it's broken, in which case fallback on deviceId,
                        // unless it's not available, then fallback on a random number which we store
                        // to a prefs file
                        try {
                            final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                            if (!"9774d56d682e549c".equals(androidId)) {
                                 DeviceId = UUID.nameUUIDFromBytes(androidId.getBytes("utf8")).toString().replace("-","");
                            } else {
                                final String deviceId = ((TelephonyManager)context. getSystemService( Context.TELEPHONY_SERVICE )).getDeviceId();
                                 DeviceId = deviceId!=null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")).toString() : UUID.randomUUID().toString().replace("-","");
                            }
                        } catch (Exception e) {
                            //throw new RuntimeException(e);
//                            ToastUtils.showToast(context,"获取失败，请联系管理员");
                        }
                        // Write the value out to the prefs file
                        context.getSharedPreferences( SPNAME, SPMODE).edit().putString("DeviceId",  DeviceId).commit();
                    }
                }
        return  DeviceId;
    }

    /**
     * @param context
     * @return 返回格式化后的机器序列号
     */
    public static String getFormatDeviceId(Context context){
        if(TextUtils.isEmpty( FormatDeviceId)){
            final String tempid =  context.getSharedPreferences( SPNAME, SPMODE).getString("FormatDeviceId","");

            if(TextUtils.isEmpty(tempid)){
                String temp = null;
                StringBuilder sb = null;
                String result="";
                try {
                    temp = getUDID(context);
                    sb = new StringBuilder();
                    if(!TextUtils.isEmpty(temp)){
                        for(int i = 3;i<temp.length();i=i+4){
                            sb.append(temp.charAt(i)) ;
                        }
                        sb.append(temp.charAt(2));
                        sb.append(temp.charAt(6));
                    }
                    result = sb.toString();;
                    if(result.length() != 10){
                        if(result.length()>10){
                            result = result.substring(0,10);
                        }
                        if(result.length()<10){
                            result = result + "0000000000".substring(0,10-temp.length());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
//                    ToastUtils.showToast(context,"转换失败，请联系管理员");
                }
                 FormatDeviceId =result.toString();
                context.getSharedPreferences( SPNAME, SPMODE).edit().putString("FormatDeviceId",  FormatDeviceId.toString()).commit();
            }else{
                FormatDeviceId= tempid;
            }
        }
        return  FormatDeviceId.toString();



    }

    /**
     * @param s
     * @return 根据字母返回数字，用于或许机器序列号
     */
    public static int getCodeInt(String s){
        switch (s){
            case "A":
                return 1;
            case "B":
                return 2;
            case "C":
                return 3;
            case "D":
                return 4;
            case "E":
                return 5;
            case "F":
                return 6;
            case "G":
                return 7;
            case "H":
                return 8;
            case "I":
                return 9;
            case "J":
                return 10;
            case "K":
                return 11;
            case "L":
                return 12;
            case "M":
                return 13;
            case "N":
                return 14;
            case "O":
                return 15;
            case "P":
                return 16;
            case "Q":
                return 17;
            case "R":
                return 18;
            case "S":
                return 19;
            case "T":
                return 20;
            case "U":
                return 21;
            case "V":
                return 22;
            case "W":
                return 23;
            case "X":
                return 24;
            case "Y":
                return 25;
            case "Z":
                return 26;
            default:return -1;

        }
    }






    /**
     * @param context
     * @return 检查wifi是否打开
     */
    public static boolean isWiFiOn(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * @param string 需要格式化的字符串
     * @return 返回字符串中的所有汉字，过滤掉所有数字符号字母等
     */
    public static String getChineseString(String string){
        Pattern  pattern = Pattern.compile("[^\u4E00-\u9FA5]");
        Matcher  matcher = pattern.matcher(string);
        return matcher.replaceAll("");
    }

    /**
     * @param f  需要格式化的float数值
     * @return 返回两位小数，并进行四舍五入
     */
    public static float RoundFloat(float f){

        BigDecimal b   =   new   BigDecimal(f);
//        double   result   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  //返回double值
        float   result   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
        return result;
    }





    /**
     * @return  获得long形的时间1531475233054
     */
    public static long getlongtime(){
        return Calendar.getInstance().getTimeInMillis();
    }

    public static String getDistanceTime(long diff) {

        //之前是带天的后来感觉不好看，去掉
//        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff)/(1000* 60 * 60);
        long minutes = (diff-hours*(1000* 60 * 60))/(1000* 60);
        long secs = (diff-hours*(1000* 60 * 60)-minutes*(1000*60))/(1000);
        return  ""+hours+"小时"+minutes+"分"+secs+"秒" ;
    }

    /**
     * @return 根据两次点击时间，判断是否可以点击
     */
    public static boolean getTimeSpace(){
        boolean b;
        if( T1==0){
            b=true;
             T1= getlongtime();
        }else{
             T2=getlongtime();
            if(( T2- T1)>700){
                b= true;
                 T1= getlongtime();
            }else{
                b= false;
            }
        }

        return b;
    }

    /**
     * @return 是否演示版本
     */
    public static boolean IsDemoVersion(){
        boolean b=false;
        if(TextUtils.equals(BuildConfig.BUILD_TYPE.toString(),"demo")){
            b=true;
        }
        return b;
    }


    /**
     * 判断某个Activity 界面是否在前台
     * @param context
     * @param className 某个界面名称
     * @return
     */
    public static boolean  isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;

    }

    /**
     * @param context
     * @return 屏幕是否亮
     */
    public static boolean isScreenOn(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (pm.isScreenOn()) {
            return true;
        }

        return false;
    }

    /**
     * @param context
     * @return 屏幕是否锁屏
     */
    public static boolean isScreenLock(Context context){
        KeyguardManager mKeyguardManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
        if (mKeyguardManager.inKeyguardRestrictedInputMode()) {
            return true;
        }
        return false;
    }


}
