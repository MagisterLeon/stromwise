package com.stromwise.skilltree.tree;

import lombok.*;

@Value
@Builder
public class TreeNodeSaveRequest {
    TreeNode treeNode;
    String parentName;
}
