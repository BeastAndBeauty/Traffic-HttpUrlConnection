package com.traffic.pyp.traffic3.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class SetTrafficLightConfigRequest extends BaseRequest {
    private String TrafficLightId;
    private String RedTime;
    private String GreenTime;
    private String YellowTime;
    public SetTrafficLightConfigRequest(Object[] parames) {
        super(parames);
        TrafficLightId=parames[0].toString();
        RedTime=parames[1].toString();
        GreenTime=parames[2].toString();
        YellowTime=parames[3].toString();
    }

    @Override
    public String getType() {
        return "SetTrafficLightConfig";
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("TrafficLightId",TrafficLightId);
            body.put("RedTime", RedTime);
            body.put("GreenTime", GreenTime);
            body.put("YellowTime", YellowTime);
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
            String result = new JSONObject(response).getString("RESULT");
            if (result.equals("S"))
                return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
