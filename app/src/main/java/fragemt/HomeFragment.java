package fragemt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.util.ArrayList;
import java.util.List;

import adapters.BookItemAdapter;
import adapters.HomeBookAdapter;
import beans.Book;

/**
 * Created by xiyou3g on 2017/9/17.
 *
 */

public class HomeFragment extends Fragment {
    List<Book> mBooks = new ArrayList<>();
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_home,container,false);
        initBooks();
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.horizon_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);



        HomeBookAdapter bookAdapter = new HomeBookAdapter(mBooks);
        recyclerView.setAdapter(bookAdapter);
        return rootView;
    }

    private void initBooks() {
        for (int i = 0; i < 20; i++) {
            Book book = new Book("借阅的书名", "作者", "书书书书书");
            mBooks.add(book);
        }
    }
}
