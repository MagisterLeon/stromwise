package com.stromwise.skilltree.google;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoogleSearchService {

    private static final int HTTP_REQUEST_TIMEOUT = 3 * 600000;
    private final GoogleApiProperties googleApiProperties;

    public List<GoogleSearchResultModel> getSearchResult(final String query) {
        Customsearch customsearch = customSearchInit();
        try {
            Customsearch.Cse.List list = customsearch.cse().list(query);
            list.setKey(googleApiProperties.getKey());
            list.setCx(googleApiProperties.getId());
            Search result = list.execute();
            return result.getItems().stream()
                    .map(GoogleSearchService::asGoogleSearchResultModel)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new GoogleSearchException(String.format("Cannot get google result for query %s", query), e);
        }
    }

    private static GoogleSearchResultModel asGoogleSearchResultModel(Result item) {
        return GoogleSearchResultModel.builder()
                .title(item.getTitle())
                .snippet(item.getSnippet())
                .url(item.getFormattedUrl())
                .build();
    }

    private Customsearch customSearchInit() {
        try {
            return new Customsearch(
                    new NetHttpTransport(),
                    new JacksonFactory(),
                    httpRequest -> {
                        httpRequest.setConnectTimeout(HTTP_REQUEST_TIMEOUT);
                        httpRequest.setReadTimeout(HTTP_REQUEST_TIMEOUT);
                    });
        } catch (Exception e) {
            throw new GoogleSearchException("Cannot establish google connection", e);
        }
    }
}