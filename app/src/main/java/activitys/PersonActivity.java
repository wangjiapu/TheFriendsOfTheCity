package activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.xiyou3g.thefriendsofthecity.R;

import beans.StaticString;
import fragemt.CloseFragment;
import fragemt.ColletionFragment;
import fragemt.FriendsFragment;
import fragemt.PersonFragment;



public class PersonActivity extends AppCompatActivity {
    private Fragment mFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                localLayoutParams.flags);

        setContentView(R.layout.activity_person);
        String key = getIntent().getStringExtra("key");

        if (key.equals(StaticString.PERSON)) {
            mFragment=new PersonFragment();
        } else if (key.equals(StaticString.COLLETION)) {
            mFragment=new ColletionFragment();
        }else if (key.equals(StaticString.FRIENDS)){
            mFragment=new FriendsFragment();
        }else if (key.equals(StaticString.COLSE)){
            mFragment=new CloseFragment();
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,mFragment)
                .commitNow();


    }
}
