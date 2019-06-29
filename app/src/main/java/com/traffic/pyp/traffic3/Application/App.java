package com.traffic.pyp.traffic3.Application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;

import java.util.List;

public class App extends Application {
    private static SharedPreferences sP;
    private static int Alerting;//账户余额告警值

    private static List<Integer> rechargeCarId;//要充值的小车id
    private static List<String> rechargePlate;//要充值的小车车牌号
    private static List<Integer> trafficLightID;//要设置的红绿灯

    @Override
    public void onCreate() {
        super.onCreate();
        sP = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static void showAlertDialog(Context context, String title, String msg,
                                       DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton("确定", listener)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public static int getAlerting() {
        return sP.getInt("Alerting", 1);
    }

    @SuppressLint("ApplySharedPref")
    public static void setAlerting(int alerting) {
        sP.edit().putInt("Alerting", alerting).commit();
    }

    public static List<Integer> getRechargeCarId() {
        return rechargeCarId;
    }

    public static void setRechargeCarId(List<Integer> rechargeCarId) {
        App.rechargeCarId = rechargeCarId;
    }

    public static List<String> getRechargePlate() {
        return rechargePlate;
    }

    public static void setRechargePlate(List<String> rechargePlate) {
        App.rechargePlate = rechargePlate;
    }

    public static List<Integer> getTrafficLightID() {
        return trafficLightID;
    }

    public static void setTrafficLightID(List<Integer> trafficLightID) {
        App.trafficLightID = trafficLightID;
    }
}