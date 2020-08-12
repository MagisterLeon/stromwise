package com.stromwise.skilltree.tree;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TreeRepository extends MongoRepository<TreeNode, String> {

    Optional<TreeNode> findByNameIgnoreCase(String name);
}
