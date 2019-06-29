package com.traffic.pyp.traffic3.ChildFragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.traffic.pyp.traffic3.Chart.HorizontalBarChartManager;
import com.traffic.pyp.traffic3.R;

public class da_ViolationbehaviorFragmnet extends Fragment {
    private HorizontalBarChart fg_da_vb;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fg_da_violationbehavior,container,false);
        initvoiew(view);
        showChart();
        return view;
    }
    public void initvoiew(View view){
        fg_da_vb=view.findViewById(R.id.fg_da_vb);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void showChart(){
        int[] colors={Color.parseColor("#92D24B"),Color.parseColor("#5081BE"),Color.parseColor("#C00101"),
                Color.parseColor("#92D28B"),Color.parseColor("#5001BE"),Color.parseColor("#C80101"),
                Color.parseColor("#62D24B"),Color.parseColor("#5381BE"),Color.parseColor("#C06101"),
                Color.parseColor("#98D24B")};
        String[] xAxisValue = {"机动车逆向行驶", "违规使用专用车道", "违反禁令标志指示","不按规定系安全带",
                "机动车不走机动车道","违反信号灯规定","违反禁止标线指示","违规停车后拒绝驶离","违规入导向车道","超速行驶"};
        float[] yAxisValue = {new Float(0.0337), new Float(0.0387), new Float(0.0431),
                new Float(0.0480), new Float(0.0482), new Float(0.0743), new Float(0.0997),
                new Float(0.1287), new Float(0.2210), new Float(0.2646)};
        new HorizontalBarChartManager().setBaseAttributes(fg_da_vb,"排名前十位的交通违法行为的占比统计",colors,xAxisValue,yAxisValue);
    }
}
