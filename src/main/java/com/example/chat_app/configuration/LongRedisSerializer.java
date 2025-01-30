package com.example.chat_app.configuration;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class LongRedisSerializer implements RedisSerializer<Long> {

    @Override
    public byte[] serialize(Long value) throws SerializationException {
        if (value == null) {
            return new byte[0];
        }
        return Long.toString(value).getBytes();
    }

    @Override
    public Long deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return Long.valueOf(new String(bytes));
    }
}