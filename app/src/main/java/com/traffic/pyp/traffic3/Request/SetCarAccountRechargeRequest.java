package com.traffic.pyp.traffic3.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class SetCarAccountRechargeRequest extends BaseRequest {
    private String CarId;
    private String Money;

    public SetCarAccountRechargeRequest(Object[] parames) {
        super(parames);
        CarId=parames[0].toString();
        Money=parames[1].toString();
    }

    @Override
    public String getType() {
        return "SetCarAccountRecharge";
    }

    @Override
    public String getRequestBody() {
        JSONObject body=new JSONObject();
        try {
            body.put("CarId",CarId);
            body.put("Money",Money);
            body.put("UserName",UserName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("hhh","请求体："+body.toString());
        return body.toString();
    }

    @Override
    public Object onResponseParse(String response) {
        Log.i("hhh","返回体：:"+response);
        try {
            String result=new JSONObject(response).getString("RESULT");
            if (result.equals("S"))
                return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
