package com.stromwise.skilltree.tree;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skill-tree/v1/nodes")
class TreeController {

    private final TreeService treeService;

  //  @ApiOperation(value = "Get all courses by name")
  //  @ApiResponse(code = 200, message = "Successful get all nodes by name")
    @GetMapping("/names")
    public List<String> getAllNodeNames() {
        return treeService.getAllNodeNames();
    }

 //   @ApiResponse(code = 200, message = "Successful get single node by name")
 //   @ApiOperation(value = "Get specific course by name")
    @GetMapping("{name}")
    public TreeNode getByName(@PathVariable String name) {
        return treeService.getByName(name);
    }

//    @ApiResponse(code = 200, message = "Successful add course")
 //   @ApiOperation(value = "Add course")
    @PostMapping
    public void save(@RequestBody TreeNodeSaveRequest request) {
        treeService.save(request.getTreeNode(), request.getParentName());
    }
}
