package com.slpcode.pipi.news.view;

import com.slpcode.pipi.news.beans.NewsBean;

import java.util.List;

/**
 * 新闻列表
 * PiPiNews [v 2.0.0]
 * classes : com.slpcode.pipi.news.view.NewsView
 * Mr.Smile create at 2016/7/25 17:01
 */
public interface NewsView {

    void showProgress();

    void addNews(List<NewsBean> newsList);

    void hideProgress();

    void showLoadFailMsg();
}
