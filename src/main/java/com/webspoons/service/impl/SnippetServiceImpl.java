package com.webspoons.service.impl;

import com.webspoons.dto.SnippetCreationRequest;
import com.webspoons.dto.SnippetCreationResponse;
import com.webspoons.dto.SnippetLikeResponse;
import com.webspoons.model.SnippetEntity;
import com.webspoons.repository.SnippetRepository;
import com.webspoons.service.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class SnippetServiceImpl implements SnippetService {
    @Autowired
    private SnippetRepository snippetRepository;
    private Integer expiryExtension = 30;

    @Override
    public ResponseEntity<SnippetCreationResponse> addSnippet(SnippetCreationRequest request, String baseUrl) {

        try {

            SnippetCreationResponse snippetCreationResponse = new SnippetCreationResponse();
            SnippetEntity snippetEntity = snippetRepository.findSnippetEntityByName(request.getName());

            if (Objects.nonNull(snippetEntity)) {
                return new ResponseEntity<>(
                        snippetCreationResponse, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);

            }
             snippetEntity = new SnippetEntity();
            snippetEntity.setName(request.getName());
            snippetEntity.setSnippet(request.getSnippet());
            LocalDateTime dateTime = LocalDateTime.now();
            dateTime = dateTime.plusSeconds(request.getExpiresIn());
            snippetEntity.setExpiresAt(dateTime);
            snippetEntity.setCreatedAt(LocalDateTime.now());
            snippetEntity.setLikes(0);
            snippetRepository.save(snippetEntity);
            snippetCreationResponse.setExpiresAt(dateTime.toString());
            snippetCreationResponse.setName(snippetEntity.getName());
            snippetCreationResponse.setSnippet(snippetEntity.getSnippet());
            String url = baseUrl + "/" + request.getName();
            snippetCreationResponse.setUrl(url);
            return new ResponseEntity<>(
                    snippetCreationResponse, new HttpHeaders(), HttpStatus.CREATED);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(
                    null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @Override
    public ResponseEntity<SnippetCreationResponse> getSnippetByName(String name, String baseUrl) {
        try {
            SnippetCreationResponse snippetCreationResponse = new SnippetCreationResponse();
            SnippetEntity snippetEntity = snippetRepository.findSnippetEntityByName(name);

            if (Objects.isNull(snippetEntity)) {
                return new ResponseEntity<>(
                        snippetCreationResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);

            }
            if (snippetEntity.getExpiresAt().isBefore(LocalDateTime.now())) {
                return new ResponseEntity<>(
                        snippetCreationResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);

            }
            LocalDateTime dateTime = snippetEntity.getExpiresAt();
            dateTime.plusSeconds(expiryExtension);
            snippetEntity.setExpiresAt(dateTime);
            snippetRepository.save(snippetEntity);
            snippetCreationResponse.setExpiresAt(snippetEntity.getExpiresAt().toString());
            snippetCreationResponse.setName(snippetEntity.getName());
            snippetCreationResponse.setSnippet(snippetEntity.getSnippet());
            snippetCreationResponse.setSnippet(snippetEntity.getSnippet());
            String url = baseUrl + "/" + snippetEntity.getName();
            snippetCreationResponse.setUrl(url);
            return new ResponseEntity<>(
                    snippetCreationResponse, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(
                    null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<SnippetLikeResponse> likeSnippetByName(String name, String baseUrl) {
        try {
            SnippetLikeResponse snippetLikeResponse = new SnippetLikeResponse();
            SnippetEntity snippetEntity = snippetRepository.findSnippetEntityByName(name);

            if (Objects.isNull(snippetEntity)) {
                return new ResponseEntity<>(
                        snippetLikeResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);

            }
            if (snippetEntity.getExpiresAt().isBefore(LocalDateTime.now())) {
                return new ResponseEntity<>(
                        snippetLikeResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);

            }
            LocalDateTime dateTime = snippetEntity.getExpiresAt();
            dateTime.plusSeconds(expiryExtension);
            snippetEntity.setExpiresAt(dateTime);
            int likes = snippetEntity.getLikes();
            likes = likes + 1;
            snippetEntity.setLikes(likes);
            snippetRepository.save(snippetEntity);
            snippetLikeResponse.setExpiresAt(snippetEntity.getExpiresAt().toString());
            snippetLikeResponse.setName(snippetEntity.getName());
            snippetLikeResponse.setSnippet(snippetEntity.getSnippet());
            snippetLikeResponse.setSnippet(snippetEntity.getSnippet());
            snippetLikeResponse.setLikes(likes);
            String url = baseUrl + "/" + snippetEntity.getName();
            snippetLikeResponse.setUrl(url);
            return new ResponseEntity<>(
                    snippetLikeResponse, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(
                    null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
