package activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.OkhttpUtil;

/**
 * Created by xiyou3g on 2017/9/25.
 *
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout signin;
    LinearLayout signup;
    Button signInButton;
    TextView signOnText;
    Button back;
    //界面好丑


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 200) {
                Log.e("qqqqq", msg.obj.toString());
            } else if (msg.what == 1) {
                Log.e("qqqqq", msg.obj.toString());
            } else {
                Log.e("qqqqq", "pppppppppp");
            }

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initView();
        initBind();

        /**
         * 暂时不要删这个
         *
         */

        RequestBody formBody = new FormBody.Builder()
                .build();
        Request request = OkhttpUtil
                .getRequest(OkhttpUtil.getHOST() + OkhttpUtil.getAllProvince(),formBody);
        Call call = OkhttpUtil.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = new Message();
                message.what = 1;
                message.obj = response.body().string();
                handler.sendMessage(message);
            }
        });

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
