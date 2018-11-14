package example.liumin.mystudy.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2018-10-31..
 *
 *      1、数据库是随用随创建，还是可以在App中，直接创建，然后APP在退出的时候在销毁
 *
 *
 *
 *
 *
 */

public class MyDb extends SQLiteOpenHelper {

    public Context context;
    public static final String DBNAME ="MyDb";
    public static final String ITEMTABLENAME = "items";
    private static MyDb Instance;
    private  static SQLiteDatabase SD;

    public MyDb(Context context) {
        super(context, DBNAME, null, 1);
        this.context=context;

    }

    //单例模式获取实例对象
    public static MyDb getInstance(Context context){
        if(Instance ==null){
            synchronized (MyDb.class){
                if(Instance ==null){
                    Instance = new MyDb(context);
                }
            }
        }
        return Instance;
    }

    public static SQLiteDatabase getSD(Context context){
        if(SD==null){
            synchronized (MyDb.class){
                if(SD==null){
                    SD=getInstance(context).getReadableDatabase();
                }
            }
        }

        return SD;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        executeAssetsSQL(db,"create.sql");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




    public  void executeAssetsSQL(SQLiteDatabase sd,String sqlname) {


        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(context.getAssets().open(sqlname)));


            String line;
            String buffer = "";
            while ((line = in.readLine()) != null) {
                buffer += line;
                if (line.trim().endsWith(";")) {
                    sd.execSQL(buffer.replace(";", ""));

                    buffer = "";
                }
            }
        } catch (IOException e) {
            Log.e("db-error", e.toString());
        } finally {

            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                Log.e("db-error", e.toString());
            }
        }
    }







}
