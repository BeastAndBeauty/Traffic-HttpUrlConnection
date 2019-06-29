package com.traffic.pyp.traffic3.Chart;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
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

import static com.github.mikephil.charting.components.Legend.LegendPosition.ABOVE_CHART_RIGHT;


public class MultilevelBarChartManager {
    public void setBaseAttribute(BarChart barChart, final String[] xAxisValue,
                                 List<float[]> yAxisValue, String title,int[] PIE_COLORS,String[] lables){
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(false);
        barChart.setDescription("");
        barChart.setMaxVisibleValueCount(60);
        barChart.setDrawGridBackground(false);
        barChart.setPinchZoom(true);//设置按比例放缩柱状图

        //设置X轴
        XAxis xAxis = barChart.getXAxis();//获取x轴
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴标签显示位置
        xAxis.setDrawGridLines(false);//不绘制格网线
        xAxis.setTextSize(50f);
        xAxis.setValueFormatter(new XAxisValueFormatter() {
            @Override
            public String getXValue(String s, int i, ViewPortHandler viewPortHandler) {
                return xAxisValue[i];
            }
        });

        //设置Y轴
        YAxis leftAxis = barChart.getAxisLeft();//获取左侧y轴
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);//设置y轴标签显示在外侧
        leftAxis.setAxisMinValue(0f);//设置Y轴最小值
        leftAxis.setDrawGridLines(true);//绘制格网线
        leftAxis.setDrawLabels(true);//绘制y轴标签
        leftAxis.setDrawAxisLine(true);//绘制y轴
        leftAxis.setEnabled(true);
        leftAxis.setTextSize(50f);
        barChart.getAxisRight().setEnabled(false);//禁用右侧y轴

        //设置图例
        Legend legend = barChart.getLegend();
        legend.setPosition(ABOVE_CHART_RIGHT);
        List<Integer> colos=new ArrayList<>();
        colos.add(PIE_COLORS[0]);
        colos.add(PIE_COLORS[1]);
        legend.setComputedColors(colos);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);//图例中的文字方向
        legend.setForm(Legend.LegendForm.SQUARE);//图例窗体的形状
        legend.setFormSize(20f);//图例窗体的大小
        legend.setTextSize(30f);//图例文字的大小

        //设置数据

        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0, n = yAxisValue.size(); i < n; ++i) {
            entries.add(new BarEntry(yAxisValue.get(i), i));
        }
        BarDataSet set1;
        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(entries, "");
            set1.setColors(PIE_COLORS);
            List<BarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(xAxisValue, dataSets);
            set1.setStackLabels(lables);
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

    }
}
