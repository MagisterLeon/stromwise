package com.stromwise.skilltree.mock;

import com.google.common.io.CharStreams;
import com.stromwise.skilltree.tree.TreeNode;
import com.stromwise.skilltree.tree.TreeParser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@Component
@RequiredArgsConstructor
class MockRepository {

    private final static String MOCK_TREE_FILE_NAME = "mockTree.json";

    private final TreeParser treeParser;
    private final ResourceLoader resourceLoader;

    public TreeNode findById(Long id) {
        TreeNode tree = treeParser.parse(treeJson());
        return tree.findById(id)
                .orElse(TreeNode.builder().build());
    }

    private String treeJson() {
        try {
            Resource treeResource = resourceLoader.getResource("classpath:" + MOCK_TREE_FILE_NAME);
            try (final Reader reader = new InputStreamReader(treeResource.getInputStream())) {
                return CharStreams.toString(reader);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
