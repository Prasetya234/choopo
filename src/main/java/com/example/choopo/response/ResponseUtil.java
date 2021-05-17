package com.example.choopo.response;

import com.example.choopo.exception.ResourceNotFoundExceotion;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

public class ResponseUtil {
    public static <T> URI resourceUri(T articleId) throws ResourceNotFoundExceotion {
        Objects.requireNonNull(articleId);
        return Optional.of(articleId)
                .map(article_id -> fromCurrentRequest().path("/{articl_id}")
                        .buildAndExpand(article_id).toUri())
                .orElseThrow(() -> new ResourceNotFoundExceotion("ARTICLE STATUS ID NOT FOUND"));
    }
}
