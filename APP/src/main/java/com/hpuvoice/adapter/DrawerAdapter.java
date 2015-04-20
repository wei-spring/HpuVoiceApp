package com.hpuvoice.adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.hpuvoice.app.R;
import java.util.List;

public class DrawerAdapter extends BaseAdapter {

  private Activity mActivity;
  private LayoutInflater mInflater;
  private List<String> mList;
  private Typeface typeface;
  private SharedPreferences userInfo;

  public DrawerAdapter(Activity a, List<String> list) {
      mActivity = a;
      typeface = Typeface.createFromAsset(a.getAssets(), "font/Roboto-Light.ttf");
      mInflater = LayoutInflater.from(a);
      mList = list;
      userInfo = a.getSharedPreferences("user_info", 0);
  }

  @Override
  public int getCount() {
    return mList.size()+1;
  }

  @Override
  public Object getItem(int position) {
    return mList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if(position == 0) {
      Holder1 holder;
      if(convertView == null) {
        convertView = mInflater.inflate(R.layout.menu_item_user, null);
        holder = new Holder1();
        holder.userAvatar = (ImageView)convertView.findViewById(R.id.item_user_avatar);
        holder.item_user_name = (TextView)convertView.findViewById(R.id.item_user_name);
        holder.item_user_tips = (TextView)convertView.findViewById(R.id.item_user_tips);
        convertView.setTag(holder);
      } else {
        holder = (Holder1)convertView.getTag();
      }

      if(userInfo.getString("avatar_url", "") == "") {

      } else {
          /*===== 设置图片，加载用户的头像 =====*/
       // holder.userAvatar.setImageUrl(userInfo.getString("avatar_url", ""), mImageLoader);
      }
      //holder.userInfo1.setText(userInfo.getString("name", "Tap to connect"));
      holder.item_user_name.setText("遗忘之海");
      holder.item_user_name.setTypeface(typeface);
      //holder.userInfo2.setText(userInfo2);
      holder.item_user_tips.setText("Lv_super 版主");
      holder.item_user_tips.setTypeface(typeface);

    } else {
      Holder2 holder;
      if (convertView == null) {
        convertView = mInflater.inflate(R.layout.menu_item_others, null);
        holder = new Holder2();
        holder.menu_item_other_text = (TextView)convertView.findViewById(R.id.menu_item_other_text);
        holder.iv_menu_item = (ImageView)convertView.findViewById(R.id.iv_menu_item);
        convertView.setTag(holder);
      } else {
        holder = (Holder2)convertView.getTag();
      }

      holder.menu_item_other_text.setText(mList.get(position-1));
      holder.menu_item_other_text.setTypeface(typeface);

    }
    return convertView;
  }

  private static class Holder1 {
    ImageView userAvatar;
    TextView item_user_name;
    TextView item_user_tips;
  }

  private static class Holder2 {
    TextView menu_item_other_text;
    ImageView iv_menu_item;
  }

}
