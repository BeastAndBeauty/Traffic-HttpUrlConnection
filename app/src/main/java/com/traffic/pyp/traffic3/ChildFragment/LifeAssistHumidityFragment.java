package com.traffic.pyp.traffic3.ChildFragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.traffic.pyp.traffic3.Chart.LineChartUtil;
import com.traffic.pyp.traffic3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LifeAssistHumidityFragment extends Fragment {


    private View view;
    private LineChart lineChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_life_assist_humidity, container, false);

        initView();
        initData();


        return view;
    }

    private void initView(){
        lineChart=view.findViewById(R.id.humidity_lineChart);
    }

    private void initData(){
        lineChart.setDescription("过去1分钟最大相对湿度：60%");
        //设置x轴的数据
        float[] temperatureArr={58,57,58,60,60,65,60,59,57,55,50,40,60,67,55,42,34,37,32,35};

        String[] numX = {"03","06","09","12","15","18","21","24","27","30","33","36","39","42","45","48","51","54","57","60",};
        LineData lineData;
        //设置名称
        lineData = LineChartUtil.initSingleLineChart(getContext(), lineChart, numX, temperatureArr);
        LineChartUtil.initDataStyle(lineChart, lineData, getContext());
    }

}
