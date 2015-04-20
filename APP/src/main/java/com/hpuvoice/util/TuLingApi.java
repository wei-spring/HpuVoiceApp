package com.hpuvoice.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by wei-spring on 2015/4/5.
 * <p/>
 * 获取图灵文本回复
 */
public class TuLingApi {

    /**
     * 解析新闻类的回复结果
     *
     * @param resString
     * @return
     */
    public static String getResNews(String resString) {
        try {
            StringBuffer sb = new StringBuffer();
            String dataText = new JSONObject(resString).getString("text");
            sb.append(dataText);
            String dataList = new JSONObject(resString).getString("list");
            JSONArray jsonArray = new JSONArray(dataList);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = (JSONObject) jsonArray.opt(i);
                String article = jo.getString("article");
                sb.append("\n" + "标题:" + article);
                String source = jo.getString("source");
                sb.append("\n" + "来源:" + source);
                String detailurl = jo.getString("detailurl");
                sb.append("\n" + "详情地址:" + detailurl);
                String icon = jo.getString("icon");
                sb.append("\n" + icon);
            }
            return sb.toString();
        } catch (Exception e) {
            return "哇哦，没有你的回复，请积攒人品...";
        }
    }

    /**
     * 解析列车类的回复结果
     *
     * @param resString
     * @return
     */
    public static String getResTrains(String resString) {
        try {
            StringBuffer sb = new StringBuffer();
            String dataText = new JSONObject(resString).getString("text");
            sb.append(dataText);
            String dataList = new JSONObject(resString).getString("list");
            JSONArray jsonArray = new JSONArray(dataList);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = (JSONObject) jsonArray.opt(i);
                String trainnum = jo.getString("trainnum");
                sb.append("\n" + "车次:" + trainnum);
                String start = jo.getString("start");
                sb.append("\n" + "起始站:" + start);
                String terminal = jo.getString("terminal");
                sb.append("\n" + "到达站:" + terminal);
                String starttime = jo.getString("starttime");
                sb.append("\n" + "开车时间:" + starttime);
                String endtime = jo.getString("endtime");
                sb.append("\n" + "到达时间:" + endtime);
                String detailurl = jo.getString("detailurl");
                sb.append("\n" + "详情地址:" + detailurl);
                String icon = jo.getString("icon");
                sb.append("\n" + icon);
            }
            return sb.toString();
        } catch (Exception e) {
            return "哇哦，没有你的回复，请积攒人品...";
        }
    }

    /**
     * 解析航班类的回复结果
     *
     * @param resString
     * @return
     */
    public static String getResFlights(String resString) {
        try {
            StringBuffer sb = new StringBuffer();
            String dataText = new JSONObject(resString).getString("text");
            sb.append(dataText);
            String dataList = new JSONObject(resString).getString("list");
            JSONArray jsonArray = new JSONArray(dataList);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = (JSONObject) jsonArray.opt(i);
                String flight = jo.getString("flight");
                sb.append("\n" + "航班:" + flight);
                String route = jo.getString("route");
                sb.append("\n" + "航班路线:" + route);
                String starttime = jo.getString("starttime");
                sb.append("\n" + "起飞时间:" + starttime);
                String endtime = jo.getString("endtime");
                sb.append("\n" + "到达时间:" + endtime);
                String state = jo.getString("state");
                sb.append("\n" + "航班状态:" + state);
                String detailurl = jo.getString("detailurl");
                sb.append("\n" + "详情址:" + detailurl);
                String icon = jo.getString("icon");
                sb.append("\n" + icon);
            }
            return sb.toString();
        } catch (Exception e) {
            return "哇哦，没有你的回复，请积攒人品...";
        }
    }

    /**
     * 解析菜谱类的回复结果
     *
     * @param resString
     * @return
     */
    public static String getResFoods(String resString) {
        try {
            StringBuffer sb = new StringBuffer();
            String dataText = new JSONObject(resString).getString("text");
            sb.append(dataText);
            String dataList = new JSONObject(resString).getString("list");
            JSONArray jsonArray = new JSONArray(dataList);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = (JSONObject) jsonArray.opt(i);
                String name = jo.getString("name");
                sb.append("\n" + "名称:" + name);
                String info = jo.getString("info");
                sb.append("\n" + "详情:" + info);
                String detailurl = jo.getString("detailurl");
                sb.append("\n" + "详情链接:" + detailurl);
                String icon = jo.getString("icon");
                sb.append("\n" + icon);
            }
            return sb.toString();
        } catch (Exception e) {
            return "哇哦，没有你的回复，请积攒人品...";
        }
    }


    /**
     * 根据不同返回码解析不同的回复结果
     *
     * @param queryString
     * @return
     */
    public static String parseRes(String queryString) {
        try {
            int codeText = new JSONObject(queryString).getInt("code");
            switch (codeText) {
                case 100000:
                    String dataText = new JSONObject(queryString).getString("text");
                    return dataText;
                case 200000:
                    String dataUrlText = new JSONObject(queryString).getString("text");
                    String dataUrl = new JSONObject(queryString).getString("url");
                    return dataUrlText + " \n " + dataUrl;
                case 302000:
                    String dataNewsText = getResNews(queryString);
                    return dataNewsText;
                case 305000:
                    String dataTrainText = getResTrains(queryString);
                    return dataTrainText;
                case 306000:
                    String dataFlightText = getResFlights(queryString);
                    return dataFlightText;
                case 308000:
                    String dataFoodText = getResFoods(queryString);
                    return dataFoodText;

            }
            return "";
        } catch (Exception e) {
            return "哇哦，没有你的回复，请积攒人品...";
        }
    }

    /**
     * 获取回复的元数据，进一步解析提取不同类别回复
     *
     * @param queryString
     * @return
     */
    public static String getData(String queryString) {
        try {
            String APIKEY = "7e75b29ab972483d7d63418ddced2bf8";
            String INFO = URLEncoder.encode(queryString, "utf-8");
            String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;
            URL getUrl = new URL(getURL);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();

            // 取得输入流，并使用Reader读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
