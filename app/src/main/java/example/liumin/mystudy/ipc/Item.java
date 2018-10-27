package example.liumin.mystudy.ipc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018-10-27.
 */

public class Item implements Parcelable {
    public String name,location;

    public ArrayList<Integer> age;

    public Item(String name ){
        this.name = name ;
    }
    public Item(Parcel in){
        name = in.readString();
        location = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Integer> getAge() {
        return age;
    }

    public void setAge(ArrayList<Integer> age) {
        this.age = age;
    }

    // 返回对象描述（几乎都是返回0的，搜索得知含有文件描述符返回1）
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(name);
        dest.writeString(location);
    }







    // 完成发序列化的功能
    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            // 从序列化对象创建原始对象
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            // 创建一定指定大小数组
            return new Item[size];
        }
    };







}
