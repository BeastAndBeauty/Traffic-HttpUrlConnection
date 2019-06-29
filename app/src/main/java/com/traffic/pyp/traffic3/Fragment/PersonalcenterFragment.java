package com.traffic.pyp.traffic3.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.traffic.pyp.traffic3.Adapter.PersonalcenterAdapter;
import com.traffic.pyp.traffic3.ChildFragment.PersonalDataFragment;
import com.traffic.pyp.traffic3.ChildFragment.RechargeRecordFragment;
import com.traffic.pyp.traffic3.ChildFragment.ThresholdSettingFragment;
import com.traffic.pyp.traffic3.R;

import java.util.ArrayList;
import java.util.List;

public class PersonalcenterFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PersonalcenterAdapter personalcenterAdapter;
    private List<Fragment> fragment;
    private List<String> title;
    private PersonalDataFragment personalDataFragment;
    private RechargeRecordFragment rechargeRecordFragment;
    private ThresholdSettingFragment thresholdSettingFragment;

    private int page;//进入个人中心默认显示的页数

    @SuppressLint("ValidFragment")
    public PersonalcenterFragment(int page) {
        this.page = page;
    }

    public PersonalcenterFragment() {
        this.page = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personalcenter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        viewPager.setCurrentItem(page);
    }

    private void initView(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        fragment = new ArrayList<>();
        title = new ArrayList<>();
        personalDataFragment = new PersonalDataFragment();
        rechargeRecordFragment = new RechargeRecordFragment();
        thresholdSettingFragment = new ThresholdSettingFragment();
        fragment.add(personalDataFragment);
        fragment.add(rechargeRecordFragment);
        fragment.add(thresholdSettingFragment);
        title.add("个人信息");
        title.add("充值记录");
        title.add("阈值设置");
        for (int i = 0; i < title.size(); i++)
            tabLayout.addTab(tabLayout.newTab().setText(title.get(i)));
        personalcenterAdapter = new PersonalcenterAdapter(getChildFragmentManager(), fragment, title);
        viewPager.setAdapter(personalcenterAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
