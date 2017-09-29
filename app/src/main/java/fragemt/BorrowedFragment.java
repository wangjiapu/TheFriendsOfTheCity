package fragemt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiyou3g.thefriendsofthecity.R;

import activitys.BookDetailsActivity;
import activitys.BorrowMoreActivity;
import activitys.MainActivity;

/**
 * Created by 江婷婷 on 2017/9/20.
 * 书架子项
 */

public class BorrowedFragment extends Fragment implements View.OnClickListener {


    private TextView more;
    private FrameLayout mFrameLayout1;
    private FrameLayout mFrameLayout2;
    private FrameLayout mFrameLayout3;
    private TextView unlisted;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_1, container, false);
        initView(view);
        showInitView();
        return view;
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
    }

    private void initView(View view) {
        more = (TextView) view.findViewById(R.id.gengduo1);
        mFrameLayout1 = (FrameLayout) view.findViewById(R.id.book_frame1);
        mFrameLayout2 = (FrameLayout) view.findViewById(R.id.book_frame2);
        mFrameLayout3 = (FrameLayout) view.findViewById(R.id.book_frame3);
        unlisted = (TextView) view.findViewById(R.id.book_unlisted);
    }
    private void showInitView() {
        String f=MainActivity.getFlag();
        if(f.equals("1")){
            more.setVisibility(View.VISIBLE);
            mFrameLayout1.setVisibility(View.VISIBLE);
            mFrameLayout2.setVisibility(View.VISIBLE);
            mFrameLayout3.setVisibility(View.VISIBLE);
            unlisted.setVisibility(View.GONE);
        }else {
            more.setVisibility(View.GONE);
            mFrameLayout1.setVisibility(View.GONE);
            mFrameLayout2.setVisibility(View.GONE);
            mFrameLayout3.setVisibility(View.GONE);
            unlisted.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        Activity activity = getActivity();
        switch (view.getId()) {
            case R.id.gengduo1:
                Intent intent1 = new Intent(activity, BorrowMoreActivity.class);
                intent1.putExtra("title", "更多");
                startActivity(intent1);
                break;
            case R.id.book_frame1:
            case R.id.book_frame2:
            case R.id.book_frame3:
                Intent intent = new Intent(activity, BookDetailsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
