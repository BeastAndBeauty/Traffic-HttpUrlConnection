package com.traffic.pyp.traffic3.Request;


import com.traffic.pyp.traffic3.Callback.OnResponseListener;
import com.traffic.pyp.traffic3.Util.NetUtils;

public abstract class BaseRequest {
    protected String UserName = "admin";

    public BaseRequest(Object[] parames) {
    }

    public abstract String getType();

    public abstract String getRequestBody();

    public abstract Object onResponseParse(String response);

    public void sendRequest(final OnResponseListener onResponseListener) {
        NetUtils.asyncRequest("http://192.168.0.4:8080/transportservice2/action/" + getType() + ".do", getRequestBody(), new OnResponseListener() {
            @Override
            public void onResponse(Object result) {
                onResponseListener.onResponse(onResponseParse(result.toString()));
            }
        });
    }
}
