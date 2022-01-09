package com.knowledgebasedsystem.bloodbiochemistrytest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "PATHOLOGY_ID")
    private String pathologyId;

    @Column(name = "CONTENT_QUESTION")
    private String content;
}
