package activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiyou3g.thefriendsofthecity.R;

/**
 * Created by xiyou3g on 2017/9/25.
 */

public class LoginActivity  extends AppCompatActivity implements View.OnClickListener {
    LinearLayout signin;
    LinearLayout signup;
    Button signInButton;
    TextView signOnText;
    Button back;
    //界面好丑

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initView();
       initBind();

    }



    private void initView() {
        signInButton = (Button) findViewById(R.id.sign_in_button);
        signOnText = (TextView) findViewById(R.id.sign_up_text);
        signin = (LinearLayout) findViewById(R.id.sign_in_layout);
        signup = (LinearLayout) findViewById(R.id.sign_up_layout);
        back = (Button) findViewById(R.id.chuce_back_button);
        signin.setVisibility(View.VISIBLE);
        signup.setVisibility(View.INVISIBLE);
    }

    private void initBind() {
        signInButton.setOnClickListener(this);
        signOnText.setOnClickListener(this);
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_text:
                signin.setVisibility(View.INVISIBLE);
                signup.setVisibility(View.VISIBLE);
                break;
            case R.id.chuce_back_button:
                signup.setVisibility(View.INVISIBLE);
                signin.setVisibility(View.VISIBLE);
                break;
        }

    }


}
