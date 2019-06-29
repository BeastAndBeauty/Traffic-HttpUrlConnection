package com.traffic.pyp.traffic3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.traffic.pyp.traffic3.R;

/**
 * 作者：paopao on 2019/1/16 12:51
 * <p>
 * 作用: 公交查询 ExpandableListView  Adapter
 */
public class BusInfoExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private String[] groupString={"中医院站","联想大厦站"};
    private String[][] timeChildString;
    private String[][] distanceChildString;
    private String[][] capacityChildString;
    private String[][] busNoChildString={{"1号","2号"},{"1号","2号"}};

    public BusInfoExpandableAdapter(Context context,
                                    String[][] timeChildString,
                                    String[][] distanceChildString, String[][] capacityChildString) {
        this.context = context;
        this.timeChildString = timeChildString;
        this.distanceChildString = distanceChildString;
        this.capacityChildString = capacityChildString;
    }

    @Override
    public int getGroupCount() {
        return groupString.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return timeChildString.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupString[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return timeChildString[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if(convertView==null){
            convertView=LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.bus_info_expandable_list_view_group,parent,false);
            groupViewHolder=new GroupViewHolder();
            groupViewHolder.station=convertView.findViewById(R.id.bus_station);
            groupViewHolder.indicator=convertView.findViewById(R.id.indicator);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder= (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.station.setText(groupString[groupPosition]);
        if(isExpanded){
            groupViewHolder.indicator.setImageResource(R.drawable.bus_info_indicator_botton);
        }else {
            groupViewHolder.indicator.setImageResource(R.drawable.bus_info_indicator_right);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if(convertView==null){
            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_info_expandable_list_view_child,parent,false);
            childViewHolder=new ChildViewHolder();
            childViewHolder.capacity=convertView.findViewById(R.id.capacity);
            childViewHolder.minute=convertView.findViewById(R.id.minute);
            childViewHolder.distance=convertView.findViewById(R.id.distance);
            childViewHolder.car_no=convertView.findViewById(R.id.bus_no);
            convertView.setTag(childViewHolder);

        }else {
            childViewHolder= (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.car_no.setText(busNoChildString[groupPosition][childPosition]);
        childViewHolder.capacity.setText(capacityChildString[groupPosition][childPosition]);
        childViewHolder.minute.setText(timeChildString[groupPosition][childPosition]);
        childViewHolder.distance.setText(distanceChildString[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class GroupViewHolder{
        TextView station;
        ImageView indicator;
    }

    static class  ChildViewHolder{
        TextView capacity;
        TextView car_no;
        TextView minute;
        TextView distance;
    }

}
