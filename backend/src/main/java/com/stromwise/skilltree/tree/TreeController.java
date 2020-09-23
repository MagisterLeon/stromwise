package com.stromwise.skilltree.tree;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/skill-tree/v1/nodes")
@RequiredArgsConstructor
@RestController
class TreeController {

    private final TreeService treeService;

    @ApiOperation(value = "Get all courses by name")
    @GetMapping("/names")
    public List<String> getAllNodeNames() {
        return treeService.getAllNodeNames();
    }

    @ApiOperation(value = "Get specific course by name")
    @GetMapping("{name}")
    public TreeNode getByName(@PathVariable String name) {
        return treeService.getByName(name);
    }

    @ApiOperation(value = "Add course")
    @PostMapping
    public void save(@RequestBody TreeNodeSaveRequest request) {
        treeService.save(request.getTreeNode(), request.getParentName());
    }
}
