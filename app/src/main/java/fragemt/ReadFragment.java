package fragemt;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.io.IOException;

import activitys.BookDetailsActivity;
import activitys.BorrowMoreActivity;
import beans.BookInfo;
import beans.InfoLists;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.GlideUtil;
import utils.JsonParserUtil;
import utils.OkhttpUtil;
import utils.SharedPerferenceUtil;


/**
 * Created by 江婷婷 on 2017/9/30.
 */

public class ReadFragment extends Fragment implements View.OnClickListener {

    private TextView more;
    private FrameLayout mFrameLayout1;
    private FrameLayout mFrameLayout2;
    private FrameLayout mFrameLayout3;
    private TextView unlisted;
    private ProgressBar progressBar;
    private LinearLayout display;


    private static int count=0;
    private static synchronized  void addcount(){
        count++;
    }

    private String flag="1";

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==5){
                Log.e("readed",msg.obj.toString());
                JsonParserUtil.getInterestBookList(msg.obj.toString(), 2);
            }
        }


    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] info= SharedPerferenceUtil.getUserInfo(getContext());
        if (info[0].equals("")){
            flag="0";
        } else if (flag == "1"){
            initData();
        }
        Log.d("Gi", "onCreate: " + InfoLists.readedInfos);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_1, container, false);

        Log.d("11", "run: " + InfoLists.readedInfos.isEmpty());
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
        switch (InfoLists.readedInfos.size()) {
            case 3:
                mFrameLayout3.setOnClickListener(this);
            case 2:
                mFrameLayout2.setOnClickListener(this);
            case 1:
                mFrameLayout1.setOnClickListener(this);
                break;
        }

    }

    private void initView(View view) {
        more = (TextView) view.findViewById(R.id.gengduo1);
        mFrameLayout1 = (FrameLayout) view.findViewById(R.id.book_frame1);
        mFrameLayout2 = (FrameLayout) view.findViewById(R.id.book_frame2);
        mFrameLayout3 = (FrameLayout) view.findViewById(R.id.book_frame3);
        unlisted = (TextView) view.findViewById(R.id.book_unlisted);
        progressBar =(ProgressBar)view.findViewById(R.id.book_progress_bar);
        display = (LinearLayout) view.findViewById(R.id.layout);
    }
    private void showInitView() {
        if(flag.equals("1")){
            more.setVisibility(View.VISIBLE);
            display.setVisibility(View.VISIBLE);
            unlisted.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);

            go();

        }else {
            progressBar.setVisibility(View.GONE);
            more.setVisibility(View.GONE);
            display.setVisibility(View.GONE);
            unlisted.setVisibility(View.VISIBLE);
        }
    }




    @Override
    public void onClick(View view) {
        Activity activity = getActivity();
        switch (view.getId()) {
            case R.id.gengduo1:
                Intent intent = new Intent(activity, BorrowMoreActivity.class);
                intent.putExtra("title", "更多");
                startActivity(intent);
                break;
            case R.id.book_frame1:
                Intent intent1 = new Intent(this.getActivity(), BookDetailsActivity.class);
                intent1.putExtra("bookInfoId", InfoLists.BInfos.get(0).getId()+"");
                startActivity(intent1);
                break;
            case R.id.book_frame2:
                Intent intent2 = new Intent(this.getActivity(), BookDetailsActivity.class);
                intent2.putExtra("bookInfoId", InfoLists.BInfos.get(1).getId()+"");
                startActivity(intent2);
                break;
            case R.id.book_frame3:
                Intent intent3 = new Intent(this.getActivity(), BookDetailsActivity.class);
                intent3.putExtra("bookInfoId", InfoLists.BInfos.get(2).getId()+"");
                startActivity(intent3);
                break;
        }
    }


    private void go() {
        addcount();
        Log.d("lml1", "go: " + InfoLists.readedInfos.isEmpty());
        if (count >= 3) {
            progressBar.setVisibility(View.GONE);
            more.setVisibility(View.VISIBLE);
            mFrameLayout1.setVisibility(View.VISIBLE);
            mFrameLayout2.setVisibility(View.VISIBLE);
            mFrameLayout3.setVisibility(View.VISIBLE);
            unlisted.setVisibility(View.GONE);
            if (InfoLists.readedInfos.size()==0) {
                TextView bookName1 = (TextView)mFrameLayout1.findViewById(R.id.book_text_view_name);
                bookName1.setText("空空如也");
                TextView bookName2 = (TextView)mFrameLayout2.findViewById(R.id.book_text_view_name);
                bookName2.setText("空空如也");
                TextView bookName3 = (TextView)mFrameLayout3.findViewById(R.id.book_text_view_name);
                bookName3.setText("空空如也");
            }
            if (InfoLists.readedInfos.size()==1) {
                initLoad(mFrameLayout1, InfoLists.readedInfos.get(0));
                TextView bookName2 = (TextView)mFrameLayout2.findViewById(R.id.book_text_view_name);
                bookName2.setText("空空如也");
                TextView bookName3 = (TextView)mFrameLayout3.findViewById(R.id.book_text_view_name);
                bookName3.setText("空空如也");
            }
            if (InfoLists.readedInfos.size()==2) {
                initLoad(mFrameLayout1, InfoLists.readedInfos.get(0));
                initLoad(mFrameLayout2, InfoLists.readedInfos.get(1));
                TextView bookName3 = (TextView)mFrameLayout3.findViewById(R.id.book_text_view_name);
                bookName3.setText("空空如也");
            }
            if (InfoLists.readedInfos.size() > 2) {

                initLoad(mFrameLayout1, InfoLists.readedInfos.get(0));
                initLoad(mFrameLayout2, InfoLists.readedInfos.get(1));
                initLoad(mFrameLayout3, InfoLists.readedInfos.get(2));
            }

        } else {

            progressBar.setVisibility(View.VISIBLE);
            more.setVisibility(View.GONE);
            mFrameLayout1.setVisibility(View.GONE);
            mFrameLayout2.setVisibility(View.GONE);
            mFrameLayout3.setVisibility(View.GONE);
            unlisted.setVisibility(View.GONE);

        }
    }

    private void initLoad(FrameLayout mFrameLayout, BookInfo bookInfo) {
        ImageView bookImage = (ImageView)mFrameLayout.findViewById(R.id.book_image_view);
        TextView bookName = (TextView)mFrameLayout.findViewById(R.id.book_text_view_name);
        TextView bookAuthor = (TextView)mFrameLayout.findViewById(R.id.book_text_view_author);
        TextView bookMarks = (TextView)mFrameLayout.findViewById(R.id.book_text_view_marks);
        TextView bookDate = (TextView)mFrameLayout.findViewById(R.id.book_text_view_date);


        GlideUtil.loadImag(getActivity(),bookImage,bookInfo.getCoverImg());
        bookName.setText(bookInfo.getBookName());
        bookAuthor.setText(bookInfo.getAuthor());
        bookMarks.setText(bookInfo.getGmtModified());
        bookDate.setText(bookInfo.getGmtCreate());

    }
    private void initData() {


        OkhttpUtil.requestReadedBook("3").enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendMessage("readed",5);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendMessage(response.body().string(),5);
            }
        });
    }
    private void sendMessage(String s,int f){
        Message m=new Message();
        m.what=f;
        m.obj=s;
        handler.sendMessage(m);
    }

}