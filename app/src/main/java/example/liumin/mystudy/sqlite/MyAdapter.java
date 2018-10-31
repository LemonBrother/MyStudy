package example.liumin.mystudy.sqlite;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import example.liumin.mystudy.R;

/**
 * Created by Administrator on 2018-10-31.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    public Context context;
    public ArrayList<Item> listdata;

    public MyAdapter (Context context, ArrayList<Item> listdata){
        this.context =context;
        this.listdata = listdata;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sql,parent,false))  ;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.setIsRecyclable(false);

        final Item i = listdata.get(position);
        holder.tvid.setText(i.getId());
        holder.tvname.setText(i.getName());
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDb.getSD(context).execSQL("delete from items where itemid = '"+i.getId()+"'");
                Toast.makeText(context,"delete over" ,Toast.LENGTH_SHORT).show();
                listdata.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);


                View vv = View.inflate(context, R.layout.dialog_sqlite, null);


                builder.setView(vv);

                final  EditText et = (EditText) vv.findViewById(R.id.dialog_sqlite_et);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyDb.getSD(context).execSQL("update items set itemname = '"+et.getText().toString().trim()+"' where itemid = '"+ listdata.get(position).getId() +"'");
                        Toast.makeText(context,"update over" ,Toast.LENGTH_SHORT).show();
                        Item item = listdata.get(position);
                        item.setName(et.getText().toString().trim());
                        listdata.set(position,item);

                        notifyDataSetChanged();
                    }
                });

                builder.setTitle("Please input the name");
                AlertDialog ad =builder.create();
                ad.show();






            }
        });




    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }
}


    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvid,tvname;
        public Button del,update;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvid = (TextView)itemView.findViewById(R.id.itemid);
            tvname = (TextView)itemView.findViewById(R.id.itemname);
            del=(Button)itemView.findViewById(R.id.item_delete);
            update=(Button)itemView.findViewById(R.id.item_update);
        }


    }
