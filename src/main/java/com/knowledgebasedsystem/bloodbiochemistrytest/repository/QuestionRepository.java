package com.knowledgebasedsystem.bloodbiochemistrytest.repository;

import com.knowledgebasedsystem.bloodbiochemistrytest.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {

    @Query("SELECT new Question(q.id, q.pathologyId, q.content) FROM Question q " +
            "WHERE q.pathologyId LIKE CONCAT('%',:pathologyId,'%')")
    List<Question> findLikePathology(@Param("pathologyId") String pathologyId);
}
