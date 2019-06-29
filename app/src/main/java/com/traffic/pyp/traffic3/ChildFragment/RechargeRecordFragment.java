package com.traffic.pyp.traffic3.ChildFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.traffic.pyp.traffic3.Adapter.RechargeRecordAdapter;
import com.traffic.pyp.traffic3.Bean.RechargeRecordBean;
import com.traffic.pyp.traffic3.R;
import com.traffic.pyp.traffic3.SQLite.DBManager;

import java.util.ArrayList;
import java.util.List;

public class RechargeRecordFragment extends Fragment {
    private TextView userName, total;
    private ListView listView;
    private List<RechargeRecordBean> data;
    private RechargeRecordAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_getusercardused, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        upData();
    }

    private void initView(View view) {
        userName = view.findViewById(R.id.userName);
        userName.setText("admin");
        total = view.findViewById(R.id.total);
        listView = view.findViewById(R.id.listView);
        data = new ArrayList<>();
        adapter = new RechargeRecordAdapter(data, getContext());
        listView.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    private void upData() {
        data.clear();
        List<RechargeRecordBean> list = new DBManager(getContext()).query();
        int totalMoney = 0;
        for (int i = 0; i < list.size(); i++) {
            data.add(list.get(i));
            totalMoney += list.get(i).getRechargeMoney();
        }
        total.setText("总支出" + totalMoney);
        adapter.notifyDataSetChanged();
    }
}