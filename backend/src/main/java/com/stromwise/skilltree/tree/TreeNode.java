package com.stromwise.skilltree.tree;

import com.stromwise.skilltree.course.Course;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    private Long id;
    private String name;
    private String description;
    private long value;

    @Singular
    private List<Course> courses;

    @Singular
    private List<TreeNode> children = new ArrayList<>();

    public Optional<TreeNode> findById(Long id) {
        if (id.equals(this.id)) {
            return Optional.of(this);
        }
        children.forEach(c -> c.findById(id));
        return Optional.empty();
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        children.forEach(c -> c.accept(visitor));
    }
}
