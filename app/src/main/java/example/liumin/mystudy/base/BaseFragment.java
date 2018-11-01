package example.liumin.mystudy.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import example.liumin.mystudy.R;

/**
 * Created by Administrator on 2018-11-01.
 */

public class BaseFragment extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();
    public TextView tip;
    public StringBuilder statesb=new StringBuilder();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addstate("onAttach");

        statesb.delete(0,statesb.length());  //每次onattach将statesb清空
        statesb.append(TAG);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addstate("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        addstate("onCreateView");

        View v = inflater.inflate(R.layout.fragment_static,container,false);
        tip = $(v,R.id.fragment_tip);
        tip.setText(TAG);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addstate("onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        addstate("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        addstate("onResume");
        tip.setText(statesb.toString());
    }

    @Override
    public void onPause() {
        super.onPause();
        addstate("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        addstate("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addstate("onDestroyView");
        tip.setText(statesb.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        addstate("onDestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        addstate("onDetach");
    }


    protected <T extends View> T $(View view,int viewid){
        return  (T)view.findViewById(viewid);
    }

    public void addstate(String state){
        Log.v(TAG,TAG+":"+state);
        statesb.append(state+",");
    }

    public void getToast(String content){
        Toast.makeText(getActivity(),content,Toast.LENGTH_SHORT).show();
    }

}
