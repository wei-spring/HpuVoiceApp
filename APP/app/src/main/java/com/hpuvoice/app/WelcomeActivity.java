package com.hpuvoice.app;

import com.hpuvoice.activitys.ContentActivity;
import com.hpuvoice.activitys.GuideActivity;
import com.hpuvoice.app.util.SystemUiHider;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class WelcomeActivity extends Activity {


    private static final int GO_HOME = 100;
    private static final int GO_GUIDE = 200;
    boolean isFirst = true;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_welcome);
        init();
    }

    private void init() {
        SharedPreferences preferences = getSharedPreferences("HP_Save", MODE_PRIVATE);
        isFirst = preferences.getBoolean("isFirst", true);
        if(isFirst) {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, 150);

        } else {

            mHandler.sendEmptyMessageDelayed(GO_HOME, 150);
        }
    }

    private void goHome() {
        Intent intent = new Intent(WelcomeActivity.this, ContentActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        this.finish();
    }

    private void goGuide() {
        Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        this.finish();
    }

}
