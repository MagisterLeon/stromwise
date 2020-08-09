package com.stromwise.skilltree.tree;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class TreeService {

    private static final Long ROOT_ID = 1L;

    private final TreeRepository treeRepository;
    private final TreeNodeSetupVisitor treeNodeSetupVisitor;

    public TreeNode tree() {
        Optional<TreeNode> root = treeRepository.findById(ROOT_ID);
        root.ifPresent(r -> r.accept(treeNodeSetupVisitor));
        return root.orElse(null);
    }
}
