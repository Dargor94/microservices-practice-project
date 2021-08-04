package com.globant.api.handler;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.function.Supplier;

public class JsonBodyHandler<T> implements HttpResponse.BodyHandler<Supplier<T>> {

    private final Class<T> tClass;

    public JsonBodyHandler(Class<T> tClass) {
        this.tClass = tClass;
    }

    public static <W> HttpResponse.BodySubscriber<Supplier<W>> asJson(Class<W> targetType) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();

        return HttpResponse.BodySubscribers.mapping(
                upstream,
                inputStream -> toSupplierOfType(inputStream, targetType));
    }

    public static <W> Supplier<W> toSupplierOfType(InputStream inputStream, Class<W> targetType) {
        return () -> {
            try (InputStream stream = inputStream) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(stream, targetType);
            } catch (IOException e) {
                return null;
            }
        };
    }

    @Override
    public HttpResponse.BodySubscriber<Supplier<T>> apply(HttpResponse.ResponseInfo responseInfo) {
        return asJson(tClass);
    }
}