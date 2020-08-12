package com.stromwise.skilltree.tree;

import com.stromwise.skilltree.course.Course;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document(value = "nodes")
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    @Id
    private String id;

    @NotNull(message = "Node name must not be null")
    @Indexed(unique = true)
    private String name;

    private String description;
    private long value;

    @Singular
    private List<Course> courses = new ArrayList<>();

    @DBRef
    @Singular
    private List<TreeNode> children = new ArrayList<>();

    public void accept(Visitor visitor) {
        visitor.visit(this);
        children.forEach(c -> c.accept(visitor));
    }
}