package activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.util.ArrayList;
import java.util.List;

import adapters.BookAdapter;
import adapters.BookItemAdapter;
import beans.Book;

public class BorrowMoreActivity extends AppCompatActivity {

    private List<Book> mBooks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more1);
        initBooks();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.more1_recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        BookItemAdapter bookAdapter = new BookItemAdapter(mBooks);
        recyclerView.setAdapter(bookAdapter);
    }

    private void initBooks() {
        for (int i = 0; i < 20; i++) {
            Book book = new Book("借阅的书名", "作者", "这本书正在借阅");
            book.setImageId(R.id.book_image_view);
            mBooks.add(book);
        }
    }
}
