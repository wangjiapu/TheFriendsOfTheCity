package fragemt;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapters.BookAdapter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.JsonParserUtil;
import utils.OkhttpUtil;

/**
 * Created by xiyou3g on 2017/9/17.
 * 书架
 */

public class BookFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BookAdapter bookAdapter;
    private List<Fragment> fragmentList;
    private List<String> titleList =Arrays.asList("已借阅", "已读完", "已收藏");

    private BorrowedFragment bFragment;
    private ReadFragment rFragment;
    private CollectedFragment cFragment;

    private ImageView mLeftmenu;




    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.fragment_book,container,false);

        initView(rootView);

        return rootView;
    }



    public void fresh()
    {
        bookAdapter.notifyDataSetChanged();
    }

    private void initView(View view) {
        viewPager = (ViewPager)view.findViewById(R.id.book_vp);
        tabLayout = (TabLayout)view.findViewById(R.id.book_tab);
        mLeftmenu=view.findViewById(R.id.leftitem);
        bFragment = new BorrowedFragment();
        rFragment = new ReadFragment();
        cFragment = new CollectedFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(bFragment);
        fragmentList.add(rFragment);
        fragmentList.add(cFragment);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(2)));

        bookAdapter = new BookAdapter(getActivity().getSupportFragmentManager(), fragmentList, titleList);

        viewPager.setAdapter(bookAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLeftmenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.leftitem:
                getActivity().onBackPressed();
                break;
        }
    }
}
