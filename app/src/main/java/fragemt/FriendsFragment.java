package fragemt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapters.BookAdapter;
import adapters.FriendsAdapter;

/**
 * Created by xiyou3g on 2017/9/19.
 * 我的好友
 */

public class FriendsFragment extends Fragment{
    private View rootView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FriendsAdapter friendsAdapter;
    private List<Fragment> fragmentList;
    private List<String> titleList = Arrays.asList("全部关注","最新发布");

    private FriendListFragment whloleFriendListFragment;
    private FriendListFragment newFriendListFragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_friends,container,false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        viewPager = (ViewPager)rootView.findViewById(R.id.frienfs_viewpager);
        tabLayout = (TabLayout)rootView.findViewById(R.id.frienfs_tablayout);

        fragmentList = new ArrayList<>();
        whloleFriendListFragment = new FriendListFragment();
        newFriendListFragment = new FriendListFragment();
        fragmentList.add(whloleFriendListFragment);
        fragmentList.add(newFriendListFragment);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));

        friendsAdapter = new FriendsAdapter(getActivity().getSupportFragmentManager(), fragmentList, titleList);

        viewPager.setAdapter(friendsAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
