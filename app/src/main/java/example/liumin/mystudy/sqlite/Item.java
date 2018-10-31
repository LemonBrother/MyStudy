package example.liumin.mystudy.sqlite;

/**
 * Created by Administrator on 2018-10-31.
 */

public class Item {
    public String id,name;

    public Item(Builder builder){
        this.id= builder.itemid;
        this.name = builder.itemname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    static class  Builder{
        private String itemid,itemname;
        public Builder name(String name ){
            this.itemname = name;
            return this;
        }

        public Builder itemid(String itemid){
            this.itemid = itemid;
            return this;
        }

        public Item build(){
            return new Item(this);
        }

    }



}
