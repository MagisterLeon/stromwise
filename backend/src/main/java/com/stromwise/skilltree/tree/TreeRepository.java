package com.stromwise.skilltree.tree;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TreeRepository extends JpaRepository<TreeNode, Long> {

    Optional<TreeNode> findByNameIgnoreCase(String name);
}
