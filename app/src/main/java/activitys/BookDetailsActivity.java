package activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.xiyou3g.thefriendsofthecity.R;

public class BookDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView star;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdetails);
        star = (ImageView) findViewById(R.id.star);
        star.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
