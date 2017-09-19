package fragemt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiyou3g.thefriendsofthecity.R;

import activitys.BookDetailsActivity;
import activitys.CollectionMoreActivity;

/**
 * Created by 江婷婷 on 2017/9/20.
 */

public class CollectedFragment extends Fragment implements View.OnClickListener {

    private TextView more;
    private ImageView mImageView1;
    private ImageView mImageView2;
    private ImageView mImageView3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_3, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initBind();
    }

    private void initBind() {

        more.setOnClickListener(this);
        mImageView1.setOnClickListener(this);
        mImageView2.setOnClickListener(this);
        mImageView3.setOnClickListener(this);
    }

    private void initView(View view) {
        more = (TextView) view.findViewById(R.id.gengduo3);
        mImageView1 = (ImageView) view.findViewById(R.id.book3_1);
        mImageView2 = (ImageView) view.findViewById(R.id.book3_2);
        mImageView3 = (ImageView) view.findViewById(R.id.book3_3);
    }

    @Override
    public void onClick(View view) {
        Activity activity = getActivity();
        switch (view.getId()) {
            case R.id.gengduo3:
                Intent intent1 = new Intent(activity, CollectionMoreActivity.class);
                startActivity(intent1);
                break;
            case R.id.book3_1:
                Intent intent2 = new Intent(activity, BookDetailsActivity.class);
                startActivity(intent2);
                break;
            case R.id.book3_2:
                Intent intent3 = new Intent(activity, BookDetailsActivity.class);
                startActivity(intent3);
                break;
            case R.id.book3_3:
                Intent intent4 = new Intent(activity, BookDetailsActivity.class);
                startActivity(intent4);
                break;

        }
    }
}