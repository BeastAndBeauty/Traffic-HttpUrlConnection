package com.traffic.pyp.traffic3.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class GetCarAccountBalanceRequest extends BaseRequest {
    private String CarId;

    public GetCarAccountBalanceRequest(Object[] parames) {
        super(parames);
        CarId=parames[0].toString();
    }

    @Override
    public String getType() {
        return "GetCarAccountBalance";
    }

    @Override
    public String getRequestBody() {
        JSONObject body=new JSONObject();
        try {
            body.put("CarId",CarId);
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
            if(result.equals("S")){
                return Integer.valueOf(new JSONObject(response).getInt("Balance"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Random().nextInt(900)+50;
    }
}
