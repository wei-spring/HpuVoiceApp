package com.hpuvoice.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.hpuvoice.app.PlayerActivity;
import com.hpuvoice.app.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenclassFragment extends Fragment {


    public OpenclassFragment() {
        // Required empty public constructor
    }


    private String xiquUrl = "http://swf.ws.126.net/openplayer/v01/-0-2_M7GF0NQE4_M7IF9EJEF-vimg1_ws_126_net//image/snapshot_movie/2011/11/H/S/M7IF9EGHS-1421720937445.swf";
    private String yishuUrl = "http://v.163.com/special/openclass/wenyifeng04.html";
    private String wenyiUrl = "http://v.163.com/special/openclass/wenyifeng07.html";
    private String itdalaoUrl = "http://v.163.com/special/openclass/tamenshuo04.html";
    private String huanbaoUrl = "http://v.163.com/special/openclass/wanwusheng09.html";
    private String guojimingxiaoUrl = "http://open.163.com/ocw/";
    private String zhongguodaxueUrl = "http://open.163.com/cuvocw/";
    private String kehanxueyuanUrl = "http://open.163.com/khan/";
    private String courderaUrl = "http://c.open.163.com/coursera/home.htm";
    private String yanjiangUrl = "http://c.open.163.com/search/search.htm?query=%E6%BC%94%E8%AE%B2&enc=%E2%84%A2#/search/all";
    public GridView gv_show_openclass;
    public int[] gridview_ivs = {R.drawable.guojimingxiao_bg, R.drawable.zhongguodaxue_bg,
            R.drawable.kehanxueyuan_bg, R.drawable.coursera_bg, R.drawable.yanjiang_bg,
            R.drawable.xiqu_bg, R.drawable.yishu_bg,
            R.drawable.wenyi_bg, R.drawable.itdalaoyanjiang_bg,
            R.drawable.huanbaoxiwang_bg};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_openclass, container, false);
        gv_show_openclass = (GridView) view.findViewById(R.id.gv_show_openclass);
        ArrayList<HashMap<String, Object>> al = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < gridview_ivs.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("icon", gridview_ivs[i]);//添加图像资源的ID
            al.add(map);
        }
        SimpleAdapter sa = new SimpleAdapter(getActivity(), al, R.layout.gv_openclass_item, new String[]{"icon"}, new int[]{R.id.iv_openclass_item});
        gv_show_openclass.setAdapter(sa);
        gv_show_openclass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent_player = new Intent(getActivity(), PlayerActivity.class);
                switch (position) {
                    case 0:
                        intent_player.putExtra("VideoUrl", guojimingxiaoUrl);
                        getActivity().startActivity(intent_player);
                        break;
                    case 1:
                        intent_player.putExtra("VideoUrl", zhongguodaxueUrl);
                        getActivity().startActivity(intent_player);
                        break;
                    case 2:
                        intent_player.putExtra("VideoUrl", kehanxueyuanUrl);
                        getActivity().startActivity(intent_player);
                        break;
                    case 3:
                        intent_player.putExtra("VideoUrl", courderaUrl);
                        getActivity().startActivity(intent_player);
                        break;
                    case 4:
                        intent_player.putExtra("VideoUrl", yanjiangUrl);
                        getActivity().startActivity(intent_player);
                        break;
                    case 5:
                        intent_player.putExtra("VideoUrl", xiquUrl);
                        getActivity().startActivity(intent_player);
                        break;
                    case 6:
                        intent_player.putExtra("VideoUrl", yishuUrl);
                        getActivity().startActivity(intent_player);
                        break;
                    case 7:
                        intent_player.putExtra("VideoUrl", wenyiUrl);
                        getActivity().startActivity(intent_player);
                        break;
                    case 8:
                        intent_player.putExtra("VideoUrl", itdalaoUrl);
                        getActivity().startActivity(intent_player);
                        break;
                    case 9:
                        intent_player.putExtra("VideoUrl", huanbaoUrl);
                        getActivity().startActivity(intent_player);
                        break;
                }
            }
        });
        return view;
    }

}
