package adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by heshu on 2017/9/25.
 */

public class FriendsAdapter  extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> titleList;

    public FriendsAdapter(FragmentManager fm,List<Fragment> fragmentList,List<String>titleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    public CharSequence getPageTitle(int position) {
        return titleList.get(position % titleList.size());
    }
}
