package com.ultimate.ecommerce.repository.server.request.base;

public class GenPostObject<T> {
    T Value;

    public GenPostObject(T value) {
        Value = value;
    }

    public T getValue() {
        return Value;
    }

    public void setValue(T value) {
        Value = value;
    }
}

