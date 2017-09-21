package activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.xiyou3g.thefriendsofthecity.R;
import com.jude.swipbackhelper.SwipeBackHelper;

/**
 * Created by PUJW on 2017/9/19.
 * 侧滑关闭
 */

public abstract class SwipeCloseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(true)
                .setSwipeSensitivity(0.3f)
                .setSwipeEdge((int) getResources().getDimension(R.dimen.swipe_back_edge))
                .setSwipeRelateEnable(true)
                .setSwipeRelateOffset(300);

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        SwipeBackHelper.onDestroy(this);
        super.onDestroy();
    }
}
