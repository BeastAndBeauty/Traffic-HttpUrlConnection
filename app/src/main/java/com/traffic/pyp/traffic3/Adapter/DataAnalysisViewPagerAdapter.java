package com.traffic.pyp.traffic3.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class DataAnalysisViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> Fragments;

    public DataAnalysisViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        Fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return Fragments.get(i);
    }

    @Override
    public int getCount() {
        return Fragments.size();
    }
}
