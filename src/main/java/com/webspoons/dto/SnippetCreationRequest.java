package com.webspoons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SnippetCreationRequest {

    @NotBlank(message = "name required")
    private String name;
    @NotBlank(message = "expires_at required")
    @JsonProperty("expires_in")
    private Integer expiresIn;
    @NotBlank(message = "snippet required")
    private String snippet;


}
