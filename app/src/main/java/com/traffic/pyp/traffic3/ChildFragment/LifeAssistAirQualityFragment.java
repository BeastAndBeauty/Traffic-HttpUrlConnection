package com.traffic.pyp.traffic3.ChildFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.traffic.pyp.traffic3.Chart.BarChartUtil;
import com.traffic.pyp.traffic3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LifeAssistAirQualityFragment extends Fragment {


    private View view;
    private BarChart air_quality_barChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_life_assist_air_quality, container, false);
        initView();
        initData();
        return view;
    }

    private void initData() {
        String[] x={"03","06","09","12","15","18","21","24","27","30","33","36","39","42","45","48","51","54","57","60"};
        float[] y={82,92,94,103,105,94,95,91,95,94,85,87,85,90,95,98,94,80,81,87};
        BarChartUtil.setBarChart(air_quality_barChart,x,y,"");
    }

    private void initView(){
        air_quality_barChart=view.findViewById(R.id.air_quality_barChart);
    }

}
