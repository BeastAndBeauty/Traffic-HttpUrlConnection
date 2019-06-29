package com.traffic.pyp.traffic3.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.traffic.pyp.traffic3.Adapter.BusInfoCapacityDialogAdapter;
import com.traffic.pyp.traffic3.Adapter.BusInfoExpandableAdapter;
import com.traffic.pyp.traffic3.Bean.BusInfoDialogListViewBean;
import com.traffic.pyp.traffic3.Callback.OnResponseListener;
import com.traffic.pyp.traffic3.R;
import com.traffic.pyp.traffic3.Request.GetBusCapacityRequest;
import com.traffic.pyp.traffic3.Request.GetBusStationInfoRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * 公交查询Fragment
 */
public class BusInquiryFragment extends Fragment implements View.OnClickListener {

    private static final String TAG="paopao";

    private TextView total_capacity;
    private int now_capacity=0;
    private Button btn_detail;
    private ExpandableListView expandable_list_view;
    private BusInfoExpandableAdapter busInfoExpandableAdapter;
    private String[][] capacityChildStrings={{"(101人)","(101人)"},{"(101人)","(101人)"}};
    private String[][] timeChildStrings={{"5分钟到达","6分钟到达"},{"5分钟到达","7分钟到达"}};
    private String[][] distanceChildStrings={{"距离站台110米", "距离站台1100米"},{"距离站台300米", "距离站台1200米"}};
    private View view;
    //车载容量的定时器
    private Timer capacity_timer;
    private int getCapacityParam=0;
    private TimerTask capacityTimerTask;
    //距离车站距离的定时器
    private Timer distance_timer;
    private int getDistanceParam=0;
    private TimerTask distanceTimerTask;

    //Dialog变量
    private BusInfoCapacityDialogAdapter dialogAdapter;
    private AlertDialog alertDialog;
    private List<BusInfoDialogListViewBean> listDialogBeans;
    private TextView dialog_total_capacity;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                //请求车载容量
                case 1:busInfoExpandableAdapter.notifyDataSetChanged();
                    total_capacity.setText(String.valueOf(now_capacity));
                break;
                //请求站台距离
                case 2:busInfoExpandableAdapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_bus_inquiry, container, false);
        initView();
        initData();
        initDialog();
        getDataFromNet();
        return view;
    }

    private void initDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        listDialogBeans=new ArrayList<>();
        for(int i=1;i<16;i++){
            listDialogBeans.add(new BusInfoDialogListViewBean(i,i,100+i));
        }
        View dialogView=LayoutInflater.from(getActivity()).inflate(R.layout.bus_info_dialog,null);
        ListView dialogListView=dialogView.findViewById(R.id.dialog_listView);
        Button dialogButton=dialogView.findViewById(R.id.dialog_back);
        dialog_total_capacity=dialogView.findViewById(R.id.dialog_total_capacity);
        dialogButton.setOnClickListener(this);
        dialogAdapter=new BusInfoCapacityDialogAdapter(getActivity(),listDialogBeans);
        dialogListView.setAdapter(dialogAdapter);
        builder.setView(dialogView);
        builder.setCancelable(true);
        alertDialog=builder.create();
    }

    //请求接口获取数据
    private void getDataFromNet() {



        //获取车载容量
        if (capacity_timer==null){
            capacity_timer=new Timer();
        }
        if(capacityTimerTask==null){
            capacityTimerTask=new TimerTask() {
                @Override
                public void run() {
                    new GetBusCapacityRequest(new Object[]{getCapacityParam%2+1}).sendRequest(new OnResponseListener() {
                        @Override
                        public void onResponse(Object result) {
                            if(getCapacityParam%15+1==1){
                                now_capacity=0;
                                capacityChildStrings[0][0]="("+result.toString()+"人)";
                                capacityChildStrings[1][0]="("+result.toString()+"人)";
                                Log.d(TAG,"if_result"+(getCapacityParam%2+1)+"="+result.toString());
                            }else if(getCapacityParam%15+1==2){
                                capacityChildStrings[0][1]="("+result.toString()+"人)";
                                capacityChildStrings[1][1]="("+result.toString()+"人)";


                                Log.d(TAG,"else_result"+(getCapacityParam%2+1)+"="+result.toString());
                            }else if(getCapacityParam%15+1==15){
                                Message message=new Message();
                                message.what=1;
                                handler.sendMessage(message);
                            }
                            now_capacity+=(int)result;
                            Log.d(TAG,"now_capacity="+now_capacity);
                            listDialogBeans.get(getCapacityParam%15).setCapacity(Integer.valueOf(result.toString()));
                            getCapacityParam++;
                        }
                    });
                }
            };
        }
        capacity_timer.schedule(capacityTimerTask,0,200);

        //获取站台距离
        if (distance_timer==null){
            distance_timer=new Timer();
        }

            if(distanceTimerTask==null){
                distanceTimerTask=new TimerTask() {
                    @Override
                    public void run() {
                        new GetBusStationInfoRequest(new Object[]{getDistanceParam%2+1}).sendRequest(new OnResponseListener() {
                            @Override
                            public void onResponse(Object result) {
                                List<String[]> list= (List<String[]>) result;
                                if((getDistanceParam%2+1)==1){
                                    timeChildStrings[0][0]=list.get(0)[0];
                                    timeChildStrings[0][1]=list.get(1)[0];
                                    distanceChildStrings[0][0]=list.get(0)[1];
                                    distanceChildStrings[0][1]=list.get(1)[1];
                                }else {
                                    timeChildStrings[1][0]=list.get(0)[0];
                                    timeChildStrings[1][1]=list.get(1)[0];
                                    distanceChildStrings[1][0]=list.get(0)[1];
                                    distanceChildStrings[1][1]=list.get(1)[1];
                                    Message message=new Message();
                                    message.what=2;
                                    handler.sendMessage(message);
                                }
                                getDistanceParam++;
                            }
                        });

                    }
                };
            }


        distance_timer.schedule(distanceTimerTask,0,1500);

    }

    private void initData() {
        busInfoExpandableAdapter=new BusInfoExpandableAdapter(getActivity(),timeChildStrings,distanceChildStrings,capacityChildStrings);
        expandable_list_view.setAdapter(busInfoExpandableAdapter);
        expandable_list_view.expandGroup(0);
        expandable_list_view.expandGroup(1);
        expandable_list_view.setGroupIndicator(null);
    }

    private void initView() {
        expandable_list_view=view.findViewById(R.id.expandable_list_view);
        total_capacity=view.findViewById(R.id.total_capacity);
        btn_detail=view.findViewById(R.id.btn_detail);
        btn_detail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //详情按钮
            case R.id.btn_detail:
                alertDialog.show();
                dialog_total_capacity.setText(String.valueOf(now_capacity));
                break;
            case R.id.dialog_back:
                alertDialog.cancel();
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (capacity_timer != null) {
            capacity_timer.cancel();
            capacity_timer = null;
        }

        if (distance_timer != null) {
            distance_timer.cancel();
            distance_timer = null;
        }

        if (capacityTimerTask != null) {
            capacityTimerTask.cancel();
            capacityTimerTask = null;
        }

        if (distanceTimerTask != null) {
            distanceTimerTask.cancel();
            distanceTimerTask = null;
        }

    }
}
