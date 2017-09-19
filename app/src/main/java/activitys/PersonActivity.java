package activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.xiyou3g.thefriendsofthecity.R;

/**
 * Created by xiyou3g on 2017/9/17.
 *
 */

public class PersonActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        String key=getIntent().getStringExtra("key");

        if (key.equals("个人主页")){

        }else if(key.equals("我的收藏")){

        }


    }
}
