package com.stromwise.skilltree.category;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController("v1/categories")
@RequiredArgsConstructor
class CategoryController {

    private final CategoryService categoryService;

    @ApiOperation(value = "Get all categories names")
    @GetMapping("/names")
    public ResponseEntity<GetCategoriesResponse> getAllNames() {
        Set<String> names = categoryService.getAllNames();
        return new ResponseEntity<>(new GetCategoriesResponse(names), HttpStatus.OK);
    }
}
