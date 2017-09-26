package activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.util.ArrayList;
import java.util.List;

import adapters.BookAdapter;
import adapters.BookItemAdapter;
import beans.Book;

public class BorrowMoreActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Book> mBooks = new ArrayList<>();
    private Button mButton;
    TextView titleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Intent intent = this.getIntent();
        intent.getStringExtra("title");
        setContentView(R.layout.activity_more1);
        initBooks();
        initView();
        initBind();
        titleText.setText(intent.getStringExtra("title"));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.more1_recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        BookItemAdapter bookAdapter = new BookItemAdapter(mBooks);
        bookAdapter.setOnItemClickListener(new BookItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(BorrowMoreActivity.this, BookDetailsActivity.class);
                startActivity(intent);
            }
        });


        recyclerView.setAdapter(bookAdapter);
    }

    private void initBooks() {
        for (int i = 0; i < 20; i++) {
            Book book = new Book("书名", "作者", "书书书书书");
            mBooks.add(book);
        }
    }

    private void initBind() {
        mButton.setOnClickListener(this);
        titleText.setOnClickListener(this);
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.gengduo_back_button);
        titleText = (TextView) findViewById(R.id.title_text);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gengduo_back_button:
                finish();
                break;
        }
    }

}
