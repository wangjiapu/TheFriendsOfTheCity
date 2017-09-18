package activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.xiyou3g.thefriendsofthecity.R;

import fragemt.BookFragment;
import fragemt.HomeFragment;
import fragemt.MessageFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mNavigation;
    private Fragment mHomeFragment =new HomeFragment();
    private Fragment mBookFragment = new BookFragment();
    private Fragment mMessageFragment = new MessageFragment();
    private Fragment mNowFragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            hideFragment();
            switch (item.getItemId()){
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mNavigation = (BottomNavigationView) findViewById(R.id.navigation_bar);
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragment();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this,item.getItemId(),Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_person) {
            Intent personIntent=new Intent(MainActivity.this,PersonActivity.class);
            personIntent.putExtra("key","个人主页7777777777");
            startActivity(personIntent);
            Toast.makeText(this,"个人主页",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_colletion) {
            Intent personIntent=new Intent(MainActivity.this,PersonActivity.class);
            personIntent.putExtra("key","我的收藏");
            Toast.makeText(this,"我的收藏",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_friends) {
            Intent personIntent=new Intent(MainActivity.this,PersonActivity.class);
            personIntent.putExtra("key","我的好友");
            Toast.makeText(this,"我的好友",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_colse) {
            Intent personIntent=new Intent(MainActivity.this,PersonActivity.class);
            personIntent.putExtra("key","附近的人");
            Toast.makeText(this,"附近的人",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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


}
