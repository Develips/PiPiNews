package com.slpcode.pipi.news.view;

/**
 * 新闻详情
 * PiPiNews [v 2.0.0]
 * classes : com.slpcode.pipi.news.view.NewsDetailView
 * Mr.Smile create at 2016/7/25 17:02
 */
public interface NewsDetailView {

    void showNewsDetialContent(String newsDetailContent);

    void showProgress();

    void hideProgress();
}
