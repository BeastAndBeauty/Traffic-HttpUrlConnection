package com.traffic.pyp.traffic3.ChildFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.traffic.pyp.traffic3.Chart.PieChartManager;
import com.traffic.pyp.traffic3.R;

import java.util.ArrayList;
import java.util.List;

public class da_RepeatedviolationFragment extends Fragment {
    private PieChart fg_da_rv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fg_da_repeatedviolation,container,false);
        initview(view);
        showChart();
        return view;
    }

    public void initview(View view){
        fg_da_rv=view.findViewById(R.id.fg_da_rv);
    }
    public void showChart() {
        fg_da_rv.setDescription("有无“重复违章记录的车辆”的占比统计");
        List<String> xVals = new ArrayList<>();
        xVals.add("有重复违章");
        xVals.add("无重复违章");
        // 设置每份所占数量
        List<Entry> yvals = new ArrayList<>();
        yvals.add(new Entry(13.8F, 2));
        yvals.add(new Entry(86.1F, 2));
        //设置每份的颜色
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FFA84543"));
        colors.add(Color.parseColor("#FF4472A5"));

        PieChartManager pieChartManagger = new PieChartManager(fg_da_rv);
        pieChartManagger.showSolidPieChart(xVals, yvals, colors);
    }

}

