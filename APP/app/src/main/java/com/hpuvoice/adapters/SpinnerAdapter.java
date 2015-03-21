package com.hpuvoice.adapters;

import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hpuvoice.app.R;

import java.util.List;

/**
 * Created by wei-spring on 2015/3/21.
 * <p/>
 * the talking object adapter
 */
public class SpinnerAdapter extends BaseAdapter {

    private int[] itemLogo;
    private List<String> itemName;
    private Context mContext;

    public SpinnerAdapter(int[] itemLogo, List<String> itemName, Context mContext) {
        this.itemLogo = itemLogo;
        this.itemName = itemName;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return itemName.size();
    }

    @Override
    public Object getItem(int position) {
        return itemName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(R.layout.item_talking_object, null);
        if (convertView != null) {
            com.hpuvoice.views.CircleImageView item_iv_talk_logo = (com.hpuvoice.views.CircleImageView) convertView.findViewById(R.id.item_iv_talk_logo);
            item_iv_talk_logo.setImageResource(itemLogo[position]);
            TextView item_tv_talk_name = (TextView) convertView.findViewById(R.id.item_tv_talk_name);
            TextPaint tp = item_tv_talk_name.getPaint();
            tp.setFakeBoldText(true);
            item_tv_talk_name.setText(itemName.get(position));
        }
        return convertView;
    }
}
