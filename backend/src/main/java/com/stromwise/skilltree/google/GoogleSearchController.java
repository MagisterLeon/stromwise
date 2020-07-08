package com.stromwise.skilltree.google;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skill-tree/v1/google")
class GoogleSearchController {

    private final GoogleSearchService googleSearchService;

    @GetMapping("/{query}")
    public List<GoogleSearchResultModel> get(@PathVariable final String query) {
        return googleSearchService.getSearchResult(query);
    }
}
