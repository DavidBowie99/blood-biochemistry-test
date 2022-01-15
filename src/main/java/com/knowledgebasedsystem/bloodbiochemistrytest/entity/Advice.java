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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "advice")
public class Advice {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CONTENT")
    private String content;
}
