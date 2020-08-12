package com.stromwise.skilltree.tree;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skill-tree/v1/nodes")
class TreeController {

    private final TreeService treeService;

    @GetMapping("{name}")
    public TreeNode getByName(@PathVariable String name) {
        return treeService.getByName(name);
    }

    @PostMapping
    public void save(@RequestBody TreeNodeSaveRequest request) {
        treeService.save(request.getTreeNode(), request.getParentName());
    }
}
