package com.ecole221.planification.web.dto;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class RestResponseDto {

    // Réponse simple (sans pagination)
    public static Map<Object, Object> response(Object results, HttpStatus status) {
        Map<Object, Object> response = new HashMap<>();
        response.put("status", status.value());
        response.put("results", results);
        return response;
    }

    // Réponse paginée
    public static <T> Map<Object, Object> paginateResponse(Page<T> page, HttpStatus status, String type) {
        Map<Object, Object> response = new HashMap<>();
        response.put("results", page.getContent());
        response.put("pages", new int[page.getTotalPages()]);
        response.put("currentPage", page.getNumber());
        response.put("previousPage", page.getNumber() > 0 ? page.getNumber() - 1 : page.getNumber());
        response.put("nextPage", page.getNumber() < page.getTotalPages() - 1 ? page.getNumber() + 1 : page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        response.put("pageSize", page.getSize());
        response.put("first", page.isFirst());
        response.put("last", page.isLast());
        response.put("status", status.value());
        response.put("type", type);
        return response;
    }
}