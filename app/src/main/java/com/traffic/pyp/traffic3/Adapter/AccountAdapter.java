package com.traffic.pyp.traffic3.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.traffic.pyp.traffic3.Application.App;
import com.traffic.pyp.traffic3.Bean.AccountBean;
import com.traffic.pyp.traffic3.Dialog.RechargeDialog;
import com.traffic.pyp.traffic3.R;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends BaseAdapter {
    private List<AccountBean> data;
    private Context context;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private Boolean[] CompoundButton_isChecked;//记录复选按钮状态

    public AccountAdapter(List<AccountBean> data, Context context, Fragment fragment, FragmentManager fragmentManager) {
        this.data = data;
        this.context = context;
        this.fragment = fragment;
        this.fragmentManager = fragmentManager;
        CompoundButton_isChecked = new Boolean[data.size()];
        for (int i = 0; i < CompoundButton_isChecked.length; i++)
            CompoundButton_isChecked[i] = false;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return this;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_account, parent, false);
            holder = new ViewHolder();
            holder.item = convertView.findViewById(R.id.item);
            holder.carID = convertView.findViewById(R.id.carID);
            holder.carImg = convertView.findViewById(R.id.carImg);
            holder.carNumber = convertView.findViewById(R.id.carNumber);
            holder.carUsers = convertView.findViewById(R.id.carUsers);
            holder.carMoney = convertView.findViewById(R.id.carMoney);
            holder.cb_recharge = convertView.findViewById(R.id.cb_recharge);
            holder.btn_recharge = convertView.findViewById(R.id.btn_recharge);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.carID.setText(data.get(position).getCarID() + "");
        holder.carImg.setImageResource(data.get(position).getCarImg());
        holder.carNumber.setText(data.get(position).getCarNumber() + "");
        holder.carUsers.setText(data.get(position).getCarUsers() + "");
        holder.carMoney.setText(data.get(position).getCarMoney() + "");
        holder.cb_recharge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CompoundButton_isChecked[position] = isChecked;
                List<Integer> rechargeCarId = new ArrayList<>();
                List<String> rechargePlate = new ArrayList<>();
                for (int i = 0; i < CompoundButton_isChecked.length; i++)
                    if (CompoundButton_isChecked[i]) {
                        rechargeCarId.add(data.get(i).getCarID());
                        rechargePlate.add(data.get(i).getCarNumber());
                    }
                App.setRechargeCarId(rechargeCarId);
                App.setRechargePlate(rechargePlate);
            }
        });
        holder.btn_recharge.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public void onClick(View v) {
                List<Integer> rechargeCarId = new ArrayList<>();
                List<String> rechargePlate = new ArrayList<>();
                rechargeCarId.add(data.get(position).getCarID());
                rechargePlate.add(data.get(position).getCarNumber());
                if (fragmentManager != null) {
                    RechargeDialog rechargeDialog = new RechargeDialog(context, rechargeCarId, rechargePlate);
                    rechargeDialog.setTargetFragment(fragment, 0);
                    rechargeDialog.show(fragmentManager, "Recharge");
                }
            }
        });
        //低于警告值更改背景颜色
        try {
            if (data.get(position).getCarMoney() < App.getAlerting()) {
                Log.i("zjh_changeColor", "color_#ffcc00");
                holder.item.setBackgroundColor(Color.parseColor("#ffcc00"));
            } else {
                Log.i("zjh_changeColor", "color_#ffffff");
                holder.item.setBackgroundColor(Color.parseColor("color_#ffffff"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    static class ViewHolder {
        LinearLayout item;
        TextView carID;
        ImageView carImg;
        TextView carNumber;
        TextView carUsers;
        TextView carMoney;
        CheckBox cb_recharge;
        Button btn_recharge;
    }
}
