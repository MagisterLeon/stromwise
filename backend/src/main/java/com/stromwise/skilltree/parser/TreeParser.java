package com.stromwise.skilltree.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stromwise.skilltree.tree.TreeNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreeParser {

    private final ObjectMapper objectMapper;

    public TreeNode parse(String treeJson) {
        try {
            return objectMapper.readValue(treeJson, TreeNode.class);
        } catch (JsonProcessingException e) {
            throw new TreeDeserializationException(treeJson, e);
        }
    }
}
