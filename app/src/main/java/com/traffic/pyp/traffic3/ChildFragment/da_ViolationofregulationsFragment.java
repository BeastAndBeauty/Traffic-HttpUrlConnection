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

import java.util.ArrayList;
import java.util.List;
import com.github.mikephil.charting.data.Entry;
import com.traffic.pyp.traffic3.Chart.PieChartManager;
import com.traffic.pyp.traffic3.R;

public class da_ViolationofregulationsFragment extends Fragment {
    private PieChart fg_da_vf;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fg_da_violationofregulations,container,false);
        initview(view);
        showchart();
        return view;
    }

    public void initview(View view){
        fg_da_vf=view.findViewById(R.id.fg_da_vf);
    }

    public void showchart(){
        fg_da_vf.setDescription("平台上有违章和没违章车辆的占比统计");
        List<String> xVals = new ArrayList<>();
        xVals.add("有违章");
        xVals.add("无违章");
        // 设置每份所占数量
        List<Entry> yvals = new ArrayList<>();
        yvals.add(new Entry(28.6F, 2));
        yvals.add(new Entry(71.3F, 2));
        //设置每份的颜色
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FF4472A5"));
        colors.add(Color.parseColor("#FFA84543"));

        PieChartManager pieChartManagger = new PieChartManager(fg_da_vf);
        pieChartManagger.showSolidPieChart(xVals, yvals, colors);

    }


}
