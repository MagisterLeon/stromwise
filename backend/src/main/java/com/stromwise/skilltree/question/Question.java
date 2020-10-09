package com.stromwise.skilltree.question;

import com.stromwise.skilltree.category.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude="categories")
@ToString(exclude = "categories")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publicId;
    private String question;
    private String answer;
    private long value;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "question_category",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Question(String question, String answer) {
        this.publicId = UUID.randomUUID().toString();
        this.question = question;
        this.answer = answer;
    }

    public void addCategories(List<Category> categories) {
        this.categories.addAll(categories);
        categories.forEach(category -> category.getQuestions().add(this));
    }
}
