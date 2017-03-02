package com.slpcode.pipi.news.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.slpcode.pipi.news.beans.NewsBean;
import com.slpcode.pipi.news.beans.NewsDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * PiPiNews [v 2.0.0]
 * classes : com.slpcode.pipi.news.utils.NewsJsonUtils
 * Mr.Smile create at 2016/7/25 17:19
 */
public class NewsJsonUtils {

    private final static String TAG = "NewsJsonUtils";

    /**
     * @author SiLiPing
     * @E-Mail 939753553@qq.com
     * @date 2017/2/28
     * @describe 获取图片轮播资源
     */
//    public static List<>

    /**
     * 将获取到的json转换为新闻列表对象
     * @param res
     * @param value
     * @return
     */
    public static List<NewsBean> readJsonNewsBeans(String res, String value) {
        List<NewsBean> beans = new ArrayList<NewsBean>();
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
            JsonElement jsonElement = jsonObj.get(value);
            if(jsonElement == null) {
                return null;
            }
            //获取整个JsonArray
            JsonArray jsonArray = jsonElement.getAsJsonArray();

            /******************-----bingo-----********************/
            System.out.println("获取value："+value);
            /**头条轮播图片获取**/
            if (("T1348647909107").equals(value)){
                //广告轮播的数据
                JsonObject jo000 = jsonArray.get(0).getAsJsonObject();
                System.out.println("获取JsonObject："+jo000.toString());
                JsonArray array=jo000.get("ads").getAsJsonArray();    //得到为jo000的数组
                System.out.println("获取ads的数组："+array.toString());
                if (jo000.has("ads")) {
                    for(int i=0;i<array.size();i++){
                        JsonObject subObject=array.get(i).getAsJsonObject();
                        System.out.println("title="+subObject.get("title").getAsString());
                        System.out.println("tag="+subObject.get("tag").getAsString());
                        System.out.println("url="+subObject.get("url").getAsString());
                        System.out.println("imgsrc="+subObject.get("imgsrc").getAsString());
                    }
                }
            }
            /******************-----end-----********************/

            for (int i = 1; i < jsonArray.size(); i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
//                if (jo.has("skipType") && "special".equals(jo.get("skipType").getAsString())) {
//                    continue;
//                }
                if (jo.has("TAGS") && !jo.has("TAG")) {
                    continue;
                }

                if (!jo.has("imgextra")) {
                    NewsBean news = JsonUtils.deserialize(jo, NewsBean.class);
                    beans.add(news);
                }
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "readJsonNewsBeans error" , e);
        }
        return beans;
    }

    public static NewsDetailBean readJsonNewsDetailBeans(String res, String docId) {
        NewsDetailBean newsDetailBean = null;
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
            JsonElement jsonElement = jsonObj.get(docId);
            if(jsonElement == null) {
                return null;
            }
            newsDetailBean = JsonUtils.deserialize(jsonElement.getAsJsonObject(), NewsDetailBean.class);
        } catch (Exception e) {
            LogUtils.e(TAG, "readJsonNewsBeans error" , e);
        }
        return newsDetailBean;
    }

}
