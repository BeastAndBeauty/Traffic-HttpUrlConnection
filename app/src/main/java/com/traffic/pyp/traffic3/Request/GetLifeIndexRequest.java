package com.traffic.pyp.traffic3.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetLifeIndexRequest extends BaseRequest {
    public GetLifeIndexRequest() {
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
        try {
            String result = new JSONObject(response).getString("RESULT");
            if (result.equals("S")) {
                return LifeIndex(new JSONObject(response).getInt("temperature"),
                        new JSONObject(response).getInt("pm2.5"),
                        new JSONObject(response).getInt("LightIntensity"),
                        new JSONObject(response).getInt("co2"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return LifeIndex(new Random().nextInt(30),
                new Random().nextInt(10) + 1,
                new Random().nextInt(1001) + 1000,
                new Random().nextInt(2001) + 4000);
    }

    private List<List<String>> LifeIndex(int temperature, int pm25, int LightIntensity, int co2) {
        List<List<String>> lists = new ArrayList<>();
        String[] UVI_array = {"辐射较弱，涂擦SPF12~15、PA+护肤品", "涂擦 SPF 大于 15、PA+防晒护肤品", "尽量减少外出，需要涂抹高倍数防晒霜"};
        String[] cold_array = {"温度低，风较大，较易发生感冒，注意防护", "无明显降温，感冒机率较低"};
        String[] dress_array = {"建议穿长袖衬衫、单裤等服装", "建议穿短袖衬衫、单裤等服装", "适合穿 T 恤、短薄外套等夏季服装"};
        String[] sports_array = {"气候适宜，推荐您进行户外运动", "易感人群应适当减少室外活动", "空气氧气含量低，请在室内进行休闲运动"};
        String[] air_array = {"空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气", "易感人群应适当减少室外活动", "空气质量差，不适合户外活动"};
        //感冒指数
        List<String> coldList = new ArrayList<>();
        if (temperature < 8) {
            coldList.add("较易发(" + temperature + ")");
            coldList.add(cold_array[0]);
        } else {
            coldList.add("少发(" + temperature + ")");
            coldList.add(cold_array[1]);
        }
        //穿衣指数
        List<String> dressList = new ArrayList<>();
        if (temperature < 12) {
            dressList.add("冷(" + temperature + ")");
            dressList.add(dress_array[0]);
        } else if (temperature >= 12 && temperature <= 21) {
            dressList.add("舒适(" + temperature + ")");
            dressList.add(dress_array[1]);
        } else {
            dressList.add("热(" + temperature + ")");
            dressList.add(dress_array[2]);
        }
        //空气污染扩散指数
        List<String> airList = new ArrayList<>();
        if (pm25 > 0 && pm25 < 30) {
            airList.add("优(" + pm25 + ")");
            airList.add(air_array[0]);
        } else if (pm25 >= 30 && pm25 <= 100) {
            airList.add("良(" + pm25 + ")");
            airList.add(air_array[1]);
        } else {
            airList.add("污染(" + pm25 + ")");
            airList.add(air_array[2]);
        }
        //紫外线指数
        List<String> UVIList = new ArrayList<>();
        if (LightIntensity > 0 && LightIntensity < 1000) {
            UVIList.add("弱(" + LightIntensity + ")");
            UVIList.add(UVI_array[0]);
        } else if (LightIntensity >= 1000 && LightIntensity <= 3000) {
            UVIList.add("中等(" + LightIntensity + ")");
            UVIList.add(UVI_array[1]);
        } else {
            UVIList.add("强(" + LightIntensity + ")");
            UVIList.add(UVI_array[2]);
        }
        //运动指数
        List<String> sportsList = new ArrayList<>();
        if (co2 > 0 && co2 < 3000) {
            sportsList.add("弱(" + co2 + ")");
            sportsList.add(sports_array[0]);
        } else if (co2 >= 3000 && co2 <= 6000) {
            sportsList.add("中等(" + co2 + ")");
            sportsList.add(sports_array[1]);
        } else {
            sportsList.add("强(" + co2 + ")");
            sportsList.add(sports_array[2]);
        }
        lists.add(UVIList);
        lists.add(coldList);
        lists.add(dressList);
        lists.add(sportsList);
        lists.add(airList);
        return lists;
    }

}

