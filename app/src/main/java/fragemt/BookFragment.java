package fragemt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapters.BookAdapter;

/**
 * Created by xiyou3g on 2017/9/17.
 *
 */

public class BookFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BookAdapter bookAdapter;
    private List<Fragment> fragmentList;
    private List<String> titleList =Arrays.asList("已借阅", "已读完", "已收藏");

    private BorrowedFragment bFragment;
    private BorrowedFragment rFragment;
    private BorrowedFragment cFragment;

    private ImageView mLeftmenu;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_book,container,false);

        initView(rootView);

        return rootView;
    }

    private void initView(View view) {
        viewPager = (ViewPager)view.findViewById(R.id.book_vp);
        tabLayout = (TabLayout)view.findViewById(R.id.book_tab);
        mLeftmenu=view.findViewById(R.id.leftitem);
        bFragment = new BorrowedFragment();
        rFragment = new BorrowedFragment();
        cFragment = new BorrowedFragment();
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
