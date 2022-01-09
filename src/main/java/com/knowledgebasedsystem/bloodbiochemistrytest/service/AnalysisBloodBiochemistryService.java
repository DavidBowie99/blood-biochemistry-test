package com.knowledgebasedsystem.bloodbiochemistrytest.service;

import com.knowledgebasedsystem.bloodbiochemistrytest.model.request.FirstProcessRequest;
import com.knowledgebasedsystem.bloodbiochemistrytest.model.request.SecondProcessRequest;
import com.knowledgebasedsystem.bloodbiochemistrytest.model.response.FirstProcessResponse;
import com.knowledgebasedsystem.bloodbiochemistrytest.model.response.SecondProcessResponse;

public interface AnalysisBloodBiochemistryService {

    FirstProcessResponse firstProcess(FirstProcessRequest request);

    SecondProcessResponse secondProcess(SecondProcessRequest request);

    String calculateBMI(Float weight, Float height);
}
