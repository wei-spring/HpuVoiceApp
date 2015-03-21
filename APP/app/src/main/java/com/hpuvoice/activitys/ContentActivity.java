package com.hpuvoice.activitys;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.hpuvoice.animation.ZoomOutPageTransformer;
import com.hpuvoice.app.R;
import com.hpuvoice.fragments.MenuDrawer;
import com.hpuvoice.fragments.ShowFragment;
import com.hpuvoice.views.PagerSlidingTabStrip;

public class ContentActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private ViewPager contentPager;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private mPagerAdapter adapter;
    private PagerSlidingTabStrip tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_content);

        setPager();
        setActionBarStyle();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.content_drawer);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
            }

            public void onDrawerOpened(View drawerView) {
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.left_drawer, new MenuDrawer())
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            contentPager.setAdapter(adapter);
            return true;
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void setPager() {
        contentPager = (ViewPager) findViewById(R.id.content_pager);
        contentPager.setOnPageChangeListener(this);
        adapter = new mPagerAdapter(getSupportFragmentManager());
        contentPager.setAdapter(adapter);
        contentPager.setOffscreenPageLimit(2);
        contentPager.setPageTransformer(true, new ZoomOutPageTransformer());
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(contentPager);
    }

    private class mPagerAdapter extends FragmentStatePagerAdapter {

        private String Title[] = {"  图灵世界    ", "   知乎  ", "   CSDN   ","   HPU   "};

        public mPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            return new ShowFragment();
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Title[position];
        }

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int position) {
        getActionBar().setSelectedNavigationItem(position);
    }

    private void setActionBarStyle() {
        getActionBar().setTitle("HpuVoice");
        getActionBar().setBackgroundDrawable(this.getBaseContext().getResources().getDrawable(R.drawable.actionbar_back));
        getActionBar().setIcon(R.drawable.ic_action);
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
        TextView textView = (TextView) findViewById(titleId);
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/Wendy.ttf"));
        textView.setTextColor(0xFFdfdfdf);
        textView.setTextSize(32);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

}

