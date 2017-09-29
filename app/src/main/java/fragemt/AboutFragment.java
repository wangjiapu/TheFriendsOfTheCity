package fragemt;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiyou3g.thefriendsofthecity.R;

/**
 * Created by heshu on 2017/9/23.
 * 个人主页--关于
 */

public class AboutFragment extends Fragment {
    private View rootView;
    private TextView signTextview;
    private TextView locationTextview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rootView= inflater.inflate(R.layout.fragment_person_about,container,false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        signTextview = (TextView)rootView.findViewById(R.id.sign_textview);
        locationTextview = (TextView)rootView.findViewById(R.id.location_textview);

        signTextview.setText(" 如果你无法简洁的表达你的想法，那只说明你还不够了解他\n         --阿尔伯特·爱因斯坦");
        locationTextview.setText(" 陕西省西安市");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
