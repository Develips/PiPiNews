package com.slpcode.pipi.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slpcode.pipi.news.R;

/**
 * PiPiNews [v 2.0.0]
 * classes : com.slpcode.pipi.news.fragment.AboutFragment
 * Mr.Smile create at 2016/7/25 15:43
 */
public class AboutFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, null);
        return view;
    }
}
