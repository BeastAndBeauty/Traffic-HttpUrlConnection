package com.traffic.pyp.traffic3.Chart;

import android.graphics.Color;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class HorizontalBarChartManager {
    public void setBaseAttributes(HorizontalBarChart barChart,
                                  String des, int[] colors,
                                  final String[] xAxisValue, float[] yAxisValue){
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setDrawGridBackground(false);
        barChart.getLegend().setEnabled(false);
        barChart.setExtraLeftOffset(50f);
        //设置坐标轴
        XAxis xAxis=barChart.getXAxis();
        YAxis leftYAxis=barChart.getAxisLeft();
        YAxis rightYAxis=barChart.getAxisRight();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelsToSkip(xAxisValue.length);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(50f);
        xAxis.setValueFormatter(new XAxisValueFormatter() {
            @Override
            public String getXValue(String s, int i, ViewPortHandler viewPortHandler) {
                return xAxisValue[i];
            }
        });

        leftYAxis.setEnabled(false);
        //leftYAxis.setAxisMinValue(0f);

        rightYAxis.setEnabled(true);
        rightYAxis.setDrawAxisLine(true);
        rightYAxis.setAxisMinValue(0f);
        rightYAxis.setLabelCount(10,true);
        rightYAxis.setStartAtZero(true);
        rightYAxis.setTextSize(50f);
        rightYAxis.setValueFormatter(new YAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, YAxis yAxis) {
                NumberFormat numberFormat=NumberFormat.getPercentInstance();
                numberFormat.setMinimumFractionDigits(2);
                numberFormat.setMaximumFractionDigits(2);
                return numberFormat.format(v);
            }
        });

        //设置描述标签
        barChart.setDescription(des);
        barChart.setDescriptionTextSize(40f);
        barChart.setDescriptionPosition(1540,60);

        //设置柱状图数据
        List<BarEntry> entries=new ArrayList<>();
        for(int i=0;i<yAxisValue.length;i++){
            entries.add(new BarEntry(yAxisValue[i],i));
        }
        BarDataSet set1;
        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(entries, "");
            set1.setColors(colors);
            List<BarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(xAxisValue, dataSets);
            data.setValueTextSize(25f);
            data.setValueTextColor(Color.parseColor("#FFA84543"));
            data.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                    NumberFormat nf = NumberFormat.getPercentInstance();
                    nf.setMinimumFractionDigits(2);
                    nf.setMaximumFractionDigits(2);//保留两位小数
                    return nf.format(v);
                }
            });
            barChart.setData(data);
        }

        barChart.invalidate();


    }
}
