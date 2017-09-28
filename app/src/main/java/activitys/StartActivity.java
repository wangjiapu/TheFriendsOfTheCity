package activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.xiyou3g.thefriendsofthecity.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import beans.UserInfo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.OkhttpUtil;
import utils.SharedPerferenceUtil;


public class StartActivity extends AppCompatActivity {


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           if (msg.what==0){
               Toast.makeText(StartActivity.this, "刷新失败!",Toast.LENGTH_SHORT).show();
           }else if (msg.what==1){
               try {
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

                       Intent i=new Intent(StartActivity.this,MainActivity.class);
                       i.putExtra("isLogin","1");
                       startActivity(i);
                   }
               } catch (JSONException e) {
                   e.printStackTrace();
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
        initData();
    }

    private void initData() {

        String[] info= SharedPerferenceUtil.getUserInfo(getApplication());
        if (info[0].equals("") && info[1].equals("")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i=new Intent(StartActivity.this,MainActivity.class);
                    i.putExtra("isLogin","0");
                    startActivity(i);
                }
            },2000);
            return;
        }

        OkhttpUtil.login(info[0],info[1]).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message m=new Message();
                m.what=0;
                handler.sendMessage(m);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    Message msg=new Message();
                    msg.what=1;
                    msg.obj=response.body().string();
                    handler.sendMessage(msg);
                }
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
