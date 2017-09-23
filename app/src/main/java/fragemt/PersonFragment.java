package fragemt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiyou3g.thefriendsofthecity.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import activitys.MainActivity;
import activitys.PersonActivity;
import adapters.PersonPagerAdater;


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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_person,container,false);

        initView(rootView);

         return rootView;
    }

    private void initView(View rootView) {
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

        setHasOptionsMenu(true);
        ((PersonActivity) getActivity()).setSupportActionBar(toolbar);
        collapsingToolbar.setTitle(" ");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.person_main, menu);
    }

}
