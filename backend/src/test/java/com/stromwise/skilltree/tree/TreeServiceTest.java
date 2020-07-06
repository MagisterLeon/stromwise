package com.stromwise.skilltree.tree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TreeServiceTest {

    @Mock
    private TreeRepository treeRepository;
    @Spy
    private TreeNodeSetupVisitor treeNodeSetupVisitor;
    @InjectMocks
    private TreeService uut;

    @Test
    void shouldCountNodesLeaves() {
        // given
        when(treeRepository.findById(anyLong())).thenReturn(
                TreeNode.builder()
                        .child(TreeNode.builder()
                                .child(TreeNode.builder().build())
                                .child(TreeNode.builder().build())
                                .build())
                        .child(TreeNode.builder()
                                .child(TreeNode.builder().build())
                                .build())
                        .child(TreeNode.builder().build())
                        .build());

        // when
        TreeNode tree = uut.tree();

        // then
        assertThat(tree.getValue()).isEqualTo(7);
        assertAll("First children values",
                () -> assertThat(tree.getChildren().get(0).getValue()).isEqualTo(3),
                () -> assertThat(tree.getChildren().get(0).getChildren().get(0).getValue()).isEqualTo(1),
                () -> assertThat(tree.getChildren().get(0).getChildren().get(1).getValue()).isEqualTo(1));
        assertAll("Second children values",
                () -> assertThat(tree.getChildren().get(1).getValue()).isEqualTo(2),
                () -> assertThat(tree.getChildren().get(1).getChildren().get(0).getValue()).isEqualTo(1));
        assertThat(tree.getChildren().get(2).getValue()).isEqualTo(1);
    }
}