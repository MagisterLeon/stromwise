package com.stromwise.skilltree.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> findByPublicId(String publicId);

    @Query(value = "" +
            "SELECT q.id AS id, q.public_id AS public_id, q.question AS question, q.answer AS answer, q.value AS value " +
            "FROM question q " +
            "join question_category qc on q.id = qc.question_id " +
            "join category c on c.id = qc.category_id " +
            "where c.name = ?1 " +
            "order by random() " +
            "LIMIT ?2 ",
            nativeQuery = true)
    List<Question> findRandomByCategoryName(String categoryName, String questionsResultLimit);
    List<QuestionResponseRate> findUserByPublicId(List<String> publicId);
}
