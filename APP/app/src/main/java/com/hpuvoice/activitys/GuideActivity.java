package com.hpuvoice.activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.hpuvoice.animation.DepthPageTransformer;
import com.hpuvoice.app.R;
import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener{

    private TextView guidePageNum;
    private ViewPager guide_vp;
    private List<View> views;
    private ViewPagerAdapter vpAdapter;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        inflater = LayoutInflater.from(this);
        initViews();
        initPageNum();
    }

    private void initViews() {
        views = new ArrayList<View>();

        views.add(inflater.inflate(R.layout.guide_views_one, null));
        views.add(inflater.inflate(R.layout.guide_views_two, null));
        views.add(inflater.inflate(R.layout.guide_views_three, null));
        views.add(inflater.inflate(R.layout.guide_views_four, null));

        vpAdapter = new ViewPagerAdapter(views, this);

        guide_vp = (ViewPager)findViewById(R.id.guide_viewpager);
        guide_vp.setPageTransformer(true, new DepthPageTransformer());
        guide_vp.setAdapter(vpAdapter);
        guide_vp.setOnPageChangeListener(this);
    }

    private void initPageNum() {
        guidePageNum = (TextView)findViewById(R.id.guide_page_num);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/Roboto-Thin.ttf");
        guidePageNum.setTypeface(typeface);
        guidePageNum.setText("");
    }

    public class ViewPagerAdapter extends PagerAdapter {

        private List<View> views;
        private Activity activity;

        public ViewPagerAdapter(List<View> views, Activity activity){
            this.views = views;
            this.activity = activity;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(views.get(arg1));
        }

        @Override
        public int getCount() {

            if(views != null){
                return views.size();
            }

            return 0;
        }
        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(views.get(arg1),0);
            if(arg1 == 0){
                AnimationSet animationSet = new AnimationSet(true);
                Animation alphaAnimation = AnimationUtils.loadAnimation(GuideActivity.this, R.anim.alpha_1);
                Animation tAnimation = AnimationUtils.loadAnimation(GuideActivity.this, R.anim.trans_1);
                animationSet.addAnimation(alphaAnimation);
                animationSet.addAnimation(tAnimation);
                ImageView imageView = (ImageView)arg0.findViewById(R.id.first_image);
                imageView.startAnimation(animationSet);
            }
            if(arg1 == views.size() - 1) {
                Button mStart = (Button)arg0.findViewById(R.id.mstart);

                mStart.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        setGuided();
                        goHome();
                    }
                });
            }
            return views.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }

        public void goHome() {
            Intent intent = new Intent(activity, ContentActivity.class);
            activity.startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            activity.finish();
        }

        public void setGuided() {
            SharedPreferences preferences = getSharedPreferences("HP_Save", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirst", false);
            editor.commit();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
