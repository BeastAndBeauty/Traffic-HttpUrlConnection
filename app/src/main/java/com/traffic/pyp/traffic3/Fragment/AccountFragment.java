package com.traffic.pyp.traffic3.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.traffic.pyp.traffic3.Adapter.AccountAdapter;
import com.traffic.pyp.traffic3.Bean.AccountBean;
import com.traffic.pyp.traffic3.Callback.OnResponseListener;
import com.traffic.pyp.traffic3.R;
import com.traffic.pyp.traffic3.Request.GetCarAccountBalanceRequest;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment {

    private ListView carList;
    private List<AccountBean> data;
    private AccountAdapter accountAdapter;
    private int[] CarId = {1, 2, 3, 4};
    private int[] im_car = {R.drawable.ic_car1, R.drawable.ic_car2, R.drawable.ic_car3, R.drawable.ic_car4};
    private String[] plate = {"辽A10001", "辽A10002", "辽A10003", "辽A10004"};
    private String[] name = {"车主:张三", "车主:李四", "车主:王五", "车主:赵六"};
    private int[] balance = new int[]{100, 200, 300, 400};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        upData(0);
    }

    @SuppressLint("CommitTransaction")
    private void initView(View view) {
        carList = view.findViewById(R.id.carList);
        data = new ArrayList<>();
        for (int i = 0; i < CarId.length; i++)
            data.add(new AccountBean(CarId[i], im_car[i], plate[i], name[i], balance[i]));
        if (getFragmentManager() != null) {
            accountAdapter = new AccountAdapter(data, getContext(), this, getFragmentManager());
        }
        carList.setAdapter(accountAdapter);
    }

    private void upData(final int i) {
        data.clear();
        new GetCarAccountBalanceRequest(new Object[]{i + 1}).
                sendRequest(new OnResponseListener() {
                    int finalI = i;

                    @Override
                    public void onResponse(Object result) {
                        try {
                            balance[finalI] = Integer.parseInt(result.toString());
                            if (++finalI < 4)
                                upData(finalI);
                            else {
                                for (int i = 0; i < CarId.length; i++)
                                    data.add(new AccountBean(CarId[i], im_car[i], plate[i], name[i], balance[i]));
                                accountAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 0:
                    upData(0);
                    break;
            }
        }
    }

}
