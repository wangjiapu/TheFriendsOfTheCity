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

import beans.InfoLists;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.JsonParserUtil;
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
    /**
     * 使用某个城市列表前先判空
     */


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    JsonParserUtil.getAllProvince(msg.obj.toString());
                  /*  for(int i=0;i< InfoLists.PInfos.size();i++){
                        Log.e(""+InfoLists.PInfos.get(i).getId(),
                                InfoLists.PInfos.get(i).getProvinceName());
                    }*/
                    break;
                case 1:
                    JsonParserUtil.getCitiesFromPro(msg.obj.toString());
                    break;
                case 2:
                    JsonParserUtil.getDistrictsFromCity(msg.obj.toString());
                   for(int i=0;i< InfoLists.DInfos.size();i++){
                    Log.e(""+InfoLists.DInfos.get(i).getDistrictId(),
                            InfoLists.DInfos.get(i).getDistrictName());
                }
                    break;
                default:
                    Toast.makeText(LoginActivity.this,"请求出错!",Toast.LENGTH_SHORT).show();
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
        //requestInfo(2,2);
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

    /**
     * 请求数据
     * @param i 获取的哪个级单位 0：省级 1：市级  2：区级
     * @param id  获取某个单位下的下一级列表 省级id可为0;
     */
    private void requestInfo(final int i,int id) {
        RequestBody body;
        Request request=null;
        switch (i){
            case 0://获取所有省
                body=new FormBody.Builder()
                        .build();
                request = OkhttpUtil
                        .getRequest(OkhttpUtil.getHOST() + OkhttpUtil.getAllProvince(),body);
                break;
            case 1://获取市
                body=new FormBody.Builder()
                        .add("provinceId",id+"")
                        .build();

                request=OkhttpUtil
                        .getRequest(OkhttpUtil.getHOST()
                                +OkhttpUtil.getCitiesByProvinceId(),body);

                break;
            case 2://获取区

                body=new FormBody.Builder()
                        .add("cityId",""+id)
                        .build();
                request=OkhttpUtil
                        .getRequest(OkhttpUtil.getHOST()
                                +OkhttpUtil.getDistrictsByCityId(),body);

                break;
        }
        Call call=OkhttpUtil.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.what = 404;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response)
                    throws IOException {

                if (response.isSuccessful()){
                    Message message = new Message();
                    message.what = i;
                    message.obj = response.body().string();
                    handler.sendMessage(message);
                }
            }
        });
    }
}
