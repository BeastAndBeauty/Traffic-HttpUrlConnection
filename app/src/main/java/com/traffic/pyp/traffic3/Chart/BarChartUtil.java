package com.traffic.pyp.traffic3.Chart;

import android.annotation.SuppressLint;
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

/**
 * 作者：paopao on 2019/1/15 15:42
 * <p>
 * 作用:竖直柱状图
 */
public class BarChartUtil {


    public static void setBarChart(BarChart barChart, final String[] xAxisValue, float[] yAxisValue, String title) {
        //设置相关属性
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(false);
        barChart.setDescription("");
        barChart.setMaxVisibleValueCount(60);
        barChart.setDrawGridBackground(false);
        barChart.setPinchZoom(true);//设置按比例放缩柱状图
        barChart.setExtraBottomOffset(10);
        //x坐标轴设置
        XAxis xAxis = barChart.getXAxis();//获取x轴
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴标签显示位置
        xAxis.setDrawGridLines(false);//不绘制格网线
        xAxis.setTextSize(50f);
        //文字倾斜展示
        xAxis.setValueFormatter(new XAxisValueFormatter() {
            @Override
            public String getXValue(String s, int i, ViewPortHandler viewPortHandler) {
                return xAxisValue[i];
            }
        });

        //y轴设置
        YAxis leftAxis = barChart.getAxisLeft();//获取左侧y轴
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);//设置y轴标签显示在外侧
        leftAxis.setAxisMinValue(0f);//设置Y轴最小值
        leftAxis.setDrawGridLines(true);//绘制格网线
        leftAxis.setDrawLabels(true);//绘制y轴标签
        leftAxis.setDrawAxisLine(true);//绘制y轴
        leftAxis.setEnabled(true);
        leftAxis.setTextSize(50f);

        barChart.getAxisRight().setEnabled(false);//禁用右侧y轴

        // 图例设置
        Legend legend = barChart.getLegend();
        legend.setPosition(ABOVE_CHART_RIGHT);
        List<Integer> colos=new ArrayList<>();
        colos.add(Color.parseColor("#FF92D14F"));
        colos.add(Color.parseColor("#FF5080BF"));
        legend.setComputedColors(colos);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);//图例中的文字方向
        legend.setForm(Legend.LegendForm.SQUARE);//图例窗体的形状
        legend.setFormSize(0f);//图例窗体的大小
        legend.setTextSize(16f);//图例文字的大小


        //设置柱状图数据
        setBarChartData(barChart, xAxisValue, yAxisValue, title);
        barChart.setExtraBottomOffset(30);//距视图窗口底部的偏移，类似与paddingbottom
        barChart.setExtraTopOffset(30);//距视图窗口顶部的偏移，类似与paddingtop
        barChart.setExtraLeftOffset(30);
        barChart.setExtraRightOffset(30);
        barChart.animateX(2000);//数据显示动画，从左往右依次显示
        barChart.animateY(2000);//数据显示动画，从左往右依次显示
    }


    /**
     * 设置柱图
     *
     * @param barChart
     * @param yAxisValue
     * @param title
     */
    @SuppressLint("ResourceType")
    private static void setBarChartData(BarChart barChart, String[] xAxisValue, float[] yAxisValue, String title) {

        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0, n = yAxisValue.length; i < n; ++i) {
            entries.add(new BarEntry(yAxisValue[i], i));
        }
        BarDataSet set1;
        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(entries, title);
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
                    return String.valueOf(v);
                }
            });
            barChart.setData(data);
        }
    }

}
