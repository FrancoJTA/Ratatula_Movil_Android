package com.example.ratatula_cliente.Api.GraphQL;

import android.util.Log;

import com.example.ratatula_cliente.Session.AppData;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;

public class OkHttpGraphQLClient {
    private static final String BASE_URL = "http://34.16.100.239:4000/graphql/";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final OkHttpClient client;
    private final Gson gson;

    public OkHttpGraphQLClient() {
        client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();
        gson = new Gson();
    }

    public <T> void sendRequest(String query, String variables, Type responseType, GraphQLCallback<T> callback) {
        String payload = "{"
                + "\"query\": \"" + query.replace("\"", "\\\"") + "\","
                + "\"variables\": " + (variables != null ? variables : "null")
                + "}";

        RequestBody body = RequestBody.create(payload, JSON);

        // Crear solicitud HTTP
        Request.Builder requestBuilder = new Request.Builder()
                .url(BASE_URL)
                .post(body)
                .addHeader("Content-Type", "application/json");

        if (AppData.getInstance().getToken() != null && !AppData.getInstance().getToken().isEmpty()) {
            requestBuilder.addHeader("Authorization",  AppData.getInstance().getToken());
        }
        Request request = requestBuilder.build();

        // Ejecutar la solicitud
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("GraphQLClient", "Error: " + e.getMessage());
                if (callback != null) {
                    callback.onError(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();

                    Log.d("GraphQLResponse", "Respuesta del servidor: " + responseBody);

                    try {
                        // Usar Gson para deserializar
                        T result = gson.fromJson(responseBody, responseType);
                        if (callback != null) {
                            callback.onSuccess(result);
                        }
                    } catch (JsonSyntaxException e) {
                        Log.e("GraphQLClient", "Error al deserializar: " + e.getMessage());

                        // También registrar la respuesta original para depuración
                        Log.e("GraphQLClient", "Respuesta sin procesar: " + responseBody);

                        if (callback != null) {
                            callback.onError(e);
                        }
                    }
                } else {
                    Log.e("GraphQLClient", "Error HTTP: " + response.code());

                    // Registrar el cuerpo del error si está disponible
                    if (response.body() != null) {
                        Log.e("GraphQLClient", "Cuerpo del error: " + response.body().string());
                    }

                    if (callback != null) {
                        callback.onError(new Exception("Error HTTP: " + response.code()));
                    }
                }
            }
        });
    }

}