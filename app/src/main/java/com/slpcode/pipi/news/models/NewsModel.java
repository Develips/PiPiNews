package com.slpcode.pipi.news.models;

/**
 * PiPiNews [v 2.0.0]
 * classes : com.slpcode.pipi.news.model.NewsModel
 * Mr.Smile create at 2016/7/25 17:13
 */
public interface NewsModel {
    void loadNews(String url, int type, NewsModelImpl.OnLoadNewsListListener listener);
    void loadNewsDetail(String docid, NewsModelImpl.OnLoadNewsDetailListener listener);
}
