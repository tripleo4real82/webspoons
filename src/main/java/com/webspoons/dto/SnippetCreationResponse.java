package com.webspoons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SnippetCreationResponse {

    private String name;
    @JsonProperty("expires_in")
    private String expiresAt;
    private String snippet;
    private String url;



}
