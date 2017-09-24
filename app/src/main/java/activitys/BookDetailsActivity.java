package activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import beans.Msg;

public class BookDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView star;
    ImageView yellowStar;
    ImageView sixing;
    Button guihuan;
    Button qingqiu;
    Button yulan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdetails);
        star = (ImageView) findViewById(R.id.star);
        yellowStar = (ImageView) findViewById(R.id.yellow_star);
        sixing = (ImageView) findViewById(R.id.imageView6);
        guihuan = (Button) findViewById(R.id.button);
        qingqiu = (Button) findViewById(R.id.qingqiu_button);
        yulan = (Button) findViewById(R.id.yulancishu_button);

        star.setOnClickListener(this);
        yellowStar.setOnClickListener(this);
        sixing.setOnClickListener(this);
        guihuan.setOnClickListener(this);
        yulan.setOnClickListener(this);
        qingqiu.setOnClickListener(this);

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
            case R.id.button:
                guihuan.setVisibility(View.INVISIBLE);
                yulan.setVisibility(View.VISIBLE);
                qingqiu.setVisibility(View.VISIBLE);
                break;
            case R.id.qingqiu_button:
                guihuan.setVisibility(View.VISIBLE);
                yulan.setVisibility(View.INVISIBLE);
                qingqiu.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
