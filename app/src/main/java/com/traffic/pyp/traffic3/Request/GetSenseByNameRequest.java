package com.traffic.pyp.traffic3.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class GetSenseByNameRequest extends  BaseRequest {
    private String SenseName;
    public GetSenseByNameRequest(Object[] parames) {
        super(parames);
        SenseName=parames[0].toString();
    }

    @Override
    public String getType() {
        return "GetSenseByName";
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("SenseName",SenseName);
            body.put("UserName", UserName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("hhh",body.toString());
        return body.toString();
    }

    @Override
    public Object onResponseParse(String response) {
        Log.i("hhh","返回体："+response);
        try {
            String result = new JSONObject(response).getString("RESULT");
            if (result.equals("S"))
                return new JSONObject(response).getInt(SenseName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        switch (SenseName) {
            case "temperature":
                return new Random().nextInt(30);
            case "humidity":
                return new Random().nextInt(31) + 20;
            case "co2":
                return new Random().nextInt(2001) + 4000;
            case "LightIntensity":
                return new Random().nextInt(1001) + 1000;
            case "pm2.5":
                return new Random().nextInt(10) + 1;
            default:
                return "参数错误";
        }
    }
}
