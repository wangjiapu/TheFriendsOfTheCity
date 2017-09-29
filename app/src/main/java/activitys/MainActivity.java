package activitys;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.text.StaticLayout;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.xiyou3g.thefriendsofthecity.R;

import beans.StaticString;
import beans.UserInfo;
import fragemt.BookFragment;
import fragemt.HomeFragment;
import fragemt.MessageFragment;
import utils.GlideUtil;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int RESULT=1;

    private BottomNavigationView mNavigation;
    private NavigationView navigationView;
    private Fragment mHomeFragment = new HomeFragment();
    private BookFragment mBookFragment = new BookFragment();
    private Fragment mMessageFragment = new MessageFragment();
    private Fragment mNowFragment = null;

    private View mHeaderView;
    private TextView mUserName;
    private TextView mUserSig;

    private  static String flag;

    public static String getFlag() {
        return flag;
    }

    private ImageView mLoginImage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            hideFragment();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment(mHomeFragment);
                    return true;
                case R.id.navigation_book:
                    showFragment(mBookFragment);
                    return true;
                case R.id.navigation_message:
                    showFragment(mMessageFragment);
                    return true;
            }
            return false;
        }

    };

    public LocalBroadcastManager mLocalBroadcastManager;
    public LocalReceive mLocalReceive;

    class LocalReceive extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            showMenuItem(navigationView.getMenu(), true);
            Toast.makeText(context ,"1111", Toast.LENGTH_SHORT).show();
            Log.d("111", "onReceive: 111");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mLocalReceive);
    }

    public void fresh()
    {
        mBookFragment.fresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcast.LOGIN");
        mLocalReceive = new LocalReceive();
        mLocalBroadcastManager.registerReceiver(mLocalReceive, intentFilter);

        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                localLayoutParams.flags);

        setContentView(R.layout.activity_main);

        flag=getIntent().getStringExtra("isLogin");
        initView();
        initFragment();

        if (flag.equals("1")){
            GlideUtil.loadImag(this,mLoginImage, UserInfo.getFaviconUrl());
            mUserName.setText(UserInfo.getUserName());
            mUserSig.setText(UserInfo.getSignature());
            showMenuItem(navigationView.getMenu(),true);
        }
        mLoginImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent loginIntent=new Intent(MainActivity.this,LoginActivity.class);
               startActivityForResult(loginIntent,RESULT);
           }
       });
    }

    private void initView() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        showMenuItem(navigationView.getMenu(),false);
        navigationView.setNavigationItemSelectedListener(this);


        mNavigation = (BottomNavigationView) findViewById(R.id.navigation_bar);
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mHeaderView=navigationView.getHeaderView(0);
        mLoginImage=mHeaderView.findViewById(R.id.imageView);
        mUserName=mHeaderView.findViewById(R.id.username);
        mUserSig=mHeaderView.findViewById(R.id.usersigna);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getItemId(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_person) {
            startIntent(StaticString.PERSON);
        } else if (id == R.id.nav_colletion) {
            Intent intent = new Intent(this, BorrowMoreActivity.class);
            intent.putExtra("title","我的收藏");
            startActivity(intent);
//            startIntent(StaticString.COLLETION);
        } else if (id == R.id.nav_friends) {
            startIntent(StaticString.FRIENDS);
        } else if (id == R.id.nav_colse) {
            startIntent(StaticString.COLSE);

        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "休息一下", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "还没有休息好", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startIntent(String flag) {
        Intent intent = new Intent(MainActivity.this, PersonActivity.class);
        intent.putExtra("key", flag);
        startActivity(intent);
    }

    public void hideFragment() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(mHomeFragment)
                .hide(mBookFragment)
                .hide(mMessageFragment)
                .commitNow();
    }

    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .show(fragment)
                .commitNow();
        mNowFragment = fragment;
    }

    public void initFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, mHomeFragment)
                .add(R.id.content, mBookFragment)
                .add(R.id.content, mMessageFragment)
                .commitNow();
        getSupportFragmentManager()
                .beginTransaction()
                .hide(mBookFragment)
                .hide(mMessageFragment)
                .commitNow();
        mNowFragment = mHomeFragment;
    }

    /**
     *  是否显示菜单前四项
     * @param menu
     * @param b
     */
    private void showMenuItem(Menu menu,boolean b) {
            for (int i=0;i<4;i++){
                menu.getItem(i).setVisible(b);
            }
            menu.getItem(4).setVisible(!b);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }
}
