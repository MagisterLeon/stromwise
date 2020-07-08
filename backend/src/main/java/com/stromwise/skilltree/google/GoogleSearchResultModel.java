package com.stromwise.skilltree.google;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GoogleSearchResultModel {
    String title;
    String snippet;
    String url;
}
