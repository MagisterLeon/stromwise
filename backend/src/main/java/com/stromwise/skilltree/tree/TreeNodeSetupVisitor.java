package com.stromwise.skilltree.tree;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TreeNodeSetupVisitor implements Visitor {

    @Override
    public void visit(final TreeNode node) {
        node.setValue(countChildrenRecursive(node) + 1);
    }

    private long countChildrenRecursive(TreeNode treeNode) {
        long count = 0;

        List<TreeNode> children = treeNode.getChildren();
        count += children.size();
        count += children.stream()
                .mapToLong(this::countChildrenRecursive)
                .sum();
        return count;
    }
}
