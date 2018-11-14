package example.liumin.mystudy.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import example.liumin.mystudy.sqlite.MyDb;

/**
 * Created by Administrator on 2018-11-07.
 */

public class MyProvider extends ContentProvider {

    public static final String LABEL = "example.liumin.mystudy.contentprovider";
    public Context context;
    public static final int ITEMCODE = 111;

    private static final UriMatcher mMatcher;
    static{
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 初始化
        mMatcher.addURI(LABEL,"items", ITEMCODE);
        // 若URI资源路径 = content://example.liumin.mystudy.contentprovider/item ，则返回注册码ITEMCODE
    }




    @Override
    public boolean onCreate() {
        //onCreate 方法执行context赋值，和初始数据赋值等
        context = getContext();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String tablename = getTableName(uri);


        return MyDb.getSD(context).query(tablename,projection,selection,selectionArgs,null,null,sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {



        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        // 该方法在最下面
        String table = getTableName(uri);

        // 向该表添加数据
        MyDb.getSD(context).insert(table, null, values);

        // 当该URI的ContentProvider数据发生变化时，通知外界（即访问该ContentProvider数据的访问者）
        context.getContentResolver().notifyChange(uri, null);

//        // 通过ContentUris类从URL中获取ID
//        long personid = ContentUris.parseId(uri);
//        System.out.println(personid);

        return uri;



    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }


    /**
     * 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
     */
    private String getTableName(Uri uri){
        String tableName = null;
        switch (mMatcher.match(uri)) {
            case ITEMCODE:
                tableName = MyDb.ITEMTABLENAME;
                break;

        }
        return tableName;
    }
}



