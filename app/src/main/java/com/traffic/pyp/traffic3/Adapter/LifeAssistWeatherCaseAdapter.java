package com.traffic.pyp.traffic3.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者：paopao on 2019/1/16 19:18
 * <p>
 * 作用:
 */
public class LifeAssistWeatherCaseAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;
    public LifeAssistWeatherCaseAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
