package com.stromwise.skilltree.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/names")
    public ResponseEntity<GetCategoriesResponse> getAllNames() {
        Set<String> names = categoryService.getAllNames();
        return new ResponseEntity<>(new GetCategoriesResponse(names), HttpStatus.OK);
    }
}
