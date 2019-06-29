package com.traffic.pyp.traffic3.Bean;

/**
 * 作者：paopao on 2019/1/16 15:57
 * <p>
 * 作用: 公交车查询模块  详情对话框 listView  bean
 */
public class BusInfoDialogListViewBean {

    private int no;
    private int busNo;
    private int capacity;

    public BusInfoDialogListViewBean(int no, int busNo, int capacity) {
        this.no = no;
        this.busNo = busNo;
        this.capacity = capacity;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getBusNo() {
        return busNo;
    }

    public void setBusNo(int busNo) {
        this.busNo = busNo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
