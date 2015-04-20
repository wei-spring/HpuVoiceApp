package com.hpuvoice.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hpuvoice.XlistView.IXListViewLoadMore;
import com.hpuvoice.XlistView.IXListViewRefreshListener;
import com.hpuvoice.adapter.NewsItemAdapter;
import com.hpuvoice.app.NewsContentActivity;
import com.hpuvoice.app.R;
import com.hpuvoice.dao.NewsItemDao;
import com.hpuvoice.util.Logger;
import com.hpuvoice.util.NetUtil;
import com.hpuvoice.util.ToastUtil;
import com.zhy.bean.CommonException;
import com.zhy.bean.NewsItem;
import com.zhy.biz.NewsItemBiz;

import java.util.ArrayList;
import java.util.List;

public class CsdnFragment extends Fragment implements IXListViewRefreshListener, IXListViewLoadMore{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    /**
     * 处理新闻的业务类
     */
    private static NewsItemBiz mNewsItemBiz;

    public static CsdnFragment newInstance(String param1, String param2) {
        CsdnFragment fragment = new CsdnFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        mNewsItemBiz = new NewsItemBiz();
        return fragment;
    }

    public CsdnFragment() {
    }


    private static final int LOAD_MORE = 0x110;
    private static final int LOAD_REFREASH = 0x111;

    private static final int TIP_ERROR_NO_NETWORK = 0X112;
    private static final int TIP_ERROR_SERVER = 0X113;

    /**
     * 是否是第一次进入
     */
    private boolean isFirstIn = true;

    /**
     * 是否连接网络
     */
    private boolean isConnNet = false;

    /**
     * 当前数据是否是从网络中获取的
     */
    private boolean isLoadingDataFromNetWork;

    /**
     * 默认的newType
     *  NEWS_TYPE_YEJIE = 1;
        NEWS_TYPE_YIDONG = 2;
        NEWS_TYPE_YANFA = 3;
        NEWS_TYPE_CHENGXUYUAN = 4;
        NEWS_TYPE_YUNJISUAN = 5;
     */
    private int newsType = 1;
    /**
     * 当前页面
     */
    private int currentPage = 1;

    /**
     * 与数据库交互
     */
    private NewsItemDao mNewsItemDao;

    /**
     * 扩展的ListView
     */
    private ListView mXListView;
    /**
     * 数据适配器
     */
    private NewsItemAdapter mAdapter;

    /**
     * 数据
     */
    private List<NewsItem> mDatas = new ArrayList<NewsItem>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_csdn, container, false);
        new LoadDatasTask().execute(LOAD_REFREASH);
        mNewsItemDao = new NewsItemDao(getActivity());

        /**
         * 初始化
         */
        mXListView = (ListView) view.findViewById(R.id.csdn_xlistView);
       // mXListView.setPullRefreshEnable(CsdnFragment.this);
        //mXListView.setPullLoadEnable(CsdnFragment.this);
       // mXListView.setRefreshTime(AppUtil.getRefreashTime(getActivity(), newsType));
        // mXListView.NotRefreshAtBegin();

        mXListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsItem newsItem = mDatas.get(position);
                Intent intent = new Intent(getActivity(), NewsContentActivity.class);
                intent.putExtra("url", newsItem.getLink());
                startActivity(intent);
            }
        });
//        if (isFirstIn) {
//            /**
//             * 进来时直接刷新
//             */
//            mXListView.startRefresh();
//            isFirstIn = false;
//        } else {
//            mXListView.NotRefreshAtBegin();
//        }
        return view;
    }

    @Override
    public void onRefresh() {
        new LoadDatasTask().execute(LOAD_REFREASH);
    }

    @Override
    public void onLoadMore() {
        new LoadDatasTask().execute(LOAD_MORE);
    }

    /**
     * 记载数据的异步任务
     *
     * @author zhy
     */
    class LoadDatasTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
//            switch (params[0]) {
//                case LOAD_MORE:
//                    loadMoreData();
//                    break;
//                case LOAD_REFREASH:
//                    return refreashData();
//            }
            List<NewsItem> newsItems = null;
            try {
                newsItems = mNewsItemBiz.getNewsItems(newsType, currentPage);
                Logger.e("  "+newsItems.size());
            } catch (CommonException e) {
                e.printStackTrace();
            }
            mDatas = newsItems;
            mAdapter = new NewsItemAdapter(getActivity(), newsItems);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mXListView.setAdapter(mAdapter);
                }
            });

            return -1;
        }

        @Override
        protected void onPostExecute(Integer result) {
            switch (result) {
                case TIP_ERROR_NO_NETWORK:
                    ToastUtil.toast(getActivity(), "没有网络连接！");
                    mAdapter.setDatas(mDatas);
                    mAdapter.notifyDataSetChanged();
                    break;
                case TIP_ERROR_SERVER:
                    ToastUtil.toast(getActivity(), "服务器错误！");
                    break;

                default:
                    break;

            }

//            mXListView.setRefreshTime(AppUtil.getRefreashTime(getActivity(), newsType));
//            mXListView.stopRefresh();
//            mXListView.stopLoadMore();
        }

    }

    /**
     * 下拉刷新数据
     */
    public Integer refreashData() {

        if (NetUtil.checkNet(getActivity())) {

            isConnNet = true;
            // 获取最新数据
            try {
                List<NewsItem> newsItems = mNewsItemBiz.getNewsItems(newsType, currentPage);
                mAdapter.setDatas(newsItems);

                isLoadingDataFromNetWork = true;
                // 设置刷新时间
                //AppUtil.setRefreashTime(getActivity(), newsType);
                // 清除数据库数据
                mNewsItemDao.deleteAll(newsType);
                // 存入数据库
                mNewsItemDao.add(newsItems);

            } catch (CommonException e) {
                e.printStackTrace();
                isLoadingDataFromNetWork = false;
                return TIP_ERROR_SERVER;
            }
        } else {
            Log.e("xxx", "no network");
            isConnNet = false;
            isLoadingDataFromNetWork = false;
            // TODO从数据库中加载
            List<NewsItem> newsItems = mNewsItemDao.list(newsType, currentPage);
            mDatas = newsItems;
            // mAdapter.setDatas(newsItems);
            return TIP_ERROR_NO_NETWORK;
        }

        return -1;

    }

    /**
     * 会根据当前网络情况，判断是从数据库加载还是从网络继续获取
     */
    public void loadMoreData() {
        // 当前数据是从网络获取的
        if (isLoadingDataFromNetWork) {
            currentPage += 1;
            try {
                List<NewsItem> newsItems = mNewsItemBiz.getNewsItems(newsType, currentPage);
                mNewsItemDao.add(newsItems);
                mAdapter.addAll(newsItems);
            } catch (CommonException e) {
                e.printStackTrace();
            }
        } else
        // 从数据库加载的
        {
            currentPage += 1;
            List<NewsItem> newsItems = mNewsItemDao.list(newsType, currentPage);
            mAdapter.addAll(newsItems);
        }

    }


}
