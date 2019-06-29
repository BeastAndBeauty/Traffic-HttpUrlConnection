package com.traffic.pyp.traffic3.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.traffic.pyp.traffic3.Callback.OnResponseListener;
import com.traffic.pyp.traffic3.R;
import com.traffic.pyp.traffic3.Request.GetAllSenseRequest;
import com.traffic.pyp.traffic3.Request.GetRoadStatusRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoadStatusFragment extends Fragment implements View.OnClickListener {


    private View view;
    private ImageView back_ground;
    private TextView huangchegnlu2;
    private TextView huangchenglu1;
    private TextView xueyuanlu;
    private TextView lianxianglu;
    private TextView xinfulu;
    private TextView yiyuanlu;
    private TextView huangchenglu3;
    private TextView huangchentggaosulu;
    private TextView tingchechang;

    private ImageView image_reflesh;

    //路况查询定时器
    private Timer status_timer;
    private int getStatusParam = 0;
    private TimerTask statusTimerTask;
    private List<String> list = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    int color_value = Color.parseColor(msg.getData().getString("color", "#6ab82e"));
                    switch (getStatusParam % 7 + 1) {
                        case 1:
                            xueyuanlu.setBackgroundColor(color_value);
                            break;
                        case 2:
                            lianxianglu.setBackgroundColor(color_value);
                            break;
                        case 3:
                            yiyuanlu.setBackgroundColor(color_value);
                            break;
                        case 4:
                            xinfulu.setBackgroundColor(color_value);
                            break;
                        case 5:
                            huangchenglu1.setBackgroundColor(color_value);
                            huangchegnlu2.setBackgroundColor(color_value);
                            huangchenglu3.setBackgroundColor(color_value);
                            break;
                        case 6:
                            huangchentggaosulu.setBackgroundColor(color_value);
                            break;
                        case 7:
                            tingchechang.setBackgroundColor(color_value);
                            break;
                    }
                    break;
                case 2:
                    temperature_value.setText(list.get(0));
                    humidity_value.setText(list.get(1));
                    pm_value.setText(list.get(2));
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private TextView date;
    private TextView today;
    private TextView temperature_value;
    private TextView humidity_value;
    private TextView pm_value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_road_status, container, false);
        initView(view);
        getNowDate();
        getStatusFromNet();
        return view;
    }

    private void getNowDate(){
        Calendar calendar=Calendar.getInstance();
        String time=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        String to="";
        Date dat=new Date();
        switch (dat.getDay()){
            case 0:
                to="星期天";
                break;
            case 1:
                to="星期一";
                break;
            case 2:
                to="星期二";
                break;
            case 3:
                to="星期三";
                break;
            case 4:
                to="星期四";
                break;
            case 5:
                to="星期五";
                break;
            case 6:
                to="星期六";
                break;
        }

        date.setText(time);
        today.setText(to);

    }

    private void getStatusFromNet() {
        if (status_timer == null) {
            status_timer = new Timer();
        }
        if (statusTimerTask == null) {
            statusTimerTask = new TimerTask() {
                @Override
                public void run() {
                    new GetRoadStatusRequest(new Object[]{(getStatusParam % 7 + 1)}).sendRequest(new OnResponseListener() {
                        @Override
                        public void onResponse(Object result) {
                            Message message = new Message();
                            message.what = 1;
                            Bundle bundle = new Bundle();
                            bundle.putString("color", (String) result);
                            message.setData(bundle);
                            handler.sendMessage(message);
                            getStatusParam++;
                        }
                    });
                }
            };
        }
        status_timer.schedule(statusTimerTask, 0, 400);
    }

    private void initView(View view) {
        back_ground = (ImageView) view.findViewById(R.id.back_ground);
        huangchegnlu2 = (TextView) view.findViewById(R.id.huangchegnlu2);
        huangchenglu1 = (TextView) view.findViewById(R.id.huangchenglu1);
        xueyuanlu = (TextView) view.findViewById(R.id.xueyuanlu);
        lianxianglu = (TextView) view.findViewById(R.id.lianxianglu);
        xinfulu = (TextView) view.findViewById(R.id.xinfulu);
        yiyuanlu = (TextView) view.findViewById(R.id.yiyuanlu);
        huangchenglu3 = (TextView) view.findViewById(R.id.huangchenglu3);
        huangchentggaosulu = (TextView) view.findViewById(R.id.huangchentggaosulu);
        tingchechang = (TextView) view.findViewById(R.id.tingchechang);

        image_reflesh = view.findViewById(R.id.image_reflesh);
        image_reflesh.setOnClickListener(this);
        date = (TextView) view.findViewById(R.id.date);
        date.setOnClickListener(this);
        today = (TextView) view.findViewById(R.id.today);
        today.setOnClickListener(this);
        temperature_value = (TextView) view.findViewById(R.id.temperature_value);
        temperature_value.setOnClickListener(this);
        humidity_value = (TextView) view.findViewById(R.id.humidity_value);
        humidity_value.setOnClickListener(this);
        pm_value = (TextView) view.findViewById(R.id.pm_value);
        pm_value.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new GetAllSenseRequest().sendRequest(new OnResponseListener() {
            @Override
            public void onResponse(Object result) {
                list = (List<String>) result;
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if(status_timer!=null){
            status_timer.cancel();
            status_timer=null;
        }
        if (statusTimerTask!=null){
            statusTimerTask.cancel();
            statusTimerTask=null;
        }
    }
}
