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
    private TextView more;
    private FrameLayout mFrameLayout1;
    private FrameLayout mFrameLayout2;
    private FrameLayout mFrameLayout3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more1);
        initBooks();
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
        more.setOnClickListener(this);
//        mFrameLayout1.setOnClickListener(this);
//        mFrameLayout2.setOnClickListener(this);
//        mFrameLayout3.setOnClickListener(this);
    }

    private void initView(View view) {
        more = (TextView) view.findViewById(R.id.gengduo1);
        mFrameLayout1 = (FrameLayout) view.findViewById(R.id.book_frame1);
        mFrameLayout2 = (FrameLayout) view.findViewById(R.id.book_frame2);
        mFrameLayout3 = (FrameLayout) view.findViewById(R.id.book_frame3);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gengduo1:
                Intent intent1 = new Intent(this, BorrowMoreActivity.class);
                startActivity(intent1);
                break;
//            case R.id.book_frame1:
//            case R.id.book_frame2:
//            case R.id.book_frame3:
//                Intent intent = new Intent(this, BookDetailsActivity.class);
//                startActivity(intent);
//                break;
        }
    }

}
