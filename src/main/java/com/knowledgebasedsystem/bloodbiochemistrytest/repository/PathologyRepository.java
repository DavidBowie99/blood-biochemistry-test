package com.knowledgebasedsystem.bloodbiochemistrytest.repository;

import com.knowledgebasedsystem.bloodbiochemistrytest.entity.Pathology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PathologyRepository extends JpaRepository<Pathology, String> {
}
