package com.sailfish.engineering.http.okhttp;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import lombok.Data;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author sailfish
 * @create 2019-11-01-17:43
 */
public class OkHttpDemo {

    public static final MediaType TYPE
            = MediaType.get("application/json; charset=utf-8");
    @Data
    static class User {
        private String name;
        private Integer age;
    }

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        User user = new User();
        user.setAge(11);
        user.setName("sailfish");

        final String body = JSON.toJSONString(user);

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(body, TYPE))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        }

    }
}
