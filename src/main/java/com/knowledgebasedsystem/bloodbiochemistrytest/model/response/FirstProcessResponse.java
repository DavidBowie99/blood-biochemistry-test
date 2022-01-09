package com.knowledgebasedsystem.bloodbiochemistrytest.model.response;

import com.knowledgebasedsystem.bloodbiochemistrytest.entity.Pathology;
import com.knowledgebasedsystem.bloodbiochemistrytest.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FirstProcessResponse {
    private List<Pathology> pathologyList;
    private List<Question> questionList;
}
