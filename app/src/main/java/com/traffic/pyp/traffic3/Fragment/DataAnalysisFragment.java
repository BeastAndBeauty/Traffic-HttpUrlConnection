package com.traffic.pyp.traffic3.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.traffic.pyp.traffic3.Adapter.DataAnalysisViewPagerAdapter;
import com.traffic.pyp.traffic3.ChildFragment.da_AgeofviolationFragmnet;
import com.traffic.pyp.traffic3.ChildFragment.da_RepeatedviolationFragment;
import com.traffic.pyp.traffic3.ChildFragment.da_ViolationbehaviorFragmnet;
import com.traffic.pyp.traffic3.ChildFragment.da_ViolationofregulationsFragment;
import com.traffic.pyp.traffic3.ChildFragment.da_ViolationperiodFragment;
import com.traffic.pyp.traffic3.ChildFragment.da_ViolationsexFragment;
import com.traffic.pyp.traffic3.ChildFragment.da_ViolationtimesFragment;
import com.traffic.pyp.traffic3.R;

import java.util.ArrayList;
import java.util.List;


public class DataAnalysisFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private Button fg_da_bt1, fg_da_bt2, fg_da_bt3, fg_da_bt4, fg_da_bt5, fg_da_bt6, fg_da_bt7;
    private ViewPager fg_da_viewpager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_dataanalysis, container, false);
        initview(view);
        SetViewPage();
        return view;
    }

    private void initview(View view) {
        fg_da_viewpager = view.findViewById(R.id.fg_da_viewpager);
        fg_da_bt1 = view.findViewById(R.id.fg_da_bt1);
        fg_da_bt2 = view.findViewById(R.id.fg_da_bt2);
        fg_da_bt3 = view.findViewById(R.id.fg_da_bt3);
        fg_da_bt4 = view.findViewById(R.id.fg_da_bt4);
        fg_da_bt5 = view.findViewById(R.id.fg_da_bt5);
        fg_da_bt6 = view.findViewById(R.id.fg_da_bt6);
        fg_da_bt7 = view.findViewById(R.id.fg_da_bt7);


        fg_da_bt1.setOnClickListener(this);
        fg_da_bt2.setOnClickListener(this);
        fg_da_bt3.setOnClickListener(this);
        fg_da_bt4.setOnClickListener(this);
        fg_da_bt5.setOnClickListener(this);
        fg_da_bt6.setOnClickListener(this);
        fg_da_bt7.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        makebgall();
        switch (v.getId()) {
            case R.id.fg_da_bt1:
                fg_da_bt1.setBackground(getContext().getResources().getDrawable(R.drawable.point2));
                fg_da_viewpager.setCurrentItem(0);
                break;
            case R.id.fg_da_bt2:
                fg_da_bt2.setBackground(getContext().getResources().getDrawable(R.drawable.point2));
                fg_da_viewpager.setCurrentItem(1);
                break;
            case R.id.fg_da_bt3:
                fg_da_bt3.setBackground(getContext().getResources().getDrawable(R.drawable.point2));
                fg_da_viewpager.setCurrentItem(2);
                break;
            case R.id.fg_da_bt4:
                fg_da_bt4.setBackground(getContext().getResources().getDrawable(R.drawable.point2));
                fg_da_viewpager.setCurrentItem(3);
                break;
            case R.id.fg_da_bt5:
                fg_da_bt5.setBackground(getContext().getResources().getDrawable(R.drawable.point2));
                fg_da_viewpager.setCurrentItem(4);
                break;
            case R.id.fg_da_bt6:
                fg_da_bt6.setBackground(getContext().getResources().getDrawable(R.drawable.point2));
                fg_da_viewpager.setCurrentItem(5);
                break;
            case R.id.fg_da_bt7:
                fg_da_bt7.setBackground(getContext().getResources().getDrawable(R.drawable.point2));
                fg_da_viewpager.setCurrentItem(6);
                break;

        }
    }

    public void makebgall() {
        Drawable drawable = getContext().getResources().getDrawable(R.drawable.point);
        fg_da_bt1.setBackground(drawable);
        fg_da_bt2.setBackground(drawable);
        fg_da_bt3.setBackground(drawable);
        fg_da_bt4.setBackground(drawable);
        fg_da_bt5.setBackground(drawable);
        fg_da_bt6.setBackground(drawable);
        fg_da_bt7.setBackground(drawable);

    }

    private List<Fragment> fragments;

    public void SetViewPage() {
        fragments = new ArrayList<>();
        fragments.add(new da_ViolationofregulationsFragment());
        fragments.add(new da_RepeatedviolationFragment());
        fragments.add(new da_ViolationtimesFragment());
        fragments.add(new da_AgeofviolationFragmnet());
        fragments.add(new da_ViolationsexFragment());
        fragments.add(new da_ViolationperiodFragment());
        fragments.add(new da_ViolationbehaviorFragmnet());
        fg_da_viewpager.setAdapter(new DataAnalysisViewPagerAdapter(getChildFragmentManager(), fragments));
        fg_da_viewpager.setCurrentItem(0);
        fg_da_viewpager.addOnPageChangeListener(this);
        fg_da_viewpager.setOffscreenPageLimit(0);//禁止预加载
        Log.i("hhh", "SetViewPage,Over");
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (i == 2) {
            switch (fg_da_viewpager.getCurrentItem()) {
                case 0:
                    fg_da_bt1.performClick();
                    break;
                case 1:
                    fg_da_bt2.performClick();
                    break;
                case 2:
                    fg_da_bt3.performClick();
                    break;
                case 3:
                    fg_da_bt4.performClick();
                    break;
                case 4:
                    fg_da_bt5.performClick();
                    break;
                case 5:
                    fg_da_bt6.performClick();
                    break;
                case 6:
                    fg_da_bt7.performClick();
                    break;
            }
        }
    }
}
