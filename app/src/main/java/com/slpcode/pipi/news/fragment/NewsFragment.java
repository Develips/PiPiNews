package com.slpcode.pipi.news.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.slpcode.baselib.tablayout.SlidingTabLayout;
import com.slpcode.baselib.tablayout.listener.OnTabSelectListener;
import com.slpcode.pipi.news.R;
import com.slpcode.pipi.news.utils.NetworkImageHolderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * material-News [v 2.0.0]
 * classes : com.dspy.material_news.NewsFragment
 * Mr.Smile create at 2016/7/22 15:53
 */
public class NewsFragment extends Fragment implements OnTabSelectListener {

    public static final int NEWS_TYPE_TOP = 0;
    public static final int NEWS_TYPE_NBA = 1;
    public static final int NEWS_TYPE_CARS = 2;
    public static final int NEWS_TYPE_JOKES = 3;

    private Context mContext;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"头条", "NBA", "汽车", "笑话",};
    private MyPagerAdapter mAdapter;

    /**顶部广告栏控件*/
    private ConvenientBanner convenientBanner;
    private List<String> networkImages;
    private String[] images = {
            "http://cms-bucket.nosdn.127.net/4a65fb9f6ddd48529737e9d4fc0d1f5c20170302112359.jpeg",
            "http://cms-bucket.nosdn.127.net/57f2b3bde5514d16b131a71ea4d49ed120170302110934.jpeg",
            "http://cms-bucket.nosdn.127.net/3f9fe8cfdd314d23a269c9c247928a8120170302105215.jpeg",
            "http://cms-bucket.nosdn.127.net/21d07c23b0314cc2882610235741c2ac20170302094807.jpeg",
            "http://cms-bucket.nosdn.127.net/3a3f04499e584492bbafd2225842509f20170302114952.jpeg"
    };
    private String[] imageTitles = {
            "看客：百日誓师 \"命运之战\"的最后冲刺",
            "吉林大妈拉横幅围堵乐天超市",
            "台禽流感蔓延 大陆游客不听劝阻喂鸽子",
            "金正男案两名嫌犯受审 记者蹲守大使馆",
            "韩国民众开铲车冲向萨德部署地"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        mContext = this.getContext();
        initBanner(view);/**顶部广告栏控件初始化*/
        for (String title : mTitles) {
            mFragments.add(SimpleCardFragment.getInstance(title));
        }
        ViewPager vp = (ViewPager) view.findViewById(R.id.vp);
        vp.setOffscreenPageLimit(3);
        setupViewPager(vp);
//        mAdapter = new MyPagerAdapter(getChildFragmentManager());
//        vp.setAdapter(mAdapter);
        /** tab固定宽度 */
        SlidingTabLayout tabLayout_4 = (SlidingTabLayout) view.findViewById(R.id.sliding_tab);

        tabLayout_4.setOnTabSelectListener(this);
        tabLayout_4.setViewPager(vp);
        vp.setCurrentItem(0);
//小红点
//        tabLayout_4.showDot(4);
//        tabLayout_4.showMsg(3, 5);
//        tabLayout_4.setMsgMargin(3, 0, 10);
//        MsgView rtv_2_3 = tabLayout_4.getMsgView(3);
//        if (rtv_2_3 != null) {
//            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
//        }
//        tabLayout_4.showMsg(5, 5);
//        tabLayout_4.setMsgMargin(5, 0, 10);
        return view;
    }

    private void setupViewPager(ViewPager mViewPager) {
        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManager(),否则会有问题
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_TOP), getString(R.string.top));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_NBA), getString(R.string.nba));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_CARS), getString(R.string.cars));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_JOKES), getString(R.string.jokes));
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onTabSelect(int position) {
        Toast.makeText(mContext, "onTabSelect&position--->" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabReselect(int position) {
        Toast.makeText(mContext, "onTabReselect&position--->" + position, Toast.LENGTH_SHORT).show();
    }

//    private class MyPagerAdapter extends FragmentPagerAdapter {
//        public MyPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragments.size();
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mTitles[position];
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragments.get(position);
//        }
//    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    /**
     * @author SiLiPing
     * @E-Mail 939753553@qq.com
     * @date 2017/3/2
     * @describe 广告轮播
     */
    private void initBanner(View view){
        convenientBanner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        initImageLoader();

        //网络加载例子
        networkImages= Arrays.asList(images);
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },networkImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                /**@describe 广告轮播点击事件*/
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(mContext, ""+imageTitles[position], Toast.LENGTH_SHORT).show();
                    }
                });
        //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件

//        convenientBanner.setManualPageable(false);//设置不能手动影响;

    }
    //初始化网络图片缓存库
    private void initImageLoader(){
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.ic_image_loadfail)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(5000);
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }
}
