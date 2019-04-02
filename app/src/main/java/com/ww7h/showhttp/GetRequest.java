package com.ww7h.showhttp;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   ShowHttp
 * 包名：     com.ww7h.showhttp
 * 创建时间：  2019/4/1 20:50
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public class GetRequest {

    OkHttpClient client = new OkHttpClient();

    void asyncGet(String url, HashMap<String, String> headerMap, RequestCallBack callBack) {
        Request.Builder builder = new Request.Builder().url(url);
        if (headerMap != null) {
            for(Map.Entry<String, String> entry: headerMap.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        Request request = builder.get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@Nullable Call call, @Nullable IOException e) {

            }

            @Override
            public void onResponse(@Nullable Call call, @Nullable Response response) {

            }
        });
    }

    void asyncGet(String url, RequestCallBack callBack) {
        asyncGet(url, null, callBack);
    }


    String syncGet(String url, HashMap<String, String> headerMap) {
        Request.Builder builder = new Request.Builder().url(url);
        if (headerMap != null) {
            for(Map.Entry<String, String> entry: headerMap.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Request request = builder.get().build();
        String result = "";
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            result = response.body().string();
        } catch (IOException ie) {
            Log.e("syncGet", "网络请求失败" + ie.toString());
        } catch (NullPointerException npe) {
            Log.e("syncGet", "未获取到返回结果" + npe.toString());
        }
        return result;
    }

    String syncGet(String url) {
        return syncGet(url, null);
    }

}
