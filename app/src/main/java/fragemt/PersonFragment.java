package fragemt;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.xiyou3g.thefriendsofthecity.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import activitys.EditorActivity;
import activitys.MainActivity;
import activitys.PersonActivity;
import adapters.PersonPagerAdater;
import beans.UserInfo;
import utils.GlideUtil;


/**
 * Created by xiyou3g on 2017/9/19.
 * 个人主页
 */

public class PersonFragment extends Fragment{
    private View rootView;

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;
    private ViewPager viewPager;
    private List<String> titleList = Arrays.asList("关于", "发布");
    private AboutFragment aFragment;
    private ReleaseFragment rFragment;
    private PersonPagerAdater personPagerAdapter;

    private TextView username;
    private TextView follow;
    private ImageView touxiang;
    private Button guanzhu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_person,container,false);

        initView(rootView);

         return rootView;
    }

    private void initView(View rootView) {

        touxiang=rootView.findViewById(R.id.persom_head);
        GlideUtil.loadImag(getActivity(),touxiang, UserInfo.getFaviconUrl());
        username=rootView.findViewById(R.id.user_name);
        username.setText(UserInfo.getUserName());
        follow=rootView.findViewById(R.id.person_follow);
        follow.setText("关注"+UserInfo.getAttentionNum()+"|粉丝"+UserInfo.getFansNum());

        guanzhu=rootView.findViewById(R.id.person_follow_button);
        toolbar =(Toolbar)rootView.findViewById(R.id.peraon_toolbar_view);
        collapsingToolbar = (CollapsingToolbarLayout) rootView.findViewById(R.id.peraon_toolbar);

        tabLayout = (TabLayout) rootView.findViewById(R.id.peraon_tablayout);
        viewPager = (ViewPager) rootView.findViewById(R.id.peraon_viewpager);

        aFragment = new AboutFragment();
        rFragment = new ReleaseFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(aFragment);
        fragmentList.add(rFragment);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));


        personPagerAdapter = new PersonPagerAdater(getActivity().getSupportFragmentManager(), fragmentList, titleList);

        viewPager.setAdapter(personPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tabLayout,60,60);
            }
        });

        setHasOptionsMenu(true);
        ((PersonActivity) getActivity()).setSupportActionBar(toolbar);
        collapsingToolbar.setTitle(" ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_person_edit:
                Toast.makeText(getActivity(),"点击编辑",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), EditorActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_person_search:
                Toast.makeText(getActivity(),"点击搜索",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.person_main, menu);
    }

    /**
     * 改变下划线的长度
     * @param tabs
     * @param leftDip
     * @param rightDip
     */
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

}
