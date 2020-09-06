package com.stromwise.skilltree.tree;

import com.stromwise.skilltree.course.Course;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tree_node")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    @Id
    private Long id;

    @NotNull(message = "Node name must not be null")
    private String name;

    private String description;

    @Transient
    private long value;

    @Singular
    @OneToMany
    private List<Course> courses = new ArrayList<>();

    @Singular
    @OneToMany
    private List<TreeNode> children = new ArrayList<>();

    public void accept(Visitor visitor) {
        visitor.visit(this);
        children.forEach(c -> c.accept(visitor));
    }
}