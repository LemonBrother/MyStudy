package example.liumin.mystudy.base;

import android.view.View;

import example.liumin.mystudy.util.CommonTools;

/**
 * Created by Administrator on 2018-10-31.
 */

public class OnMyClick implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        if(!CommonTools.getTimeSpace()){
            return ;
        }
    }
}
