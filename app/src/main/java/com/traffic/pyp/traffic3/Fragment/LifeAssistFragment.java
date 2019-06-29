package com.traffic.pyp.traffic3.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.traffic.pyp.traffic3.Adapter.LifeAssistWeatherCaseAdapter;
import com.traffic.pyp.traffic3.Callback.OnResponseListener;
import com.traffic.pyp.traffic3.Chart.LineChartUtil;
import com.traffic.pyp.traffic3.ChildFragment.LifeAssistAirQualityFragment;
import com.traffic.pyp.traffic3.ChildFragment.LifeAssistCarbonDioxideFragment;
import com.traffic.pyp.traffic3.ChildFragment.LifeAssistHumidityFragment;
import com.traffic.pyp.traffic3.ChildFragment.LifeAssistTemperatureFragment;
import com.traffic.pyp.traffic3.R;
import com.traffic.pyp.traffic3.Request.GetLifeIndexRequest;
import com.traffic.pyp.traffic3.Request.GetSenseByNameRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * 生活助手
 */
public class LifeAssistFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {


    private View view;
    private LineChart lineChart;
    private ViewPager life_assist_viewPager;
    private List<Fragment> fragments;
    private LifeAssistWeatherCaseAdapter lifeAssistWeatherCaseAdapter;
    private List<TextView> texts;
    private List<List<String>> lists;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    XRay_index.setText(lists.get(0).get(0));
                    XRay_tip.setText(lists.get(0).get(1));
                    cold_index.setText(lists.get(1).get(0));
                    cold_tip.setText(lists.get(1).get(1));
                    clothes_index.setText(lists.get(2).get(0));
                    clothes_tip.setText(lists.get(2).get(1));
                    sport_index.setText(lists.get(3).get(0));
                    sport_tip.setText(lists.get(3).get(1));
                    air_index.setText(lists.get(4).get(0));
                    air_tip.setText(lists.get(4).get(1));
                    break;
                case 2:
                    String a=msg.getData().getString("temperature_value","20°");
                    show_now_temperature.setText(a);
                    break;
            }

            super.handleMessage(msg);
        }
    };
    private TextView XRay_index;
    private TextView XRay_tip;
    private TextView cold_index;
    private TextView cold_tip;
    private TextView clothes_index;
    private TextView clothes_tip;
    private TextView sport_index;
    private TextView sport_tip;
    private TextView air_index;
    private TextView air_tip;
    private ImageView image_reflesh;
    private TextView show_now_temperature;

    private Timer timer;
    private TimerTask timerTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_life_assist, container, false);

        initView();
        initData();
        initViewPager();
        setTodayText();

        return view;
    }

    private void setTodayText() {
        if(timer==null){
            timer=new Timer();
        }
        if(timerTask==null){
            timerTask=new TimerTask() {
                @Override
                public void run() {
                    new GetLifeIndexRequest().sendRequest(new OnResponseListener() {
                        @Override
                        public void onResponse(Object result) {
                            lists = (List<List<String>>) result;
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            Log.i("paopao", "result" + lists.get(0).get(0));
                            Log.i("paopao", "result" + lists.get(0).get(1));
                        }
                    });
                }
            };
        }

        timer.schedule(timerTask,0,3000);

    }

    private void initViewPager() {

        fragments = new ArrayList<>();
        Fragment airQuality = new LifeAssistAirQualityFragment();
        Fragment temperature = new LifeAssistTemperatureFragment();
        Fragment humidity = new LifeAssistHumidityFragment();
        Fragment carbonDioxide = new LifeAssistCarbonDioxideFragment();
        fragments.add(airQuality);
        fragments.add(temperature);
        fragments.add(humidity);
        fragments.add(carbonDioxide);
        lifeAssistWeatherCaseAdapter = new LifeAssistWeatherCaseAdapter(getChildFragmentManager(), fragments);
        life_assist_viewPager.setAdapter(lifeAssistWeatherCaseAdapter);
        life_assist_viewPager.setOnPageChangeListener(this);

    }

    private void initData() {
        //设置x轴的数据
        String[] numX = {"昨天", "今天", "明天", "周五", "周六", "周日"};
        //设置y轴的数据
        float[] datas1 = {22, 24, 25, 25, 25, 22};//数据
        float[] datas2 = {14, 15, 16, 17, 16, 16};//数据
        LineData lineData;
        //设置名称
        lineData = LineChartUtil.initDoubleLineChart(getContext(), lineChart, numX, datas1, datas2);
        LineChartUtil.initDataStyle(lineChart, lineData, getContext());

    }

    private void initView() {
        lineChart = view.findViewById(R.id.lineChart);
        life_assist_viewPager = view.findViewById(R.id.life_assist_viewPager);
        TextView text_airQuality = view.findViewById(R.id.text_airQuality);
        TextView text_temperature = view.findViewById(R.id.text_temperature);
        TextView text_humidity = view.findViewById(R.id.text_humidity);
        TextView text_carbonDioxide = view.findViewById(R.id.text_carbonDioxide);
        text_airQuality.setOnClickListener(this);
        text_temperature.setOnClickListener(this);
        text_humidity.setOnClickListener(this);
        text_carbonDioxide.setOnClickListener(this);
        texts = new ArrayList<>();
        texts.add(text_airQuality);
        texts.add(text_temperature);
        texts.add(text_humidity);
        texts.add(text_carbonDioxide);

        XRay_index = (TextView) view.findViewById(R.id.XRay_index);
        XRay_index.setOnClickListener(this);
        XRay_tip = (TextView) view.findViewById(R.id.XRay_tip);
        XRay_tip.setOnClickListener(this);
        cold_index = (TextView) view.findViewById(R.id.cold_index);
        cold_index.setOnClickListener(this);
        cold_tip = (TextView) view.findViewById(R.id.cold_tip);
        cold_tip.setOnClickListener(this);
        clothes_index = (TextView) view.findViewById(R.id.clothes_index);
        clothes_index.setOnClickListener(this);
        clothes_tip = (TextView) view.findViewById(R.id.clothes_tip);
        clothes_tip.setOnClickListener(this);
        sport_index = (TextView) view.findViewById(R.id.sport_index);
        sport_index.setOnClickListener(this);
        sport_tip = (TextView) view.findViewById(R.id.sport_tip);
        sport_tip.setOnClickListener(this);
        air_index = (TextView) view.findViewById(R.id.air_index);
        air_index.setOnClickListener(this);
        air_tip = (TextView) view.findViewById(R.id.air_tip);
        air_tip.setOnClickListener(this);

        show_now_temperature=view.findViewById(R.id.show_now_temperature);

        image_reflesh=view.findViewById(R.id.image_reflesh);
        image_reflesh.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        setTextViewBackground(i);

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_airQuality:
                life_assist_viewPager.setCurrentItem(0);
                setTextViewBackground(0);
                break;
            case R.id.text_temperature:
                life_assist_viewPager.setCurrentItem(1);
                setTextViewBackground(1);
                break;
            case R.id.text_humidity:
                life_assist_viewPager.setCurrentItem(2);
                setTextViewBackground(2);
                break;
            case R.id.text_carbonDioxide:
                life_assist_viewPager.setCurrentItem(3);
                setTextViewBackground(3);
                break;
            case R.id.image_reflesh:
                refleshTemperature();
        }
    }

    private void refleshTemperature(){
        new GetSenseByNameRequest(new Object[]{"temperature"}).sendRequest(new OnResponseListener() {
            @Override
            public void onResponse(Object result) {
                Bundle bundle=new Bundle();
                Message message=new Message();
                message.what=2;
                bundle.putString("temperature_value",String.valueOf(result)+"°");
                message.setData(bundle);

                handler.sendMessage(message);
            }
        });
    }

    //设置空气质量  温度  相对湿度  二氧化碳--TextView显示矩形框
    private void setTextViewBackground(int position) {
        for (int i = 0; i < texts.size(); i++) {
            if (i == position) {
                texts.get(i).setBackgroundResource(R.drawable.rectangle_text_view);
            } else {
                texts.get(i).setBackgroundResource(0);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
        if(timerTask!=null){
            timerTask.cancel();
            timerTask=null;
        }
    }
}
