package com.stromwise.skilltree.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stromwise.skilltree.tree.TreeNode;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class TreeParserTest {
    TreeParser uut = new TreeParser(new ObjectMapper());

    @Test
    void shouldParseTreeWithProperValues() throws IOException {
        // given
        // when
        TreeNode result = uut.parse(treeJson());

        // then
        assertThat(result.getName()).isEqualTo("Programming");
        assertThat(result.getChildren())
                .extracting("id", "name")
                .contains(
                        tuple(2L, "Backend"),
                        tuple(5L, "Frontend"));
        assertThat(result.getChildren().get(0).getChildren())
                .extracting("id", "name")
                .contains(
                        tuple(3L, "Java"),
                        tuple(4L, "C++"));
        assertThat(result.getChildren().get(1).getChildren())
                .extracting("id", "name")
                .contains(
                        tuple(6L, "Angular"),
                        tuple(7L, "React"));
    }

    private String treeJson() throws IOException {
        File file = ResourceUtils.getFile("classpath:mockTreeTest.json");
        return new String(Files.readAllBytes(file.toPath()));
    }
}