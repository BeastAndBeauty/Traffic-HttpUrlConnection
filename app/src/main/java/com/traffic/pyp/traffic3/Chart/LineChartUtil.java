package com.traffic.pyp.traffic3.Chart;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：paopao on 2019/1/16 17:53
 * <p>
 * 作用: 折线图
 */
public class LineChartUtil {

    public static LineData initSingleLineChart(Context context, LineChart mLineChart, String[] count, float[] datas) {

        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < count.length; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues.add(count[i]);
        }

        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < count.length; i++) {
            yValues.add(new Entry(datas[i], i));
        }
        //设置折线的样式
        LineDataSet dataSet = new LineDataSet(yValues, "");
        //用y轴的集合来设置参数
        dataSet.setLineWidth(1.75f); // 线宽
        dataSet.setCircleSize(2f);// 显示的圆形大小
        dataSet.setColor(Color.rgb(89, 194, 230));// 折线显示颜色
        dataSet.setCircleColor(Color.rgb(89, 194, 230));// 圆形折点的颜色
        dataSet.setHighLightColor(Color.GREEN); // 高亮的线的颜色
        dataSet.setHighlightEnabled(true);
        dataSet.setValueTextColor(Color.rgb(89, 194, 230)); //数值显示的颜色
        dataSet.setValueTextSize(30f);     //数值显示的大小

        ArrayList<LineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        //构建一个LineData  将dataSets放入
        LineData lineData = new LineData(xValues, dataSets);
        return lineData;
    }

    public static LineData initDoubleLineChart(Context context, LineChart mLineChart, String[] count, float[] datas1, float[] datas2) {

        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < count.length; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues.add(count[i]);
        }

        // y轴的数据
        ArrayList<Entry> yValues1 = new ArrayList<Entry>();
        for (int i = 0; i < count.length; i++) {
            yValues1.add(new Entry(datas1[i], i));
        }

        // y轴的数据
        ArrayList<Entry> yValues2 = new ArrayList<Entry>();
        for (int i = 0; i < count.length; i++) {
            yValues2.add(new Entry(datas2[i], i));
        }

        LineDataSet dataSet = new LineDataSet(yValues1, "null");
        //dataSet.enableDashedLine(10f, 10f, 0f);//将折线设置为曲线(即设置为虚线)
        //用y轴的集合来设置参数
        dataSet.setLineWidth(1.75f); // 线宽
        dataSet.setCircleSize(3f);// 显示的圆形大小
        dataSet.setColor(Color.rgb(89, 194, 230));// 折线显示颜色
        dataSet.setCircleColor(Color.rgb(89, 194, 230));// 圆形折点的颜色
        dataSet.setHighLightColor(Color.BLACK); // 高亮的线的颜色
        dataSet.setHighlightEnabled(true);
        dataSet.setValueTextColor(Color.rgb(89, 194, 230)); //数值显示的颜色
        dataSet.setValueTextSize(30f);     //数值显示的大小

        LineDataSet dataSet1 = new LineDataSet(yValues2, "null");

        //用y轴的集合来设置参数
        dataSet1.setLineWidth(1.75f);
        dataSet1.setCircleSize(3f);
        dataSet1.setColor(Color.rgb(252, 76, 122));
        dataSet1.setCircleColor(Color.rgb(252, 76, 122));
        dataSet1.setHighLightColor(Color.BLACK);
        dataSet1.setHighlightEnabled(true);
        dataSet1.setValueTextColor(Color.rgb(252, 76, 122));
        dataSet1.setValueTextSize(30f);

        //构建一个类型为LineDataSet的ArrayList 用来存放所有 y的LineDataSet   他是构建最终加入LineChart数据集所需要的参数
        ArrayList<LineDataSet> dataSets = new ArrayList<>();

        //将数据加入dataSets
        dataSets.add(dataSet);
        dataSets.add(dataSet1);

        //构建一个LineData  将dataSets放入
        LineData lineData = new LineData(xValues, dataSets);
        return lineData;
    }

    public static void initDataStyle(LineChart lineChart, LineData lineData, Context context) {
        //设置点击折线点时，显示其数值
//        MyMakerView mv = new MyMakerView(context, R.layout.item_mark_layout);
//        mLineChart.setMarkerView(mv);
        lineChart.setDrawBorders(false); //在折线图上添加边框
        lineChart.setDrawGridBackground(false); //表格颜色
        lineChart.setGridBackgroundColor(Color.GRAY & 0x70FFFFFF); //表格的颜色，设置一个透明度
        lineChart.setTouchEnabled(true); //可点击
        lineChart.setDragEnabled(false);  //可拖拽
        lineChart.setScaleEnabled(false);  //可缩放
        lineChart.setPinchZoom(false);
        lineChart.setBackgroundColor(Color.parseColor("#f9f9f9")); //设置背景颜色

        lineChart.setData(lineData);
        Legend mLegend = lineChart.getLegend(); //设置标示，就是那个一组y的value的
        mLegend.setForm(Legend.LegendForm.SQUARE); //样式
        mLegend.setFormSize(30f); //字体
        mLegend.setTextColor(Color.GRAY); //颜色
        lineChart.setVisibleXRange(0, 5);   //x轴可显示的坐标范围
        XAxis xAxis = lineChart.getXAxis();  //x轴的标示
        xAxis.setPosition(XAxis.XAxisPosition.TOP); //x轴位置
        xAxis.setTextColor(Color.GRAY);    //字体的颜色
        xAxis.setTextSize(20f); //字体大小
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setGridColor(Color.GRAY);//网格线颜色
        xAxis.setDrawGridLines(false); //不显示网格线
        YAxis axisLeft = lineChart.getAxisLeft(); //y轴左边标示
        YAxis axisRight = lineChart.getAxisRight(); //y轴右边标示
        axisLeft.setTextColor(Color.GRAY); //字体颜色
        axisLeft.setTextSize(20f); //字体大小
        axisLeft.setStartAtZero(false);
        //axisLeft.setAxisMaxValue(800f); //最大值
        axisLeft.setLabelCount(0, false); //显示格数
        axisLeft.setGridColor(Color.GRAY); //网格线颜色

        axisRight.setDrawAxisLine(false);
        axisRight.setDrawGridLines(false);
        axisRight.setDrawLabels(false);
        //设置比例图标的显示隐藏
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);
        //设置动画效果
        lineChart.animateY(2000, Easing.EasingOption.Linear);
        lineChart.animateX(2000, Easing.EasingOption.Linear);
        lineChart.invalidate();
        //lineChart.animateX(2500);  //立即执行动画
    }

    public static void initDataStyle2(LineChart lineChart, LineData lineData, Context context) {
        //设置点击折线点时，显示其数值
//        MyMakerView mv = new MyMakerView(context, R.layout.item_mark_layout);
//        mLineChart.setMarkerView(mv);
        lineChart.setDescriptionPosition(1600f, 25f);
        lineChart.setDrawBorders(false); //在折线图上添加边框
        lineChart.setDrawGridBackground(false); //表格颜色
        lineChart.setGridBackgroundColor(Color.GRAY & 0x70FFFFFF); //表格的颜色，设置一个透明度
        lineChart.setTouchEnabled(true); //可点击
        lineChart.setDragEnabled(false);  //可拖拽
        lineChart.setScaleEnabled(false);  //可缩放
        lineChart.setPinchZoom(false);
        lineChart.setBackgroundColor(Color.parseColor("#f9f9f9")); //设置背景颜色

        lineChart.setData(lineData);
        lineData.setDrawValues(false);
        Legend mLegend = lineChart.getLegend(); //设置标示，就是那个一组y的value的
        mLegend.setForm(Legend.LegendForm.SQUARE); //样式
        mLegend.setFormSize(30f); //字体
        mLegend.setTextColor(Color.GRAY); //颜色
        XAxis xAxis = lineChart.getXAxis();  //x轴的标示
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //x轴位置
        xAxis.setTextColor(Color.GRAY);    //字体的颜色
        xAxis.setTextSize(20f); //字体大小
        xAxis.setGridColor(Color.GRAY);//网格线颜色
        xAxis.setDrawGridLines(false); //不显示网格线
        xAxis.setLabelsToSkip(0);
        YAxis axisLeft = lineChart.getAxisLeft(); //y轴左边标示
        YAxis axisRight = lineChart.getAxisRight(); //y轴右边标示
        axisLeft.setTextColor(Color.GRAY); //字体颜色
        axisLeft.setTextSize(20f); //字体大小
        axisLeft.setStartAtZero(false);
        //axisLeft.setAxisMaxValue(800f); //最大值
        axisLeft.setLabelCount(6, false); //显示格数
        axisLeft.resetAxisMaxValue();    //重新设置Y轴坐标最大为多少，自动调整
        axisLeft.resetAxisMinValue();    //重新设置Y轴坐标，自动调整
        axisLeft.setGridColor(Color.GRAY); //网格线颜色

        axisRight.setDrawAxisLine(false);
        axisRight.setDrawGridLines(false);
        axisRight.setDrawLabels(false);
        //设置比例图标的显示隐藏
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);
        //设置动画效果
        lineChart.animateY(2000, Easing.EasingOption.Linear);
        lineChart.animateX(2000, Easing.EasingOption.Linear);
        lineChart.invalidate();
        //lineChart.animateX(2500);  //立即执行动画
    }

}
