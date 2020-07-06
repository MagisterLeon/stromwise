package com.stromwise.skilltree.tree;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TreeService {

    private static final Long ROOT_ID = 1L;

    private final TreeRepository treeRepository;
    private final TreeNodeSetupVisitor treeNodeSetupVisitor;

    public TreeNode tree() {
        TreeNode root = treeRepository.findById(ROOT_ID);
        root.accept(treeNodeSetupVisitor);
        return root;
    }
}
