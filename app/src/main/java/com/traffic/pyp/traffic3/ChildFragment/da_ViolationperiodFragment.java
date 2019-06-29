package com.traffic.pyp.traffic3.ChildFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.traffic.pyp.traffic3.Chart.MBarChartManager;
import com.traffic.pyp.traffic3.R;

public class da_ViolationperiodFragment extends Fragment {
    private BarChart fg_da_vp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fg_da_violationperiod,container,false);
        initview(view);
        showChart();
        return view;
    }
    public void initview(View view){
        fg_da_vp=view.findViewById(R.id.fg_da_vp);
    }
    public void showChart(){
        fg_da_vp.setDescription("每日时段内车辆违章的占比统计");
        String[] strings=new String[]{"0:00:01-2:00:00","2:00:01-4:00:00","4:00:01-6:00:00","6:00:01-8:00:00","8:00:01-10:00:00","10:00:01-12:00:00","12:00:01-14:00:00","14:00:01-16:00:00","16:00:01-18:00:00","18:00:01-20:00:00","20:00:01-22:00:00","22:00:01-24:00:00"};
        float[] data=new float[]{(float) 0.012,(float) 0.003,(float) 0.013,(float) 0.052,(float) 0.1854,(float) 0.2187,(float) 0.135,(float) 0.1928,(float) 0.139,(float) 0.043,(float) 0.023,(float) 0.011};
        MBarChartManager.initDataStyle(fg_da_vp,MBarChartManager.initBarChartManager(getActivity(),fg_da_vp,strings,data),getActivity());
    }
}
