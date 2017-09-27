package activitys;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import beans.CityInfo;
import beans.DistrictsInfo;
import beans.InfoLists;
import beans.ProvinceInfo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.JsonParserUtil;
import utils.MobileNumUtil;
import utils.OkhttpUtil;

/**
 * Created by xiyou3g on 2017/9/25.
 *
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mSignin;
    private LinearLayout mSignup;
    private Button mSignInButton;
    private Button mRegisterBt;
    TextView signOnText;
    //-_-
    Button back;
    AppCompatSpinner provinceSpinner;
    AppCompatSpinner citySpinner;
    AppCompatSpinner countySpinner;


    ArrayList<String> provinceName;
    ArrayAdapter<String> provinceAdapter;



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

                    break;
                case 1:
                    JsonParserUtil.getCitiesFromPro(msg.obj.toString());
                    break;
                case 2:
                    JsonParserUtil.getDistrictsFromCity(msg.obj.toString());
                    break;
                case 3:
                    Toast.makeText(LoginActivity.this,"请输入正确的手机号码！",Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                   if (JsonParserUtil.sendSMSOk(msg.obj.toString())){
                       Toast.makeText(LoginActivity.this,"发送成功!!",Toast.LENGTH_SHORT).show();
                   }else{
                       Toast.makeText(LoginActivity.this,"发送失败!!",Toast.LENGTH_SHORT).show();
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
        initSpinner();
    }

    private void initSpinner() {

        provinceSpinner = (AppCompatSpinner) findViewById(R.id.spin_province);
        citySpinner = (AppCompatSpinner) findViewById(R.id.spin_city);
        countySpinner = (AppCompatSpinner) findViewById(R.id.spin_county);

        provinceName = new ArrayList<>();

        requestInfo(0, 0);

        //省
        provinceName.add("省");
        provinceName.add("2");
        Log.d("列表", "" + provinceName.size());
        provinceAdapter = new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_spinner_item, provinceName);

        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpinner.setAdapter(provinceAdapter);
        provinceSpinner.setSelection(0,true);

        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                for (int i = 0; i < InfoLists.PInfos.size(); i++) {
                String s = InfoLists.PInfos.get(i).getProvinceName();
                provinceName.add(s);
            }
                Toast.makeText(LoginActivity.this, "你点击的是:"+InfoLists.PInfos.get(pos).getProvinceName(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
               requestError();
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


    private void initView() {
        mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mRegisterBt=(Button)findViewById(R.id.sign_up_button);
        signOnText = (TextView) findViewById(R.id.sign_up_text);
        mSignin = (LinearLayout) findViewById(R.id.sign_in_layout);
        mSignup = (LinearLayout) findViewById(R.id.sign_up_layout);
        back = (Button) findViewById(R.id.chuce_back_button);
        mSignin.setVisibility(View.VISIBLE);
        mSignup.setVisibility(View.INVISIBLE);
    }
    private void initBind() {
        mSignInButton.setOnClickListener(this);
        signOnText.setOnClickListener(this);
        back.setOnClickListener(this);
        mRegisterBt.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_text:
                mSignin.setVisibility(View.INVISIBLE);
                mSignup.setVisibility(View.VISIBLE);
                break;
            case R.id.chuce_back_button:
                mSignup.setVisibility(View.INVISIBLE);
                mSignin.setVisibility(View.VISIBLE);
                break;

            case R.id.sign_up_button:  //完善
                /*OkhttpUtil.register(电话，密码，验证码，直辖市/省，市，区).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //失败
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //成功
                    }
                });*/
                break;
        }
    }


    /**
     * 请求验证码
     * @param tel  输入手机号
     */
    private void senfSMS(String tel){

        if ( ! MobileNumUtil.isMobileNO(tel)){
            Message message=new Message();
            message.what=3;
            handler.sendMessage(message);
            return;
        }
        RequestBody telBody=new FormBody.Builder()
                .add("userTel",tel)
                .build();
        Request request=OkhttpUtil.getRequest(OkhttpUtil.getHOST()+OkhttpUtil.getSms(),telBody);
        Call call=OkhttpUtil.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                requestError();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    Message message=new Message();
                    message.what=4;
                    message.obj=response.body().string();
                    handler.sendMessage(message);
                }
            }
        });
    }


    private void requestError(){
        Message message = new Message();
        message.what = 404;
        handler.sendMessage(message);
    }

}
