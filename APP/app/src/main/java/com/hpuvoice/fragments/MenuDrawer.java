package com.hpuvoice.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import com.hpuvoice.activitys.AboutActivity;
import com.hpuvoice.adapters.DrawerAdapter;
import com.hpuvoice.app.R;
import java.util.ArrayList;
import java.util.List;


public class MenuDrawer extends Fragment {

  private DrawerAdapter adapter;
  private List<String> mList;
  private SharedPreferences userInfo;

  public MenuDrawer() {
  }


  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_menu_drawer, container, false);
    ListView listView = (ListView)rootView.findViewById(R.id.meun_drawer_lv);

    mList = new ArrayList<String>();
      mList.add("");
      mList.add("~~发 现~~");
      mList.add("~~公开课~~");
      mList.add("~~学霸区~~");
      mList.add("~~考试答案~~");
    adapter = new DrawerAdapter(getActivity(),mList);
    listView.setAdapter(adapter);
    
    listView.setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
          long arg3) {
          if(arg2 == 0) {
              initDialog(getActivity());
          }
          if(arg2 == 1) {
              Intent intent = new Intent(getActivity(), AboutActivity.class);
              getActivity().startActivity(intent);
              getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
          }
      }
    });
    return rootView;
  }
  
  public void initDialog(final Activity a) {
    View connectDialog = a.getLayoutInflater().inflate(R.layout.menu_item_user_modify, null);
    final EditText editTextName = (EditText)connectDialog.findViewById(R.id.et_item_user_name);
    final EditText editTextTips = (EditText)connectDialog.findViewById(R.id.et_item_user_name);

    new AlertDialog.Builder(a)
    .setView(connectDialog)
    .setPositiveButton("修改",
        new DialogInterface.OnClickListener() {

          @Override
          public void onClick(DialogInterface dialog, int which) {
           String nameMd = editTextName.getText().toString();
           String tipsMd = editTextTips.getText().toString();
          }
        }).setNegativeButton("取消", null).show();
  }

}
