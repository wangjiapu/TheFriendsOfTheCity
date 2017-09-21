package activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.StaticLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.xiyou3g.thefriendsofthecity.R;

import beans.StaticString;
import fragemt.BookFragment;
import fragemt.HomeFragment;
import fragemt.MessageFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mNavigation;
    private Fragment mHomeFragment = new HomeFragment();
    private Fragment mBookFragment = new BookFragment();
    private Fragment mMessageFragment = new MessageFragment();
    private Fragment mNowFragment = null;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                localLayoutParams.flags);

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
            startIntent(StaticString.COLLETION);
        } else if (id == R.id.nav_friends) {
            startIntent(StaticString.FRIENDS);
        } else if (id == R.id.nav_colse) {
            startIntent(StaticString.COLSE);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

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


}
