package com.webspoons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SnippetLikeResponse {

    private String name;
    @JsonProperty("expires_at")
    private String expiresAt;
    private String snippet;
    private String url;
    private Integer likes;


}
