package com.newsfeed.utils;

import com.newsfeed.model.Category;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomSerializer extends StdSerializer<Category> {

    public CustomSerializer() {
        this(null);
    }

    public CustomSerializer(Class<Category> t) {
        super(t);
    }

    @Override
    public void serialize(
            Category category,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException {
        generator.writeObject(category.getName());
    }


}
