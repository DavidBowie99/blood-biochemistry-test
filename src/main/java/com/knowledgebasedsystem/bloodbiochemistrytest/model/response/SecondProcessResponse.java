package com.knowledgebasedsystem.bloodbiochemistrytest.model.response;

import com.knowledgebasedsystem.bloodbiochemistrytest.entity.Advice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecondProcessResponse {
    List<Advice> adviceList;
}
