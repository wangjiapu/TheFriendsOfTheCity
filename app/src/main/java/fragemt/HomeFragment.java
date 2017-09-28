package fragemt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.util.ArrayList;
import java.util.List;

import activitys.BookDetailsActivity;
import activitys.BorrowMoreActivity;
import activitys.MainActivity;
import adapters.BookItemAdapter;
import adapters.HomeBookAdapter;
import beans.Book;

/**
 * Created by xiyou3g on 2017/9/17.
 *
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
    List<Book> mBooks = new ArrayList<>();
    private View rootView;
    private TextView more;
    private Toolbar mToolbar;
    private FrameLayout mFrameLayout1;
    private FrameLayout mFrameLayout2;
    private FrameLayout mFrameLayout3;
    private ImageView mLeftMenu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_home,container,false);
        initBooks();
        initView(rootView);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBind();
    }

    private void initBind() {
        more.setOnClickListener(this);
        mFrameLayout1.setOnClickListener(this);
        mFrameLayout2.setOnClickListener(this);
        mFrameLayout3.setOnClickListener(this);
        mLeftMenu.setOnClickListener(this);
    }

    private void initView(View view) {
        mLeftMenu=view.findViewById(R.id.leftitem);
        mToolbar = (Toolbar) view.findViewById(R.id.home_tool_bar);
        more = (TextView) view.findViewById(R.id.home_more1);
        mFrameLayout1 = (FrameLayout) view.findViewById(R.id.home_book_frame1);
        mFrameLayout2 = (FrameLayout) view.findViewById(R.id.home_book_frame2);
        mFrameLayout3 = (FrameLayout) view.findViewById(R.id.home_book_frame3);
    }


    @Override
    public void onClick(View view) {
        Activity activity = getActivity();
        switch (view.getId()) {
            case R.id.home_more1:
                Intent intent1 = new Intent(activity, BorrowMoreActivity.class);
                intent1.putExtra("title", "猜你喜欢");
                startActivity(intent1);
                break;
            case R.id.home_book_frame1:
            case R.id.home_book_frame2:
            case R.id.home_book_frame3:
                Intent intent = new Intent(activity, BookDetailsActivity.class);
                //传Book
                startActivity(intent);
                break;
            case R.id.leftitem:
                getActivity().onBackPressed();
                break;
        }
    }

}
