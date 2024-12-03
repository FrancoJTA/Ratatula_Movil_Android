package com.example.ratatula_cliente.Api.GraphQL;

public interface GraphQLCallback<T> {
    void onSuccess(T result);
    void onError(Exception e);
}