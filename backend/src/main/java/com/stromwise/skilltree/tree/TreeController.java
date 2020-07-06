package com.stromwise.skilltree.tree;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skill-tree/v1/tree")
class TreeController {

    private final TreeService treeService;

    @GetMapping
    public TreeNode tree() {
        return treeService.tree();
    }

}
