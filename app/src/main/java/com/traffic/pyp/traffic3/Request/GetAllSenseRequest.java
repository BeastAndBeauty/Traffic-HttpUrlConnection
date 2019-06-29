package com.traffic.pyp.traffic3.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetAllSenseRequest extends BaseRequest {
    public GetAllSenseRequest() {
        super(new Object[]{});
    }


    @Override
    public String getType() {
        return "GetAllSense";
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("UserName",UserName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("hhh","请求体："+body.toString());
        return body.toString();
    }

    @Override
    public Object onResponseParse(String response) {
        Log.i("hhh","返回体："+response);
        List<String> list = new ArrayList<>();
        try {
            String result = new JSONObject(response).getString("RESULT");
            if (result.equals("S")) {
                list.add(new JSONObject(response).getInt("temperature") + "℃");//温度
                list.add(new JSONObject(response).getInt("humidity") + "％");//湿度
                list.add(new JSONObject(response).getInt("pm2.5") + "μg/m³");//pm2.5
                list.add(new JSONObject(response).getInt("LightIntensity") + "Lux");//光照强度
                list.add(new JSONObject(response).getInt("co2") + "ppm");//二氧化碳
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        list = new ArrayList<>();
        list.add(new Random().nextInt(30) + "℃");//随机温度
        list.add((new Random().nextInt(31) + 20) + "％");//随机湿度
        list.add((new Random().nextInt(10) + 1) + "μg/m³");//随机pm2.5
        list.add((new Random().nextInt(1001) + 1000) + "Lux");//随机光照强度
        list.add((new Random().nextInt(2001) + 4000) + "ppm");//随机二氧化碳
        return list;
    }
}
