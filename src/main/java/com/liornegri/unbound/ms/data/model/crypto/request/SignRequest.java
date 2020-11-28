package com.liornegri.unbound.ms.data.model.crypto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
public class SignRequest {

    @NotBlank(message = "id cannot be null or empty")
    @JsonProperty(value = "id", required = true)
    String id;

    @NotNull(message = "data cannot be null")
    @NotEmpty(message = "data cannot be empty")
    @JsonProperty(value = "data", required = true)
    byte[] data;
}
