package activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.util.ArrayList;
import java.util.List;

import adapters.BookItemAdapter;
import beans.Book;

public class CollectionMoreActivity extends AppCompatActivity {

    private List<Book> mBooks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//hjk
        setContentView(R.layout.activity_more3);
        initBooks();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.more3_recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        BookItemAdapter bookAdapter = new BookItemAdapter(mBooks);
        recyclerView.setAdapter(bookAdapter);
    }

    private void initBooks() {
        for (int i = 0; i < 20; i++) {
            Book book = new Book("收藏的书名", "作者", "收藏了这本书");
            book.setImageId(R.id.book_image_view);
            mBooks.add(book);
        }
    }
}
