package com.stromwise.skilltree.tree;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TreeService {

    private static final Long ROOT_ID = 1L;

    private final TreeRepository treeRepository;

    public TreeNode tree() {
        return treeRepository.findById(ROOT_ID);
    }
}
