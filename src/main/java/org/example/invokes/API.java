package org.example.invokes;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class API {
    public static Response invokeGet(
            Map<String, Object> headers,
            Map<String, Object> pathParams,
            Map<String, Object> queryParams,
            String url
    ) {
        return RestAssured.given()
                .headers(headers)
                .pathParams(pathParams)
                .queryParams(queryParams)
                .log().all()
                .get(url);
    }

    public static Response invokePost(
            Map<String, Object> headers,
            Map<String, Object> pathParams,
            Map<String, Object> queryParams,
            String body,
            String url

    ) {
        return RestAssured.given()
                .headers(headers)
                .pathParams(pathParams)
                .queryParams(queryParams)
                .body(body)
                .log().all()
                .post(url);
    }

    public static Response invokePut(
            Map<String, Object> headers,
            Map<String, Object> pathParams,
            Map<String, Object> queryParams,
            String body,
            String url
    ) {
        return RestAssured.given()
                .headers(headers)
                .pathParams(pathParams)
                .queryParams(queryParams)
                .body(body)
                .log().all()
                .put(url);
    }

    public static Response invokeDelete(
            Map<String, Object> headers,
            Map<String, Object> pathParams,
            Map<String, Object> queryParams,
            String url
    ) {
        return RestAssured.given()
                .headers(headers)
                .pathParams(pathParams)
                .queryParams(queryParams)
                .log().all()
                .delete(url);
    }
}