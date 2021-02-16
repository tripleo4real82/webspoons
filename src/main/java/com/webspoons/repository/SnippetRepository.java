package com.webspoons.repository;

import com.webspoons.model.SnippetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnippetRepository  extends JpaRepository<SnippetEntity, Long> {

public SnippetEntity findSnippetEntityByName(String name) ;

}
