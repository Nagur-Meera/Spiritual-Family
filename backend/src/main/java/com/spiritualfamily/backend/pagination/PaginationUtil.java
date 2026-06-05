package com.spiritualfamily.backend.pagination;

import org.springframework.data.domain.Page;

public class PaginationUtil {

    public static <T> PageResponse<T> createPageResponse(
            Page<T> page
    ) {

        return PageResponse.<T>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }
}