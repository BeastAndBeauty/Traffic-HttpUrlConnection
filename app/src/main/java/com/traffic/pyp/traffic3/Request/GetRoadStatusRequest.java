package com.traffic.pyp.traffic3.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class GetRoadStatusRequest extends  BaseRequest {
    private String RoadId;
    public GetRoadStatusRequest(Object[] parames) {
        super(parames);
        RoadId=parames[0].toString();
    }

    @Override
    public String getType() {
        return "GetRoadStatus";
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("RoadId", RoadId);
            body.put("UserName", UserName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("hhh","请求体："+body.toString());
        return body.toString();
    }

    @Override
    public Object onResponseParse(String response) {
        Log.i("hhh","返回体："+response);
        String[] str_color = new String[]{"#6ab82e", "#ece93a", "#f49b25", "#e33532", "#b01e23"};
        try {
            String result = new JSONObject(response).getString("RESULT");
            if (result.equals("S"))
                return str_color[new JSONObject(response).getInt("Status")];
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str_color[new Random().nextInt(5) ];
    }
}
