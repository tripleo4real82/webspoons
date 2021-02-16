package com.webspoons.controller;

import com.webspoons.dto.SnippetCreationRequest;
import com.webspoons.dto.SnippetCreationResponse;
import com.webspoons.dto.SnippetLikeResponse;
import com.webspoons.service.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/snippets")
public class SnippetController {

    @Autowired
    private SnippetService snippetService;

    @Async
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Future<ResponseEntity<SnippetCreationResponse>> addSnippet(HttpServletRequest request, @RequestBody SnippetCreationRequest snippetCreationRequest) {

        return new AsyncResult<>(snippetService.addSnippet(snippetCreationRequest, request.getRequestURL().toString()));
    }

    @Async
    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Future<ResponseEntity<SnippetCreationResponse>> getSnippetByName(HttpServletRequest request, @PathVariable(name = "name") String name) {
        return new AsyncResult<>(snippetService.getSnippetByName(name, request.getRequestURL().toString()));
    }

    @Async
    @GetMapping(value = "/{name}/like", produces = MediaType.APPLICATION_JSON_VALUE)
    public Future<ResponseEntity<SnippetLikeResponse>> likeSnippetByName(HttpServletRequest request, @PathVariable(name = "name") String name) {
        return new AsyncResult<>(snippetService.likeSnippetByName(name, request.getRequestURL().toString()));
    }

}
