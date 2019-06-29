package com.traffic.pyp.traffic3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.traffic.pyp.traffic3.Bean.BusInfoDialogListViewBean;
import com.traffic.pyp.traffic3.R;

import java.util.List;

/**
 * 作者：paopao on 2019/1/16 15:58
 * <p>
 * 作用:  公交查询 对话框 listVIew 射配器
 */
public class BusInfoCapacityDialogAdapter  extends BaseAdapter {

    private Context context;
    private List<BusInfoDialogListViewBean> list;
    private LayoutInflater inflater;

    public BusInfoCapacityDialogAdapter(Context context, List<BusInfoDialogListViewBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.bus_info_dialog_list_view, parent, false);
            viewHolder.dialog_no =  convertView.findViewById(R.id.dialog_no);
            viewHolder.dialog_busNo =convertView.findViewById(R.id.dialog_busNo);
            viewHolder.dialog_capacity =  convertView.findViewById(R.id.dialog_capacity);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BusInfoDialogListViewBean bean = list.get(position);
        viewHolder.dialog_no.setText(String.valueOf(bean.getNo()));
        viewHolder.dialog_busNo.setText(String.valueOf(bean.getBusNo()));
        viewHolder.dialog_capacity.setText(String.valueOf(bean.getCapacity()));

        return convertView;

    }

    static class ViewHolder {
        TextView dialog_no;
        TextView dialog_busNo;
        TextView dialog_capacity;

    }
}
