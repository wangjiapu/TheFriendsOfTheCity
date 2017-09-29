package activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import beans.BookInfo;
import beans.Msg;
import utils.GlideUtil;

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
    BookInfo mBookInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_bookdetails);

        Bundle bundle = this.getIntent().getExtras();
        mBookInfo = (BookInfo) bundle.get("BookInfo");

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


        ImageView bookImage = (ImageView) findViewById(R.id.book_image);
        GlideUtil.loadImag(this, bookImage, mBookInfo.getCoverImg());

        TextView bookName = (TextView) findViewById(R.id.book_name);
        bookName.setText(mBookInfo.getBookName());

        TextView authorName = (TextView) findViewById(R.id.dt_author);
        authorName.setText(mBookInfo.getAuthor());

        TextView returnTime = (TextView) findViewById(R.id.dt_return_time);
        returnTime.setText(mBookInfo.getGmtCreate());

        TextView details = (TextView) findViewById(R.id.textView8);
//        details.setText(mBookInfo.);


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
}
