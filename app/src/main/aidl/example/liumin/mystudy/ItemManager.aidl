// ItemManager.aidl
package example.liumin.mystudy;

// Declare any non-default types here with import statements

//引入包
import example.liumin.mystudy.ipc.Item;
interface ItemManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);


    List<Item> getItemList();
    void addItem(in Item item);
}
