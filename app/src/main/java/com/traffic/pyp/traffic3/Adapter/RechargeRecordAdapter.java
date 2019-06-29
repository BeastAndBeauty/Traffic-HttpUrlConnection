package com.traffic.pyp.traffic3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.traffic.pyp.traffic3.Bean.RechargeRecordBean;
import com.traffic.pyp.traffic3.R;

import java.util.List;

public class RechargeRecordAdapter extends BaseAdapter {
    private List<RechargeRecordBean> data;
    private Context context;

    public RechargeRecordAdapter(List<RechargeRecordBean> data, Context context) {
        this.data = data;
        this.context = context;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_rechargerecord, parent, false);
            holder = new ViewHolder();
            holder.rechargeUserName = convertView.findViewById(R.id.rechargeUserName);
            holder.rechargePlate = convertView.findViewById(R.id.rechargePlate);
            holder.rechargeMoney = convertView.findViewById(R.id.rechargeMoney);
            holder.rechargeTime = convertView.findViewById(R.id.rechargeTime);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.rechargeUserName.setText(data.get(position).getUserName() + "");
        holder.rechargePlate.setText(data.get(position).getRechargePlate());
        holder.rechargeMoney.setText(data.get(position).getRechargeMoney() + "");
        holder.rechargeTime.setText(data.get(position).getStringrechargeTime() + "");
        return convertView;
    }

    static class ViewHolder {
        TextView rechargeUserName;
        TextView rechargePlate;
        TextView rechargeMoney;
        TextView rechargeTime;
    }
}
