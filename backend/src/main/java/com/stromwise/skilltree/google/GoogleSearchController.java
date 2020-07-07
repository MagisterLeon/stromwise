package com.stromwise.skilltree.google;

import com.google.api.services.customsearch.model.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skill-tree/v1/google")
class GoogleSearchController {

    private final GoogleSearchService googleSearchService;

    @GetMapping("/{query}")
    public Search get(@PathVariable final String query) {
        return googleSearchService.getSearchResult(query);
    }

}
