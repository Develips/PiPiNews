package com.slpcode.pipi.news.presenter;

import android.content.Context;

import com.slpcode.pipi.news.beans.NewsDetailBean;
import com.slpcode.pipi.news.models.NewsModel;
import com.slpcode.pipi.news.models.NewsModelImpl;
import com.slpcode.pipi.news.view.NewsDetailView;

/**
 * PiPiNews [v 2.0.0]
 * classes : com.slpcode.pipi.news.presenter.NewsDetailPresenterImpl
 * Mr.Smile create at 2016/7/25 17:48
 */
public class NewsDetailPresenterImpl implements NewsDetailPresenter, NewsModelImpl.OnLoadNewsDetailListener {

    private Context mContent;
    private NewsDetailView mNewsDetailView;
    private NewsModel mNewsModel;

    public NewsDetailPresenterImpl(Context mContent, NewsDetailView mNewsDetailView) {
        this.mContent = mContent;
        this.mNewsDetailView = mNewsDetailView;
        mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNewsDetail(final String docId) {
        mNewsDetailView.showProgress();
        mNewsModel.loadNewsDetail(docId, this);
    }


    @Override
    public void onSuccess(NewsDetailBean newsDetailBean) {
        if(newsDetailBean != null) {
            mNewsDetailView.showNewsDetialContent(newsDetailBean.getBody());
        }
        mNewsDetailView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsDetailView.hideProgress();
    }
}

