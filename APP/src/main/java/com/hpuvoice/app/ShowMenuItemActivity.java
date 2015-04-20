package com.hpuvoice.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Toast;

public class ShowMenuItemActivity extends Activity {

    private WebView wv_show_menu_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_menu_item);
        wv_show_menu_item = (WebView)findViewById(R.id.wv_show_menu_item);

        String MenuItemUrl = getIntent().getStringExtra("MenuItemUrl");
        if(MenuItemUrl != null){
            wv_show_menu_item.loadUrl(MenuItemUrl);
        }else{
            Toast.makeText(this, "网络有问题哦，亲...", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * 返回按钮
     * @param view
     */
    public void back(View view)
    {
        finish();
    }
}
