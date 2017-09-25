package activitys;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.xiyou3g.thefriendsofthecity.R;

/**
 * Created by xiyou3g on 2017/9/25.
 */

public class LoginActivity  extends AppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_login);
    }
}
