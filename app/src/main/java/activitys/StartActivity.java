package activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.xiyou3g.thefriendsofthecity.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import beans.UserInfo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.JsonParserUtil;
import utils.OkhttpUtil;
import utils.SharedPerferenceUtil;


public class StartActivity extends AppCompatActivity {


    String cityName = null;
    public LocationClient mLocationClient;
    public BDAbstractLocationListener mListener = new MyLocationListener();

    private static int flag=0;
    private static synchronized  void count(){
        flag++;
    }

    private static String result="0";

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           if (msg.what==0){
               Toast.makeText(StartActivity.this, "刷新失败!",Toast.LENGTH_SHORT).show();
           }else if (msg.what==1){
               try {
                   Log.e("11111111",msg.obj.toString());
                   JSONObject j=new JSONObject(msg.obj.toString());
                   if (j.getString("code").equals("200")){
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
                       result="1";
                       isGo();
                   }else {
                       result="0";
                       isGo();
                   }
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }else if (msg.what==2){
               if (msg.obj.equals("获取图书错误！")){
                    //出错
                   Log.e("22222","error");
                   isGo();
               }else{
                   Log.e("222222",msg.obj.toString());
                   JsonParserUtil.getInterestBookList(msg.obj.toString(),0);
                   isGo();
               }
           }else if (msg.what==3){
               if (msg.obj.equals("获取感兴趣的人失败!")){
                   //出错
                   Log.e("3333","error");
                   isGo();
               }else{
                   Log.e("3333333",msg.obj.toString());
                   JsonParserUtil.getInterestUserList(msg.obj.toString());
                   isGo();
               }
           }else if (msg.what==4){
               initLocation();
               if (msg.obj.equals("获取同城书友书失败!")){
                   Log.e("444444","error");
                   isGo();
               }else{
                   Log.e("4444444",msg.obj.toString());
                   JsonParserUtil.getInterestBookList(msg.obj.toString(),1);
                   isGo();
               }
           }
        }




    };






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                localLayoutParams.flags);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        /**
         * 这里这里 不知道怎么放
         */
        initLocation();//获取的太慢了 还没获取完没赋值 的感觉就往下走了 不知道怎么改
        Log.d("jnhbgv", cityName);

        initData();
    }


    private void initData() {

        Log.d("jnhbgv", "initData: "+cityName);
        //获取地理位置得到市名称

        OkhttpUtil.requestSameCityBooks("7",cityName).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendMessage("获取同城书友书失败!",4);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    sendMessage(response.body().string(),5);
                }
            }
        });





        OkhttpUtil.requestInterestUsers("3").enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendMessage("获取感兴趣的人失败!",3);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    sendMessage(response.body().string(),3);
                }
            }
        });


        OkhttpUtil.requestInterestBooks("3").enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendMessage("获取图书错误！",2);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                   sendMessage(response.body().string(),2);
                }
            }
        });

        String[] info= SharedPerferenceUtil.getUserInfo(getApplication());

        OkhttpUtil.login(info[0],info[1]).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               sendMessage("error",0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendMessage(response.body().string(),1);

            }
        });


    }



    private void sendMessage(String s,int f){
        Message m=new Message();
        m.what=f;
        m.obj=s;
        handler.sendMessage(m);
    }

    private void isGo() {
        count();
        if (flag==3){
            Intent i=new Intent(StartActivity.this,MainActivity.class);
            i.putExtra("isLogin",result);
            startActivity(i);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }



    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        initLocationOptions();
        mLocationClient.registerLocationListener(mListener);
    }
    private void initLocationOptions() {
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        int span=1000;
        option.setScanSpan(span);
        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();

    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("市：").append(bdLocation.getCity()).append("\n");
            cityName = currentPosition.toString();
            Log.d("ddd", "onReceiveLocation: " + cityName);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
        }


    }


}
