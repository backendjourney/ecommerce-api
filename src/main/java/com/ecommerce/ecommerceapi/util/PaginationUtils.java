package com.ecommerce.ecommerceapi.util;

import com.ecommerce.ecommerceapi.exception.APIException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Set;

public final class PaginationUtils {

    private PaginationUtils() {

    }

    public static Pageable buildPageable(int pageNumber, int pageSize, String sortBy, String sortOrder, Set<String> allowedSortFields) {
        if (!allowedSortFields.contains(sortBy)) {
            throw new APIException("Invalid sort field: " + sortBy);
        }
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return PageRequest.of(pageNumber, pageSize, sortByAndOrder);
    }
}
