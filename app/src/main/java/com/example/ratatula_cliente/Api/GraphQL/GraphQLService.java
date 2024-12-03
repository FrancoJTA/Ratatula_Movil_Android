package com.example.ratatula_cliente.Api.GraphQL;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GraphQLService {
    @POST(".")
    Call<ResponseBody> executeQuery(@Body RequestBody body);
}