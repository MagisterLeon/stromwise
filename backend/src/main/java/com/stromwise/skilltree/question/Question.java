package com.stromwise.skilltree.question;

import com.stromwise.skilltree.category.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "categories")
@ToString(exclude = "categories")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publicId;
    private String question;
    private String answer;
    private long value;
    private int know;
    private int notSure;
    private int notKnow;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "question_category",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Question(String question) {
        this.publicId = UUID.randomUUID().toString();
        this.question = question;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getQuestions().add(this);
    }

    public void addCategories(List<Category> categories) {
        this.categories.addAll(categories);
        categories.forEach(category -> category.getQuestions().add(this));
    }
}
