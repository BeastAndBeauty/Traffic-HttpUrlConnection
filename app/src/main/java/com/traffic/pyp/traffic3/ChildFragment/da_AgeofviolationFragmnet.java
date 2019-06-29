package com.traffic.pyp.traffic3.ChildFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.traffic.pyp.traffic3.Chart.MultilevelBarChartManager;
import com.traffic.pyp.traffic3.R;

import java.util.ArrayList;
import java.util.List;


public class da_AgeofviolationFragmnet extends Fragment {
    private BarChart fg_da_av;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fg_da_ageofviolation,container,false);
        initview(view);
        showChart();
        return view;
    }

    public void initview(View view){
        fg_da_av=view.findViewById(R.id.fg_da_av);
    }

    public void showChart(){
        String[] xAxisValue = {"90后", "80后", "70后", "60后", "50后"};
        List<float[]> yAxisValue = new ArrayList<>();
        yAxisValue.add(new float[]{ (float) 0.220,(float) 0.388});
        yAxisValue.add(new float[]{ (float) 0.520,(float) 0.562});
        yAxisValue.add(new float[]{ (float) 0.480,(float) 0.407});
        yAxisValue.add(new float[]{ (float) 0.480,(float) 0.308});
        yAxisValue.add(new float[]{ (float) 0.180,(float) 0.213});
        int[] PIE_COLORS = {
                Color.parseColor("#EA6D08"),
                Color.parseColor("#6A9800")};
        String title = "年龄群体车辆违章的占比统计";
        String[] lables=new String[]{"有违章", "无违章"};
        new MultilevelBarChartManager().setBaseAttribute(fg_da_av,xAxisValue,yAxisValue,title,PIE_COLORS,lables);
    }
}
