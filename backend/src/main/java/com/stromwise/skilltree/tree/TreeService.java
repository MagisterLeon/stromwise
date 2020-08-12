package com.stromwise.skilltree.tree;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class TreeService {

    private final TreeRepository treeRepository;
    private final TreeNodeSetupVisitor treeNodeSetupVisitor;

    public TreeNode getByName(final String name) {
        Optional<TreeNode> root = treeRepository.findByNameIgnoreCase(name);
        root.ifPresent(r -> r.accept(treeNodeSetupVisitor));
        return root.orElse(null);
    }

    public void save(final TreeNode treeNode, final String parentName) {
        treeRepository.save(treeNode);

        Optional<TreeNode> parentOptional = treeRepository.findByNameIgnoreCase(parentName);
        parentOptional.ifPresent(parent -> {
            parent.getChildren().add(treeNode);
            treeRepository.save(parent);
        });
    }
}
