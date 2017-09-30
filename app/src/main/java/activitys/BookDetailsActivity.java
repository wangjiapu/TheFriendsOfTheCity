package activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiyou3g.thefriendsofthecity.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import beans.BookDetailInfo;
import beans.BookInfo;
import beans.Msg;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.GlideUtil;
import utils.OkhttpUtil;

public class BookDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView star;
    ImageView yellowStar;
    ImageView sixing;
    Button guihuan;
    Button qingqiu;
    Button yulan;
    Button button;
    LinearLayout after;
    LinearLayout before;
    ImageView beforeSixin;
    BookDetailInfo mBookInfo;

    private ImageView bookImage;

    private TextView bookName;
    private TextView authorName;
    private TextView returnTime;
    private TextView details;
    private TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_bookdetails);

       String s= getIntent().getStringExtra("bookInfoId");
        initData(s);

        star = (ImageView) findViewById(R.id.star);
        yellowStar = (ImageView) findViewById(R.id.yellow_star);
        sixing = (ImageView) findViewById(R.id.imageView6);
        guihuan = (Button) findViewById(R.id.button);
        qingqiu = (Button) findViewById(R.id.qingqiu_button);
        yulan = (Button) findViewById(R.id.yulancishu_button);
        button = (Button) findViewById(R.id.shuji_back_button);
        after = (LinearLayout) findViewById(R.id.book_detail_after);
        before = (LinearLayout) findViewById(R.id.book_detail_before);
        beforeSixin = (ImageView) findViewById(R.id.before_sixing_image);
        star.setOnClickListener(this);
        yellowStar.setOnClickListener(this);
        sixing.setOnClickListener(this);
        guihuan.setOnClickListener(this);
        yulan.setOnClickListener(this);
        qingqiu.setOnClickListener(this);
        button.setOnClickListener(this);
        beforeSixin.setOnClickListener(this);


         bookImage = (ImageView) findViewById(R.id.book_image);
         bookName = (TextView) findViewById(R.id.book_name);
         authorName = (TextView) findViewById(R.id.dt_author);
         returnTime = (TextView) findViewById(R.id.dt_return_time);
         details = (TextView) findViewById(R.id.textView8);
        username=(TextView)findViewById(R.id.textView4);


    }

    private void initData(String bookInfoId) {
        OkhttpUtil.requestdetailBookInfo(bookInfoId).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

               if (response.isSuccessful()){
                   Message m=new Message();
                   m.what=1;
                   m.obj=response.body().string();
                   handler.sendMessage(m);
               }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.star:
                yellowStar.setVisibility(View.VISIBLE);
                star.setVisibility(View.INVISIBLE);
                break;
            case R.id.yellow_star:
                star.setVisibility(View.VISIBLE);
                yellowStar.setVisibility(View.INVISIBLE);
                break;
            case R.id.imageView6:
                Intent intent = new Intent(this, ChatActivity.class);
                List<Msg> msgList = new ArrayList<Msg>();
                intent.putExtra("msgList", (Serializable) msgList);
                startActivity(intent);
                break;
            case R.id.before_sixing_image:
                Intent intent2 = new Intent(this, ChatActivity.class);
                List<Msg> msgList2 = new ArrayList<Msg>();
                intent2.putExtra("msgList", (Serializable) msgList2);
                startActivity(intent2);
                break;
            case R.id.button:
                guihuan.setVisibility(View.INVISIBLE);
                yulan.setVisibility(View.VISIBLE);
                qingqiu.setVisibility(View.VISIBLE);
                after.setVisibility(View.INVISIBLE);
                before.setVisibility(View.VISIBLE);
                break;
            case R.id.qingqiu_button:
                guihuan.setVisibility(View.VISIBLE);
                yulan.setVisibility(View.INVISIBLE);
                qingqiu.setVisibility(View.INVISIBLE);
                after.setVisibility(View.VISIBLE);
                before.setVisibility(View.INVISIBLE);
                break;
            case R.id.shuji_back_button:
                finish();
                break;
        }
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                try {
                    JSONObject j=new JSONObject(msg.obj.toString());
                    if (j.getString("code").equals("200")){
                        Log.e("bookInfoDetail",msg.obj.toString());
                        Gson g=new Gson();
                        mBookInfo=g.fromJson(j.getJSONObject("bookInfoDetail").toString(), BookDetailInfo.class);
                        setdata();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(BookDetailsActivity.this,"加载失败!",Toast.LENGTH_SHORT).show();
            }
        }
    };
    private void setdata() {
        GlideUtil.loadImag(this, bookImage, mBookInfo.getCoverImg());
        bookName.setText(mBookInfo.getBookName());
        username.setText(mBookInfo.getSrcUserName());
        authorName.setText(mBookInfo.getAuthor());
        returnTime.setText(mBookInfo.getGmtCreate());
        details.setText(mBookInfo.getBriefText());
    }
}
