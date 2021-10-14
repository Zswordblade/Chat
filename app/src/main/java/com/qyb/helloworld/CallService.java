package com.qyb.helloworld;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CallService {
    @FormUrlEncoded
    @POST("u/helloworld")
    Call<HelloResponse> helloworld(@Field("id") int id);

    @POST("u/Register")
    Call<UsersResponse> Register(@Body Users users);

    @POST("u/Login")
    Call<UsersResponse> Login(@Body LoginData loginData);
}
