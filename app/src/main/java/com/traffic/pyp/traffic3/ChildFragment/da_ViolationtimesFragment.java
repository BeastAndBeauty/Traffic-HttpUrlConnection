package com.traffic.pyp.traffic3.ChildFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.traffic.pyp.traffic3.Chart.HorizontalBarChartManager;
import com.traffic.pyp.traffic3.R;


public class da_ViolationtimesFragment extends Fragment {
    private HorizontalBarChart fg_da_vt;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fg_da_violationtimes,container,false);
        initview(view);
        showChart();
        return view;
    }

    public void initview(View view){
        fg_da_vt=view.findViewById(R.id.fg_da_vt);
    }

    public void showChart(){
        int[] colors={Color.parseColor("#92D24B"),Color.parseColor("#5081BE"),Color.parseColor("#C00101")};
        String[] xAxisValue = {"1-2条违章", "3-5条违章", "5条以上违章"};
        float[] yAxisValue = {new Float(0.6051),
                new Float(0.2628),
                new Float(0.1320)};
        new HorizontalBarChartManager().setBaseAttributes(fg_da_vt,"违章车辆的违章次数占比分布图统计",colors,xAxisValue,yAxisValue);
    }
}
