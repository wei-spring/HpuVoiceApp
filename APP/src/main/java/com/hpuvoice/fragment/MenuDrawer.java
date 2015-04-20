package com.hpuvoice.fragment;

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

import com.hpuvoice.adapter.DrawerAdapter;
import com.hpuvoice.app.ChatActivity;
import com.hpuvoice.app.R;
import com.hpuvoice.app.ShowMenuItemActivity;

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
        ListView listView = (ListView) rootView.findViewById(R.id.meun_drawer_lv);

        mList = new ArrayList<String>();
        mList.add(" 聊天 ");
        mList.add(" 天气 ");//http://m.hao123.com/a/tianqi
        mList.add(" 旅游 ");//http://m.tuniu.com/
        mList.add(" 健康 ");//http://m.soujibing.com/
        mList.add(" 星座 ");//http://www.nbbltv.com/tool/#search
        mList.add("二手物品");//http://wap.58.com/w/sale/?refrom=m58&refrom=m58
        mList.add("心理测试");//http://infoapp.3g.qq.com/g/s?aid=astro&g_ut=3&g_f=20585#toplist?tab=new
        mList.add("附近美食");//http://map.baidu.com/mobile/webapp/search/search/qt=s&wd=%E7%BE%8E%E9%A3%9F/needloc=1&viewmode=no_ad/?third_party=ucsearchbox
        mList.add("运动减肥");//http://m.39.net/fitness/ydjf/index.html
        mList.add("搞笑漫画");//http://manhua.yicha.cn/manhua/

        adapter = new DrawerAdapter(getActivity(), mList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent_show_menu = new Intent(getActivity(), ShowMenuItemActivity.class);
                switch (arg2) {
                    case 0:
                        initDialog(getActivity());
                        break;
                    case 1:
                        Intent intent = new Intent(getActivity(), ChatActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    case 2:
                        intent_show_menu.putExtra("MenuItemUrl", "http://m.hao123.com/a/tianqi");
                        getActivity().startActivity(intent_show_menu);
                        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    case 3:
                        intent_show_menu.putExtra("MenuItemUrl", "http://m.tuniu.com/");
                        getActivity().startActivity(intent_show_menu);
                        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    case 4:
                        intent_show_menu.putExtra("MenuItemUrl", "http://m.soujibing.com/");
                        getActivity().startActivity(intent_show_menu);
                        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    case 5:
                        intent_show_menu.putExtra("MenuItemUrl", "http://www.nbbltv.com/tool/#search");
                        getActivity().startActivity(intent_show_menu);
                        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    case 6:
                        intent_show_menu.putExtra("MenuItemUrl", "http://wap.58.com/w/sale/?refrom=m58&refrom=m58");
                        getActivity().startActivity(intent_show_menu);
                        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    case 7:
                        intent_show_menu.putExtra("MenuItemUrl", "http://infoapp.3g.qq.com/g/s?aid=astro&g_ut=3&g_f=20585#toplist?tab=new");
                        getActivity().startActivity(intent_show_menu);
                        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    case 8:
                        intent_show_menu.putExtra("MenuItemUrl", "http://map.baidu.com/mobile/webapp/search/search/qt=s&wd=%E7%BE%8E%E9%A3%9F/needloc=1&viewmode=no_ad/?third_party=ucsearchbox");
                        getActivity().startActivity(intent_show_menu);
                        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    case 9:
                        intent_show_menu.putExtra("MenuItemUrl", "http://m.39.net/fitness/ydjf/index.html");
                        getActivity().startActivity(intent_show_menu);
                        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    case 10:
                        intent_show_menu.putExtra("MenuItemUrl", "http://manhua.yicha.cn/manhua/");
                        getActivity().startActivity(intent_show_menu);
                        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                }
            }
        });
        return rootView;
    }

    public void initDialog(final Activity a) {
        View connectDialog = a.getLayoutInflater().inflate(R.layout.menu_item_user_modify, null);
        final EditText editTextName = (EditText) connectDialog.findViewById(R.id.et_item_user_name);
        final EditText editTextTips = (EditText) connectDialog.findViewById(R.id.et_item_user_name);

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
