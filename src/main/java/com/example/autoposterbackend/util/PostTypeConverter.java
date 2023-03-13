package com.example.autoposterbackend.util;

import com.example.backend.enumerate.PostType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PostTypeConverter implements AttributeConverter<PostType, String> {

    @Override
    public String convertToDatabaseColumn(PostType postType) {
        if (postType == null) {
            return null;
        }
        return postType.getName();
    }

    @Override
    public PostType convertToEntityAttribute(String name) {
        if (name == null) {
            return null;
        }

        return Stream.of(PostType.values())
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
