package com.traffic.pyp.traffic3.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GetTrafficLightConfigActionRequest extends BaseRequest {
    private String TrafficLightId;
    public GetTrafficLightConfigActionRequest(Object[] parames) {
        super(parames);
        TrafficLightId=parames[0].toString();
    }

    @Override
    public String getType() {
        return "GetTrafficLightConfigAction";
    }

    @Override
    public String getRequestBody() {
        JSONObject body=new JSONObject();
        try {
            body.put("TrafficLightId",TrafficLightId);
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
        Map<String, Integer> map = new HashMap<>();
        map.put("TrafficLightId", Integer.valueOf(TrafficLightId));
        try {
            String result = new JSONObject(response).getString("RESULT");
            if (result.equals("S")){
                map.put("RedTime", Integer.parseInt(new JSONObject(response).getString("RedTime")));
                map.put("GreenTime", Integer.parseInt(new JSONObject(response).getString("GreenTime")));
                map.put("YellowTime", Integer.parseInt(new JSONObject(response).getString("YellowTime")));
                return map;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        map.put("RedTime", new Random().nextInt(40)+1);
        map.put("GreenTime", new Random().nextInt(40)+1);
        map.put("YellowTime", new Random().nextInt(8)+1);
        return map;
    }
}
