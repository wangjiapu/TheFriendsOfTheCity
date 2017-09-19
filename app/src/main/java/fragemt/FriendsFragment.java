package fragemt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiyou3g.thefriendsofthecity.R;

/**
 * Created by xiyou3g on 2017/9/19.
 * 个人主页
 */

public class FriendsFragment extends Fragment{
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_friends,container,false);
        return rootView;
    }
}
