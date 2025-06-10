package com.chinmayshivratriwar.cache_comparison.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;

@Data
@Setter
@Getter
@Builder
@JsonDeserialize(builder = InputObject.InputObjectBuilder.class)
// Need to explicitly tell jackson to use builder for deserialization
public class InputObject {
    private String correlationId;
    private String region;
    private String type;
    private String generationDateTime;
    private byte[] payload;

    @JsonPOJOBuilder(withPrefix = "")
    public static class InputObjectBuilder {
    }
}
