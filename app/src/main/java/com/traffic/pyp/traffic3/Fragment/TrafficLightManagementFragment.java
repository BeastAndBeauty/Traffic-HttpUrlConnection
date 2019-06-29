package com.traffic.pyp.traffic3.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.traffic.pyp.traffic3.Adapter.TrafficLightManagementAdapter;
import com.traffic.pyp.traffic3.Application.App;
import com.traffic.pyp.traffic3.Bean.TrafficLightManagementBean;
import com.traffic.pyp.traffic3.Callback.OnResponseListener;
import com.traffic.pyp.traffic3.Dialog.LightSettingsDialog;
import com.traffic.pyp.traffic3.R;
import com.traffic.pyp.traffic3.Request.GetTrafficLightConfigActionRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrafficLightManagementFragment extends Fragment implements View.OnClickListener {

    private Spinner spinner;
    private List<String> list;
    private ArrayAdapter<String> arrayAdapter;
    private ListView tableList;
    private List<TrafficLightManagementBean> data;
    private List<TrafficLightManagementBean> listBeans;
    private TrafficLightManagementAdapter adapter;
    private Button btn_Search, btn_AllSettings;

    private String sort;//记录排序
    private int[] sortLightTime;//记录id排序

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trafficlightmanagement, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        upData();
    }


    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        list = new ArrayList<>();
        list.add("路口升序");
        list.add("路口降序");
        list.add("红灯升序");
        list.add("红灯降序");
        list.add("绿灯升序");
        list.add("绿灯降序");
        list.add("黄灯升序");
        list.add("黄灯降序");
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new SelectedListener());
        btn_Search = view.findViewById(R.id.btn_Search);
        btn_AllSettings = view.findViewById(R.id.btn_AllSettings);
        btn_Search.setOnClickListener(this);
        btn_AllSettings.setOnClickListener(this);
        data = new ArrayList<>();
        listBeans = new ArrayList<>();
        tableList = view.findViewById(R.id.tableList);
        adapter = new TrafficLightManagementAdapter(data, getContext(), this, getFragmentManager());
        tableList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_Search) {
            upData();
        } else if (v.getId() == R.id.btn_AllSettings) {
            if (App.getTrafficLightID() == null || App.getTrafficLightID().size() == 0) {
                App.showAlertDialog(getContext(), "警告", "请选择需要批量设置的操作项", null);
            } else {
                if (getFragmentManager() != null) {
                    LightSettingsDialog lightSettingsDialog = new LightSettingsDialog();
                    lightSettingsDialog.setTargetFragment(this, 0);
                    lightSettingsDialog.show(getFragmentManager(), "LightSettings");
                }
            }
        }
    }

    private void upData() {
        listBeans.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    if (i != 1) {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    final int finalI = i;
                    new GetTrafficLightConfigActionRequest(new Object[]{String.valueOf(i)})
                            .sendRequest(new OnResponseListener() {
                                @Override
                                public void onResponse(Object result) {
                                    try {
                                        Map<String, Integer> map = (Map<String, Integer>) result;
                                        listBeans.add(new TrafficLightManagementBean(map.get("TrafficLightId"),
                                                map.get("RedTime"), map.get("GreenTime"),
                                                map.get("YellowTime")));
                                        if (finalI == 5) {
                                            Message message = new Message();
                                            message.what = 200;
                                            handler.sendMessage(message);
                                        }
                                    } catch (Exception e) {
                                    }

                                }
                            });
                }
            }
        }).start();
    }

    private class SelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            sort = list.get(position);//更改排序规则
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 0:
                    upData();
                    break;
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    data.clear();
                    if (sort.equals(list.get(0))) {
                        sortLightTime = new int[]{0, 1, 2, 3, 4};
                        for (int i = 0; i < listBeans.size(); i++)
                            data.add(listBeans.get(i));
                    } else if (sort.equals(list.get(1))) {
                        sortLightTime = new int[]{4, 3, 2, 1, 0};
                        for (int i = listBeans.size() - 1; i >= 0; i--)
                            data.add(listBeans.get(i));
                    } else if (sort.equals(list.get(2))) {
                        int[] RedLightTime = new int[listBeans.size()];
                        for (int i = 0; i < listBeans.size(); i++)
                            RedLightTime[i] = listBeans.get(i).getRedLightTime();
                        sortLightTime = sort(RedLightTime, true);
                        for (int i = 0; i < sortLightTime.length; i++)
                            data.add(listBeans.get(sortLightTime[i]));
                    } else if (sort.equals(list.get(3))) {
                        int[] RedLightTime = new int[listBeans.size()];
                        for (int i = 0; i < listBeans.size(); i++)
                            RedLightTime[i] = listBeans.get(i).getRedLightTime();
                        sortLightTime = sort(RedLightTime, false);
                        for (int i = 0; i < sortLightTime.length; i++)
                            data.add(listBeans.get(sortLightTime[i]));
                    } else if (sort.equals(list.get(4))) {
                        int[] GreenLightTime = new int[listBeans.size()];
                        for (int i = 0; i < listBeans.size(); i++)
                            GreenLightTime[i] = listBeans.get(i).getGreenLightTime();
                        sortLightTime = sort(GreenLightTime, true);
                        for (int i = 0; i < sortLightTime.length; i++)
                            data.add(listBeans.get(sortLightTime[i]));
                    } else if (sort.equals(list.get(5))) {
                        int[] GreenLightTime = new int[listBeans.size()];
                        for (int i = 0; i < listBeans.size(); i++)
                            GreenLightTime[i] = listBeans.get(i).getGreenLightTime();
                        sortLightTime = sort(GreenLightTime, false);
                        for (int i = 0; i < sortLightTime.length; i++)
                            data.add(listBeans.get(sortLightTime[i]));
                    } else if (sort.equals(list.get(6))) {
                        int[] YellowLightTime = new int[listBeans.size()];
                        for (int i = 0; i < listBeans.size(); i++)
                            YellowLightTime[i] = listBeans.get(i).getYellowLightTime();
                        sortLightTime = sort(YellowLightTime, true);
                        for (int i = 0; i < sortLightTime.length; i++)
                            data.add(listBeans.get(sortLightTime[i]));
                    } else if (sort.equals(list.get(7))) {
                        int[] YellowLightTime = new int[listBeans.size()];
                        for (int i = 0; i < listBeans.size(); i++)
                            YellowLightTime[i] = listBeans.get(i).getYellowLightTime();
                        sortLightTime = sort(YellowLightTime, false);
                        for (int i = 0; i < sortLightTime.length; i++)
                            data.add(listBeans.get(sortLightTime[i]));
                    }
                    adapter.notifyDataSetChanged();
            }
        }
    };

    /**
     * is true升序 false降序
     *
     * @param array
     * @param is
     * @return
     */
    private int[] sort(int[] array, Boolean is) {
        int[] id = new int[]{0, 1, 2, 3, 4};
        if (is)
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[i] > array[j]) {
                        int temp1 = array[i];
                        int temp2 = id[i];
                        array[i] = array[j];
                        array[j] = temp1;
                        id[i] = id[j];
                        id[j] = temp2;
                    }
                }
            }
        else
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[i] < array[j]) {
                        int temp1 = array[i];
                        int temp2 = id[i];
                        array[i] = array[j];
                        array[j] = temp1;
                        id[i] = id[j];
                        id[j] = temp2;
                    }
                }
            }

        return id;
    }
}
