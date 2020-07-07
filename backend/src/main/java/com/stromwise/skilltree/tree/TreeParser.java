package com.stromwise.skilltree.tree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stromwise.skilltree.parser.Parser;
import com.stromwise.skilltree.parser.ParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreeParser implements Parser<TreeNode> {

    private final ObjectMapper objectMapper;

    public TreeNode parse(String treeJson) {
        try {
            return objectMapper.readValue(treeJson, TreeNode.class);
        } catch (JsonProcessingException e) {
            throw new ParseException(treeJson, e);
        }
    }
}
