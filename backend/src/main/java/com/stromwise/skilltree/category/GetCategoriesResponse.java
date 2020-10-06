package com.stromwise.skilltree.category;

import lombok.Value;

import java.util.Set;

@Value
class GetCategoriesResponse {
    Set<String> categoryNames;
}
