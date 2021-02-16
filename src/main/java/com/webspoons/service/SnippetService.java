package com.webspoons.service;

import com.webspoons.dto.SnippetCreationRequest;
import com.webspoons.dto.SnippetCreationResponse;
import com.webspoons.dto.SnippetLikeResponse;
import org.springframework.http.ResponseEntity;

public interface SnippetService {
    public ResponseEntity<SnippetCreationResponse> addSnippet(SnippetCreationRequest request, String baseUrl);

    public ResponseEntity<SnippetCreationResponse> getSnippetByName(String name, String baseUrl);

    public ResponseEntity<SnippetLikeResponse> likeSnippetByName(String name, String baseUrl);


}
