package com.hpuvoice.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class PlayerActivity extends Activity {

    public WebView player_video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_player);
        player_video = (WebView)findViewById(R.id.player_video);
        WebSettings settings = player_video.getSettings();
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setMediaPlaybackRequiresUserGesture(true);
        player_video.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        String VideoUrl = getIntent().getStringExtra("VideoUrl");
        if(VideoUrl != null){
            if(VideoUrl.contains("swf")){
                showProgreeDialog();
            }
            player_video.loadUrl(VideoUrl);
        }else{
            Toast.makeText(this,"网络有问题哦，亲...",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示加载视频的进度条...
     */
    public void showProgreeDialog(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在加载网络视频.....");
        progressDialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player, menu);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
