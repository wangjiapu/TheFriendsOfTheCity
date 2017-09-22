package fragemt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
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

import activitys.MainActivity;
import activitys.PersonActivity;

/**
 * Created by xiyou3g on 2017/9/19.
 * 个人主页
 */

public class PersonFragment extends Fragment{
    private View rootView;
    public static final String FRUIT_NAME = "fruit_name";

    public static final String FRUIT_IMAGE_ID = "fruit_image_id";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_person,container,false);
        Toolbar toolbar =(Toolbar)rootView.findViewById(R.id.peraon_toolbar_view);
        ((PersonActivity) getActivity()).setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbar= (CollapsingToolbarLayout) rootView.findViewById(R.id.peraon_toolbar);

        TextView peraonContentText = (TextView) rootView.findViewById(R.id.peraon_content_text);
        ((PersonActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        collapsingToolbar.setTitle(" ");
        setHasOptionsMenu(true);

        peraonContentText.setText("个人主页12345678987654323456789876542345678987654323456789");
        return rootView;
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
