package com.traffic.pyp.traffic3.Request;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetBusStationInfoRequest extends BaseRequest{
    private String BusStationId;
    public GetBusStationInfoRequest(Object[] parames) {
        super(parames);
        BusStationId=parames[0].toString();
    }

    @Override
    public String getType() {
        return "GetBusStationInfo";
    }

    @Override
    public String getRequestBody() {
        JSONObject body=new JSONObject();
        try {
            body.put("BusStationId",BusStationId);
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
        List<String[]> list = new ArrayList<>();
        String[] Distance = new String[2];
        Distance[0] = String.valueOf(new Random().nextInt(10000)+1);
        Distance[1] = String.valueOf(new Random().nextInt(10000)+1);
        try {
            String result=new JSONObject(response).getString("RESULT");
            if(result.equals("S")){
                result = new JSONObject(response).getString("ROWS_DETAIL");
                JSONArray jsonArray = new JSONArray(result);
                Distance[0] = jsonArray.getJSONObject(0).getString("Distance");
                Distance[1] = jsonArray.getJSONObject(1).getString("Distance");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        list.add(new String[]{(int) (((Double.parseDouble(Distance[0]) * 0.00001) / 20) * 60) + "分钟到达",
                "距离站台" + (int) (Double.parseDouble(Distance[0]) * 0.01) + "米"});
        list.add(new String[]{(int) (((Double.parseDouble(Distance[1]) * 0.00001) / 20) * 60) + "分钟到达",
                "距离站台" + (int) (Double.parseDouble(Distance[1]) * 0.01) + "米"});
        return list;
    }
}
