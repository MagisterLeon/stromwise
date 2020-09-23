package com.stromwise.skilltree.google;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/skill-tree/v1/google")
@RequiredArgsConstructor
@RestController
class GoogleSearchController {

    private final GoogleSearchService googleSearchService;

    @ApiOperation(value = "Get courses from google")
    @GetMapping("/{query}")
    public List<GoogleSearchResultModel> get(@PathVariable final String query) {
        return googleSearchService.getSearchResult(query);
    }
}
