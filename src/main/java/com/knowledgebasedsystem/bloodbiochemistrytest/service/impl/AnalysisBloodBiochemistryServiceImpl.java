package com.knowledgebasedsystem.bloodbiochemistrytest.service.impl;

import com.knowledgebasedsystem.bloodbiochemistrytest.constant.BMI;
import com.knowledgebasedsystem.bloodbiochemistrytest.constant.BloodBiochemistryLimit;
import com.knowledgebasedsystem.bloodbiochemistrytest.entity.Advice;
import com.knowledgebasedsystem.bloodbiochemistrytest.entity.Pathology;
import com.knowledgebasedsystem.bloodbiochemistrytest.entity.Question;
import com.knowledgebasedsystem.bloodbiochemistrytest.model.request.FirstProcessRequest;
import com.knowledgebasedsystem.bloodbiochemistrytest.model.request.QuestionSecondProcess;
import com.knowledgebasedsystem.bloodbiochemistrytest.model.request.SecondProcessRequest;
import com.knowledgebasedsystem.bloodbiochemistrytest.model.response.FirstProcessResponse;
import com.knowledgebasedsystem.bloodbiochemistrytest.model.response.SecondProcessResponse;
import com.knowledgebasedsystem.bloodbiochemistrytest.repository.AdviceRepository;
import com.knowledgebasedsystem.bloodbiochemistrytest.repository.PathologyRepository;
import com.knowledgebasedsystem.bloodbiochemistrytest.repository.QuestionRepository;
import com.knowledgebasedsystem.bloodbiochemistrytest.service.AnalysisBloodBiochemistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnalysisBloodBiochemistryServiceImpl implements AnalysisBloodBiochemistryService {

    @Autowired
    private PathologyRepository pathologyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AdviceRepository adviceRepository;

    @Override
    public FirstProcessResponse firstProcess(FirstProcessRequest request) {
        FirstProcessResponse response = new FirstProcessResponse();
        List<Pathology> pathologyList = new ArrayList<>();
        String bmi = calculateBMI(request.getWeight(), request.getHeight());
        List<Question> question = new ArrayList<>();
        if (request.getGlucose() >= BloodBiochemistryLimit.PREDIABETES_MIN && request.getGlucose() < BloodBiochemistryLimit.GLUCOSE_HUNGRY && request.getHba1c() >= BloodBiochemistryLimit.HBA1C) {
            // Tiền tiểu đường - chắc chắn
            Optional<Pathology> pathology = pathologyRepository.findById("PATHOLOGY001");
            pathologyList.add(pathology.get());
            question = questionRepository.findLikePathology("PATHOLOGY001");
        }
        if (request.getGlucose() >= BloodBiochemistryLimit.GLUCOSE_HUNGRY && request.getHba1c() >= BloodBiochemistryLimit.HBA1C) {
            // Tiểu đường - chắc chắn
            Optional<Pathology> pathology = pathologyRepository.findById("PATHOLOGY002");
            pathologyList.add(pathology.get());
            question = questionRepository.findLikePathology("PATHOLOGY002");
        }
        if (request.getGlucose() >= BloodBiochemistryLimit.PREDIABETES_MIN && request.getGlucose() < BloodBiochemistryLimit.GLUCOSE_HUNGRY && request.getHba1c() < BloodBiochemistryLimit.HBA1C) {
            // Tiền tiểu đường - nghi ngờ
            Optional<Pathology> pathology = pathologyRepository.findById("PATHOLOGY003");
            pathologyList.add(pathology.get());
            question = questionRepository.findLikePathology("PATHOLOGY003");
        }
        if (request.getGlucose() >= BloodBiochemistryLimit.GLUCOSE_HUNGRY && request.getHba1c() < BloodBiochemistryLimit.HBA1C) {
            // Tiểu đường - nghi ngờ
            Optional<Pathology> pathology = pathologyRepository.findById("PATHOLOGY004");
            pathologyList.add(pathology.get());
            question = questionRepository.findLikePathology("PATHOLOGY004");
        }
        if (!(request.getGender().equalsIgnoreCase("female") && request.getPregnant())) {
            for (int i=0; i< question.size(); i++) {
                if (question.get(i).getId().equalsIgnoreCase("QUESTION001")) {
                    question.remove(i);
                    break;
                }
            }
        }
        response.setPathologyList(pathologyList);
        response.setQuestionList(question);
        return response;
    }

    @Override
    public SecondProcessResponse secondProcess(SecondProcessRequest request) {
        SecondProcessResponse response = new SecondProcessResponse();
        List<Advice> adviceList = new ArrayList<>();
        List<Pathology> pathologyList = request.getPathologyList();
        List<QuestionSecondProcess> questionList = request.getQuestionSecondProcesses();
        for (int i = 0; i < pathologyList.size(); i++) {
            if (pathologyList.get(i).getId().contains("PATHOLOGY001") || pathologyList.get(i).getId().contains("PATHOLOGY002")
                    || pathologyList.get(i).getId().contains("PATHOLOGY003") || pathologyList.get(i).getId().contains("PATHOLOGY004")) {
                QuestionSecondProcess fourMany = new QuestionSecondProcess();
                for (int j = 0; j < questionList.size(); j++) {
                    if (questionList.get(i).getId().contains("QUESTION003")) {
                        fourMany.setId(questionList.get(i).getId());
                        fourMany.setResult(questionList.get(i).isResult());
                    }
                }
                if (fourMany.isResult()) {
                    Optional<Advice>advice = adviceRepository.findById("ADVICE001");
                    adviceList.add(advice.get());
                }
            }
        }
        response.setAdviceList(adviceList);
        return response;
    }

    @Override
    public String calculateBMI(Float weight, Float height) {
        Float bmi = weight / (height * height);
        if (bmi < 18.5) {
            return BMI.BMI_1;
        } else if (bmi >= 18.5 && bmi < 23) {
            return BMI.BMI_2;
        } else if (bmi >= 23 && bmi < 25) {
            return BMI.BMI_3;
        } else if (bmi >= 25) {
            return BMI.BMI_4;
        }
        return "NO_DATA";
    }

}
