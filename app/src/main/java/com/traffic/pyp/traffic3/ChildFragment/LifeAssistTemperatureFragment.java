package com.traffic.pyp.traffic3.ChildFragment;


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
public class LifeAssistTemperatureFragment extends Fragment {


    private View view;
    private LineChart lineChart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_life_assist_temperature, container, false);

        initView();
        initData();

        return view;
    }

    private void initData() {
        lineChart.setDescription("过去1分钟最高气温：90°C最低气温：15°C");
        //设置x轴的数据
        float[] temperatureArr={19,19,18,18,18,18,17,16,17,19,20,21,21,23,23,24,24,25,25,25};
        String[] numX = {"03","06","09","12","15","18","21","24","27","30","33","36","39","42","45","48","51","54","57","60",};
        LineData lineData;
        //设置名称
        lineData = LineChartUtil.initSingleLineChart(getContext(), lineChart, numX,temperatureArr);
        LineChartUtil.initDataStyle(lineChart, lineData, getContext());
    }

    private void initView(){
        lineChart=view.findViewById(R.id.temperature_lineChart);
    }


}
