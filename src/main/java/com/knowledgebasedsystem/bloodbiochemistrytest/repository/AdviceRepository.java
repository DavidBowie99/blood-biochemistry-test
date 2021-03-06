package com.knowledgebasedsystem.bloodbiochemistrytest.repository;

import com.knowledgebasedsystem.bloodbiochemistrytest.entity.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviceRepository extends JpaRepository<Advice, String> {
}
