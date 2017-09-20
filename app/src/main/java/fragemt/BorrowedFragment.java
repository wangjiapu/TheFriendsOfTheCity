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

import activitys.BookDetails;
import activitys.More1;

/**
 * Created by 江婷婷 on 2017/9/20.
 */

public class BorrowedFragment extends Fragment implements View.OnClickListener{


    TextView more;
    ImageView mImageView1;
    ImageView mImageView2;
    ImageView mImageView3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_1, container, false);

        more = (TextView) view.findViewById(R.id.gengduo1);
        mImageView1 = (ImageView) view.findViewById(R.id.book1_1);
        mImageView2 = (ImageView) view.findViewById(R.id.book1_2);
        mImageView3 = (ImageView) view.findViewById(R.id.book1_3);
        more.setOnClickListener(this);
        mImageView1.setOnClickListener(this);
        mImageView2.setOnClickListener(this);
        mImageView3.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        Activity activity = getActivity();
        switch (view.getId()) {
            case R.id.gengduo1:
                Intent intent1 = new Intent(activity, More1.class);
                startActivity(intent1);
                break;
            case R.id.book1_1:
                Intent intent2 = new Intent(activity, BookDetails.class);
                startActivity(intent2);
                break;
            case R.id.book1_2:
                Intent intent3 = new Intent(activity, BookDetails.class);
                startActivity(intent3);
                break;
            case R.id.book1_3:
                Intent intent4 = new Intent(activity, BookDetails.class);
                startActivity(intent4);
                break;

        }
    }
}
