package activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiyou3g.thefriendsofthecity.R;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import beans.CityInfo;
import beans.DistrictsInfo;
import beans.InfoLists;
import beans.ProvinceInfo;
import beans.UserInfo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.JsonParserUtil;
import utils.MobileNumUtil;
import utils.OkhttpUtil;
import utils.SharedPerferenceUtil;
import xiyou.mobile.User;

/**
 * Created by xiyou3g on 2017/9/25.
 *  注册
 */

public class LoginActivity extends SwipeCloseActivity implements View.OnClickListener {
    private LinearLayout mSignin;
    private LinearLayout mSignup;
    private Button mSignInButton;
    private TextView mLogin_bt;
    private Button mRegisterBt;
    private Button yanzhengma;
    TextView signOnText;
    //-_-
    ImageView back;
    AppCompatSpinner provinceSpinner;
    AppCompatSpinner citySpinner;
    AppCompatSpinner countySpinner;



    ArrayList<String> provinceName;
    ArrayAdapter<String> provinceAdapter;

    ArrayList<String> cityName;
    ArrayAdapter<String> cityAdapter;

    ArrayList<String> countyName;
    ArrayAdapter<String> countyAdapter;


    EditText teleNumText;
    EditText passwordText;
    EditText yzNumText;

    ProvinceInfo mProvinceInfo;
    CityInfo mCityInfo;
    DistrictsInfo mDistrictsInfo;

    String teleNum;
    String password;
    String yzNum;


    /**
     * 使用某个城市列表前先判空
     */


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    if (!InfoLists.PInfos.isEmpty()) {
                        InfoLists.PInfos.clear();
                        ProvinceInfo p = new ProvinceInfo();
                        p.setProvinceName("省");
                        InfoLists.PInfos.add(p);
                        provinceName.clear();
                    }
                    JsonParserUtil.getAllProvince(msg.obj.toString());
                    while (provinceName.isEmpty()) {
                        for (int i = 0; i < InfoLists.PInfos.size(); i++) {
                            String s = InfoLists.PInfos.get(i).getProvinceName();
                            provinceName.add(s);
                        }
                    }
                    provinceAdapter.notifyDataSetChanged();
                    cityAdapter.notifyDataSetChanged();
                    countyAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    if (!InfoLists.CInfos.isEmpty()) {
                        InfoLists.CInfos.clear();
                        CityInfo c = new CityInfo();
                        c.setCityName("市");
                        InfoLists.CInfos.add(c);
                        cityName.clear();
                    }
                    if (!InfoLists.DInfos.isEmpty()) {
                        InfoLists.DInfos.clear();
                        DistrictsInfo d = new DistrictsInfo();
                        d.setDistrictName("区");
                        InfoLists.DInfos.add(d);
                        countyName.clear();
                        countyName.add("区");
                    }
                    JsonParserUtil.getCitiesFromPro(msg.obj.toString());
                    while (cityName.isEmpty()) {
                        for (int i = 0; i < InfoLists.CInfos.size(); i++) {
                            String s = InfoLists.CInfos.get(i).getCityName();
                            cityName.add(s);
                        }
                    }
                    citySpinner.setSelection(0);
                    cityAdapter.notifyDataSetChanged();
                    countyAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    if (!InfoLists.DInfos.isEmpty()) {
                        InfoLists.DInfos.clear();
                        DistrictsInfo d = new DistrictsInfo();
                        d.setDistrictName("区");
                        InfoLists.DInfos.add(d);
                        countyName.clear();
                    }
                    JsonParserUtil.getDistrictsFromCity(msg.obj.toString());
                    while (countyName.isEmpty()) {
                        for (int i = 0; i < InfoLists.DInfos.size(); i++) {
                            String s = InfoLists.DInfos.get(i).getDistrictName();
                            countyName.add(s);
                        }
                    }
                    countySpinner.setSelection(0);
                    countyAdapter.notifyDataSetChanged();
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
    private Context mContext;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        requestInfo(0, 0);
        provinceName = new ArrayList<>();
        cityName = new ArrayList<>();
        countyName = new ArrayList<>();
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
        //省

//        provinceName.add("省");
        provinceAdapter = new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_spinner_item, provinceName);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpinner.setAdapter(provinceAdapter);
        provinceSpinner.setSelection(0, true);
        provinceAdapter.setNotifyOnChange(true);
        //市
//        cityName.add("市");
        cityAdapter = new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_spinner_item, cityName);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);
        citySpinner.setSelection(0, true);
        cityAdapter.setNotifyOnChange(true);
        //区
//        countyName.add("区");
        Log.d("列表", "" + countyName.size());
        countyAdapter = new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_spinner_item, countyName);
        countyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countySpinner.setAdapter(countyAdapter);
        countySpinner.setSelection(0, true);
        countyAdapter.setNotifyOnChange(true);


        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                mProvinceInfo = InfoLists.PInfos.get(pos);
                requestInfo(1, mProvinceInfo.getId());
//                Toast.makeText(LoginActivity.this, "你点击的是:" + provinceName.get(pos) + pos + "真的" + mProvinceInfo.getProvinceName(), Toast.LENGTH_SHORT).show();
                cityAdapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                mCityInfo = InfoLists.CInfos.get(pos);
                requestInfo(2, mCityInfo.getCityId());
//                Toast.makeText(LoginActivity.this, "你点击的是:" + cityName.get(pos) + pos + "真的" + mCityInfo.getCityName(), Toast.LENGTH_SHORT).show();
                countyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        countySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                mDistrictsInfo = InfoLists.DInfos.get(pos);
//                Toast.makeText(LoginActivity.this, "你点击的是:" + countyName.get(pos) + pos + "真的" + mDistrictsInfo.getDistrictName(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
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
        mRegisterBt = (Button) findViewById(R.id.sign_up_button);
        mLogin_bt=(TextView)findViewById(R.id.login_bt);
        signOnText = (TextView) findViewById(R.id.sign_up_text);
        mSignin = (LinearLayout) findViewById(R.id.sign_in_layout);
        mSignup = (LinearLayout) findViewById(R.id.sign_up_layout);
        back = (ImageView) findViewById(R.id.chuce_back_button);
        yanzhengma = (Button) findViewById(R.id.send_yanzhengma);
        passwordText = (EditText) findViewById(R.id.password_edit_text);
        teleNumText = (EditText) findViewById(R.id.account_edit_text);
        yzNumText = (EditText) findViewById(R.id.yanzhengma_edit_text);
        mSignin.setVisibility(View.VISIBLE);
        mSignup.setVisibility(View.INVISIBLE);
    }
    private void initBind() {
        signOnText.setOnClickListener(this);
        back.setOnClickListener(this);
        mRegisterBt.setOnClickListener(this);
        yanzhengma.setOnClickListener(this);
        mLogin_bt.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_text:
                mSignin.setVisibility(View.INVISIBLE);
                mSignup.setVisibility(View.VISIBLE);
                passwordText = (EditText) findViewById(R.id.zhuce_password_edit_text);
                teleNumText = (EditText) findViewById(R.id.zhuce_phone_edit_text);
                break;
            case R.id.chuce_back_button:
                mSignup.setVisibility(View.INVISIBLE);
                mSignin.setVisibility(View.VISIBLE);
                passwordText = (EditText) findViewById(R.id.password_edit_text);
                teleNumText = (EditText) findViewById(R.id.account_edit_text);
                break;

            case R.id.sign_up_button:  //完善
                teleNum = teleNumText.getText().toString();
                password = passwordText.getText().toString();
                yzNum = yzNumText.getText().toString();

                Log.d("45632", "onClick: " + teleNum + password + yzNum);
                /**
                 * 验证信息
                 */

                Callback callback = new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //失败
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "重试一下", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        //成功
                        final String responseData = response.body().string();
                        Gson gson = new Gson();
                        final Re r = gson.fromJson(responseData, Re.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!r.getCode().equals(200)) {
                                    Log.e("conde",responseData+"");
                                    Toast.makeText(LoginActivity.this, r.getMsg(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginActivity.this, r.getMsg(), Toast.LENGTH_SHORT).show();
                                    mSignin.setVisibility(View.INVISIBLE);
                                    mSignup.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                        Log.d("sxsxqsx", "onResponse: 111" + r.getMsg());
                    }
                };
                OkhttpUtil.register(teleNum, password, yzNum, "" + mProvinceInfo.getId(), "" + mCityInfo.getCityId(), "" + mDistrictsInfo.getDistrictId()).enqueue(callback);
                break;




            case R.id.login_bt:
                teleNum = teleNumText.getText().toString();
                password = passwordText.getText().toString();
                yzNum = yzNumText.getText().toString();
                Log.d("45632", "onClick: " + teleNum + password + yzNum);
                OkhttpUtil.login(teleNum, password).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "重试一下", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        if (response.isSuccessful()) {
                            SharedPerferenceUtil.saveUserInfo(getApplication(), teleNum, password);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    JSONObject j= null;
                                    try {
                                        j = new JSONObject(response.body().string());
                                        JSONObject jo=j.getJSONObject("userInfo");
                                        UserInfo.setId(jo.getLong("id"));
                                        UserInfo.setUserName("userName");
                                        UserInfo.setSex(jo.getBoolean("sex"));
                                        UserInfo.setSignature(jo.getString("signature"));
                                        UserInfo.setFaviconUrl(jo.getString("faviconUrl"));
                                        UserInfo.setAttentionNum(jo.getInt("attentionNum"));
                                        UserInfo.setFansNum(jo.getInt("fansNum"));
                                        UserInfo.setProvinceId(jo.getInt("provinceId"));
                                        UserInfo.setCityId(jo.getInt("cityId"));
                                        UserInfo.setDistrictId(jo.getInt("districtId"));
                                        UserInfo.setCityName(jo.getString("cityName"));
                                        UserInfo.setProvinceName(jo.getString("provinceName"));
                                        UserInfo.setDistrictName(jo.getString("districtName"));
                                        new Thread()
                                        {
                                            @Override
                                            public void run() {
                                                User.register(teleNum,password, ""+UserInfo.getId());    //注册失败,已经注册过可以直接登录
                                                User.login(teleNum,password);
                                                super.run();
                                            }
                                        }.start();

                                    } catch (JSONException e) {
                                    } catch (IOException e) {
                                    }
                                    LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(mContext);
                                    Intent intent = new Intent("com.example.broadcast.LOGIN");
                                    localBroadcastManager.sendBroadcast(intent);
                                }
                            });
                            finish();
                        }
                    }
                });
                break;
            case R.id.send_yanzhengma:
                senfSMS(teleNumText.getText().toString());
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

    class Re {
        String code;
        String msg;

        public String getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
