package com.slpcode.pipi.news.presenter;

import com.slpcode.pipi.news.R;
import com.slpcode.pipi.news.view.MainView;

/**
 * PiPiNews [v 2.0.0]
 * classes : com.slpcode.pipi.news.presenter.MainPresenterImpl
 * Mr.Smile create at 2016/7/25 15:24
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;

    public MainPresenterImpl(MainView mMainView) {
        this.mMainView = mMainView;
    }

    @Override
    public void switchNavigation(int id) {
        switch (id){
            case R.id.navigation_item_news:
                mMainView.switch2News();
                break;
            case R.id.navigation_item_images:
                mMainView.switch2Images();
                break;
            case R.id.navigation_item_weather:
                mMainView.switch2Weather();
                break;
            case R.id.navigation_item_about:
                mMainView.switch2About();
                break;
            default:
                mMainView.switch2News();
                break;
        }
    }
}
