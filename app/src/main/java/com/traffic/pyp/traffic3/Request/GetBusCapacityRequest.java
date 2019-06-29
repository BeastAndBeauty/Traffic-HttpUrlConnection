package com.traffic.pyp.traffic3.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class GetBusCapacityRequest extends BaseRequest {
    private String BusId;
    public GetBusCapacityRequest(Object[] parames) {
        super(parames);
        BusId=parames[0].toString();
    }

    @Override
    public String getType() {
        return "GetBusCapacity";
    }

    @Override
    public String getRequestBody() {
        JSONObject body=new JSONObject();
        try {
            body.put("BusId",BusId);
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
        try {
            String result=new JSONObject(response).getString("RESULT");
            return new JSONObject(response).getInt("BusCapacity");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Random().nextInt(100)+10;
    }
}
