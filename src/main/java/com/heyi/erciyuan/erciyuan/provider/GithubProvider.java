package com.heyi.erciyuan.erciyuan.provider;

import com.alibaba.fastjson.JSON;
import com.heyi.erciyuan.erciyuan.eto.AccessTokenDTO;
import com.heyi.erciyuan.erciyuan.eto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;



@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
        try (Response response = client.newCall(request).execute()) {
            String accessToken= response.body().string();
//            System.out.println(accessToken);
            String koten = accessToken.split("&")[0].split("=")[1];
            return koten;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getGithubUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();

        try {
        Response response = client.newCall(request).execute();
            String accessToken_user = response.body().string();
            GithubUser user = JSON.parseObject(accessToken_user,GithubUser.class);
            return user;
        } catch (IOException e) {
        }
        return null;
    }
}
