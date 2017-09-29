package fragemt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import activitys.BookDetailsActivity;
import activitys.BorrowMoreActivity;
import activitys.MainActivity;
import activitys.SearchActivity;
import adapters.BookItemAdapter;
import adapters.HomeBookAdapter;
import beans.Book;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.OkhttpUtil;
import beans.BookInfo;
import beans.InfoLists;
import utils.GlideUtil;

/**
 * Created by xiyou3g on 2017/9/17.
 * 主页
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
    List<BookInfo> mBooks = new ArrayList<>();
    private View rootView;
    private TextView more;
    private Toolbar mToolbar;
    private EditText inputTest;
    private ImageButton search;
    private FrameLayout mFrameLayout1;
    private FrameLayout mFrameLayout2;
    private FrameLayout mFrameLayout3;
    private ImageView mLeftMenu;

    ImageView inBook1;
    ImageView inBook2;
    ImageView inBook3;

    ImageView inPerson;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_home,container,false);
        initBooks();
        initView(rootView);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        RecyclerView recyclerView = rootView.findViewById(R.id.horizon_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        HomeBookAdapter bookAdapter = new HomeBookAdapter(mBooks);
        recyclerView.setAdapter(bookAdapter);
        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String content = inputTest.getText().toString();
                if(!TextUtils.isEmpty(content)){
                    inputTest.setText("");//清空输入框
                    OkhttpUtil.searchBooks(content).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("search","error");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()){
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getActivity() , SearchActivity.class);

                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
        return rootView;
    }

    private void initBooks() {
        for (BookInfo bookInfo : InfoLists.SameBInfos) {
            mBooks.add(bookInfo);
            Log.d("44444", "initBooks: 8465132465");
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
        mToolbar = view.findViewById(R.id.home_tool_bar);
        more = view.findViewById(R.id.home_more1);
        mFrameLayout1 = view.findViewById(R.id.home_book_frame1);
        mFrameLayout2 = view.findViewById(R.id.home_book_frame2);
        mFrameLayout3 = view.findViewById(R.id.home_book_frame3);
        inBook1 = mFrameLayout1.findViewById(R.id.book_image_view);
        inBook2 = mFrameLayout2.findViewById(R.id.book_image_view);
        inBook3 = mFrameLayout3.findViewById(R.id.book_image_view);
        Log.d("123", "in41itView: " + InfoLists.BInfos.size());
        GlideUtil.loadImag(getContext(), inBook1, InfoLists.BInfos.get(0).getCoverImg());
        GlideUtil.loadImag(getContext(), inBook2, InfoLists.BInfos.get(1).getCoverImg());
        GlideUtil.loadImag(getContext(), inBook3, InfoLists.BInfos.get(2).getCoverImg());
        TextView name1 = mFrameLayout1.findViewById(R.id.book_text_view_name);
        TextView author2 = mFrameLayout1.findViewById(R.id.book_text_view_author);
        TextView name2 = mFrameLayout2.findViewById(R.id.book_text_view_name);
        TextView author1 = mFrameLayout2.findViewById(R.id.book_text_view_author);
        TextView name3 = mFrameLayout3.findViewById(R.id.book_text_view_name);
        TextView author3 = mFrameLayout3.findViewById(R.id.book_text_view_author);
        name1.setText(InfoLists.BInfos.get(0).getBookName());
        name2.setText(InfoLists.BInfos.get(1).getBookName());
        name3.setText(InfoLists.BInfos.get(2).getBookName());
        author1.setText(InfoLists.BInfos.get(0).getAuthor());
        author2.setText(InfoLists.BInfos.get(1).getAuthor());
        author3.setText(InfoLists.BInfos.get(2).getAuthor());

        FrameLayout mPeople1 = view.findViewById(R.id.home_people_more1);
        FrameLayout mPeople2 = view.findViewById(R.id.home_people_more2);
        FrameLayout mPeople3 = view.findViewById(R.id.home_people_more3);

        TextView nam1 = mPeople1.findViewById(R.id.book_text_view_name);
        TextView nam2 = mPeople2.findViewById(R.id.book_text_view_name);
        TextView nam3 = mPeople3.findViewById(R.id.book_text_view_name);
        TextView sex1 = mPeople2.findViewById(R.id.book_text_view_author);
        TextView sex2 = mPeople1.findViewById(R.id.book_text_view_author);
        TextView sex3 = mPeople3.findViewById(R.id.book_text_view_author);
        nam1.setText(InfoLists.UInfos.get(0).getUserName());
        nam2.setText(InfoLists.UInfos.get(1).getUserName());
        nam3.setText(InfoLists.UInfos.get(2).getUserName());
        sex1.setText(InfoLists.UInfos.get(0).getCityName());
        sex2.setText(InfoLists.UInfos.get(1).getCityName());
        sex3.setText(InfoLists.UInfos.get(2).getCityName());
        Log.d("4444", "initView: " + InfoLists.UInfos.get(2).getCityName());
        ImageView inP1 = mPeople1.findViewById(R.id.book_image_view);
        ImageView inP2 = mPeople2.findViewById(R.id.book_image_view);
        ImageView inP3 = mPeople3.findViewById(R.id.book_image_view);
        GlideUtil.loadImag(getContext(), inP1, InfoLists.UInfos.get(0).getFaviconUrl());
        GlideUtil.loadImag(getContext(), inP2, InfoLists.UInfos.get(1).getFaviconUrl());
        GlideUtil.loadImag(getContext(), inP3, InfoLists.UInfos.get(2).getFaviconUrl());


        mToolbar = (Toolbar) view.findViewById(R.id.home_tool_bar);
        more = (TextView) view.findViewById(R.id.home_more1);
        search= (ImageButton) view.findViewById(R.id.home_search);
        inputTest=(EditText) view.findViewById(R.id.home_input);
        mFrameLayout1 = (FrameLayout) view.findViewById(R.id.home_book_frame1);
        mFrameLayout2 = (FrameLayout) view.findViewById(R.id.home_book_frame2);
        mFrameLayout3 = (FrameLayout) view.findViewById(R.id.home_book_frame3);
    }


    @Override
    public void onClick(View view) {
        Activity activity = getActivity();
        switch (view.getId()) {
            case R.id.home_more1:
                Intent intent0 = new Intent(activity, BorrowMoreActivity.class);
                intent0.putExtra("title", "猜你喜欢");
                startActivity(intent0);
                break;
            case R.id.home_book_frame1:
                Intent intent1 = new Intent(activity, BookDetailsActivity.class);
                intent1.putExtra("BookInfo", (Serializable) InfoLists.BInfos.get(0));
                startActivity(intent1);
                break;
            case R.id.home_book_frame2:
                Intent intent2 = new Intent(activity, BookDetailsActivity.class);
                intent2.putExtra("BookInfo", (Serializable) InfoLists.BInfos.get(1));
                startActivity(intent2);
                break;
            case R.id.home_book_frame3:
                Intent intent3 = new Intent(activity, BookDetailsActivity.class);
                intent3.putExtra("BookInfo", (Serializable) InfoLists.BInfos.get(2));
                startActivity(intent3);
                break;
            case R.id.leftitem:
                getActivity().onBackPressed();
                break;
        }
    }

}
