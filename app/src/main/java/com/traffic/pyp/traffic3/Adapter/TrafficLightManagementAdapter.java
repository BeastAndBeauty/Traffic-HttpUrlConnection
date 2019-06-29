package com.traffic.pyp.traffic3.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.traffic.pyp.traffic3.Application.App;
import com.traffic.pyp.traffic3.Bean.TrafficLightManagementBean;
import com.traffic.pyp.traffic3.Dialog.LightSettingsDialog;
import com.traffic.pyp.traffic3.R;

import java.util.ArrayList;
import java.util.List;

public class TrafficLightManagementAdapter extends BaseAdapter {

    private List<TrafficLightManagementBean> data;
    private Context context;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private Boolean[] CompoundButton_isChecked;//记录复选按钮状态

    public TrafficLightManagementAdapter(List<TrafficLightManagementBean> data, Context context, Fragment fragment, FragmentManager fragmentManager) {
        this.data = data;
        this.context = context;
        this.fragment = fragment;
        this.fragmentManager = fragmentManager;
        CompoundButton_isChecked = new Boolean[5];
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_trafficlightmanagement, parent, false);
            holder = new ViewHolder();
            holder.TrafficLightId = convertView.findViewById(R.id.TrafficLightId);
            holder.redLightTime = convertView.findViewById(R.id.redLightTime);
            holder.yellowLightTime = convertView.findViewById(R.id.yellowLightTime);
            holder.greenLightTime = convertView.findViewById(R.id.greenLightTime);
            holder.checkbox = convertView.findViewById(R.id.checkbox);
            holder.btn_setting = convertView.findViewById(R.id.btn_setting);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.TrafficLightId.setText(data.get(position).getTrafficLightId() + "");
        holder.redLightTime.setText(data.get(position).getRedLightTime() + "");
        holder.yellowLightTime.setText(data.get(position).getYellowLightTime() + "");
        holder.greenLightTime.setText(data.get(position).getGreenLightTime() + "");
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CompoundButton_isChecked[position] = isChecked;
                List<Integer> trafficLightID = new ArrayList<>();
                for (int i = 0; i < CompoundButton_isChecked.length; i++)
                    if (CompoundButton_isChecked[i]) {
                        trafficLightID.add(data.get(i).getTrafficLightId());
                    }
                App.setTrafficLightID(trafficLightID);
            }
        });
        holder.btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> list = new ArrayList<>();
                list.add(data.get(position).getTrafficLightId());
                if (fragmentManager != null) {
                    LightSettingsDialog lightSettingsDialog = new LightSettingsDialog(list);
                    lightSettingsDialog.setTargetFragment(fragment, 0);
                    lightSettingsDialog.show(fragmentManager, "LightSettings");
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView TrafficLightId;
        TextView redLightTime;
        TextView yellowLightTime;
        TextView greenLightTime;
        CheckBox checkbox;
        Button btn_setting;
    }
}
