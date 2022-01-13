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
        if (checkAllParam(request)) {
            Optional<Pathology> pathology = pathologyRepository.findById("PATHOLOGY000");
            pathologyList.add(pathology.get());
            response.setPathologyList(pathologyList);
            return response;
        }
        if (request.getGlucose() >= 5.6 && request.getGlucose() < 7.0 && request.getHba1c() >= 5.7) {
            Optional<Pathology> pathology = pathologyRepository.findById("PATHOLOGY001");
            pathologyList.add(pathology.get());
            question = questionRepository.findLikePathology("PATHOLOGY001");//Tiền tiểu đường
        }
        if (request.getGlucose() >= 7.0 && request.getHba1c() >= 6.5) {
            Optional<Pathology> pathology = pathologyRepository.findById("PATHOLOGY002");//Tiểu đường
            pathologyList.add(pathology.get());
            question = questionRepository.findLikePathology("PATHOLOGY002");
        }
        if (request.getGlucose() >= 5.6 && request.getGlucose() < 7.0 && request.getHba1c() < 5.7) {
            Optional<Pathology> pathology = pathologyRepository.findById("PATHOLOGY003");//
            pathologyList.add(pathology.get());
            question = questionRepository.findLikePathology("PATHOLOGY003");
        }
        if (request.getGlucose() >= 7.0 && request.getHba1c() < 6.5) {
            Optional<Pathology> pathology = pathologyRepository.findById("PATHOLOGY004");
            pathologyList.add(pathology.get());
            question = questionRepository.findLikePathology("PATHOLOGY004");
        }
        if ((request.getAcidUric() > 8.0 && request.getGender().equalsIgnoreCase("male"))
                || (request.getAcidUric() > 7.5 && request.getGender().equalsIgnoreCase("female"))) {
            Optional<Pathology> pathology = pathologyRepository.findById("PATHOLOGY005");
            pathologyList.add(pathology.get());
            question.addAll(questionRepository.findLikePathology("PATHOLOGY005"));
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
                QuestionSecondProcess question001 = new QuestionSecondProcess();
                QuestionSecondProcess question002 = new QuestionSecondProcess();
                for (int j = 0; j < questionList.size(); j++) {
                    if (questionList.get(j).getId().contains("QUESTION001")) {
                        question001.setId(questionList.get(i).getId());
                        question001.setResult(questionList.get(i).isResult());
                        break;
                    }
                }
                for (int j = 0; j < questionList.size(); j++) {
                    if (questionList.get(j).getId().contains("QUESTION002")) {
                        question002.setId(questionList.get(i).getId());
                        question002.setResult(questionList.get(i).isResult());
                        break;
                    }
                }
                if (!request.getPregnant()) {
                    if (request.getAge() < 30 && question002.isResult() && (pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY002") || pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY004"))) {
                        Optional<Advice> advice = adviceRepository.findById("ADVICE001");
                        adviceList.add(advice.get());
                    }
                    if (request.getAge() < 30 && !question002.isResult() && (pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY002") || pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY004"))) {
                        Optional<Advice> advice = adviceRepository.findById("ADVICE002");
                        adviceList.add(advice.get());
                    }
                    if (request.getAge() > 30 && (pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY002") || pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY004"))) {
                        Optional<Advice> advice = adviceRepository.findById("ADVICE003");
                        adviceList.add(advice.get());
                    }
                    if (request.getAge() < 30 && question002.isResult() && (pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY001") || pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY003"))) {
                        Optional<Advice> advice = adviceRepository.findById("ADVICE004");
                        adviceList.add(advice.get());
                    }
                    if (!question002.isResult() && (pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY001") || pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY003"))) {
                        Optional<Advice> advice = adviceRepository.findById("ADVICE005");
                        adviceList.add(advice.get());
                    }
                }
                if (request.getPregnant()) {
                    if (request.getAge() < 30 && !question002.isResult() && (pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY001") || pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY003"))) {
                        Optional<Advice> advice = adviceRepository.findById("ADVICE006");
                        adviceList.add(advice.get());
                    }
                    if (request.getAge() >= 30 && !question002.isResult()  && (pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY001") || pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY003"))) {
                        Optional<Advice> advice = adviceRepository.findById("ADVICE007");
                        adviceList.add(advice.get());
                    }
                    if (request.getAge() < 30 && !question002.isResult()  && (pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY002") || pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY004"))) {
                        Optional<Advice> advice = adviceRepository.findById("ADVICE008");
                        adviceList.add(advice.get());
                    }
                    if (request.getAge() >= 30 && !question002.isResult()  && (pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY002") || pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY004"))) {
                        Optional<Advice> advice = adviceRepository.findById("ADVICE009");
                        adviceList.add(advice.get());
                    }
                    if (request.getAge() >= 30 && question002.isResult()  && (pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY001") || pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY003"))) {
                        Optional<Advice> advice = adviceRepository.findById("ADVICE010");
                        adviceList.add(advice.get());
                    }
                    if (request.getAge() < 30 && question002.isResult()  && (pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY001") || pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY003"))) {
                        Optional<Advice> advice = adviceRepository.findById("ADVICE013");
                        adviceList.add(advice.get());
                    }
                    if (request.getAge() >= 30 && question002.isResult()  && (pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY002") || pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY004"))) {
                        Optional<Advice> advice = adviceRepository.findById("ADVICE014");
                        adviceList.add(advice.get());
                    }
                    if (request.getAge() < 30 && question002.isResult() && (pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY002") || pathologyList.get(i).getId().equalsIgnoreCase("PATHOLOGY004"))) {
                        Optional<Advice> advice = adviceRepository.findById("ADVICE015");
                        adviceList.add(advice.get());
                    }
                }
            }
            if (pathologyList.get(i).getId().contains("PATHOLOGY005")) {
                for (int j = 0; j < questionList.size(); j++) {
                    if (questionList.get(j).getId().contains("QUESTION003")) {
                        if (questionList.get(j).isResult()) {
                            Optional<Advice> advice = adviceRepository.findById("ADVICE011");
                            adviceList.add(advice.get());
                        } else {
                            Optional<Advice> advice = adviceRepository.findById("ADVICE012");
                            adviceList.add(advice.get());
                        }
                        break;
                    }
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

    private boolean checkAllParam(FirstProcessRequest request) {
        if (request.getGlucose() < 5.6 && request.getHba1c() < 5.7 && request.getAlbumin() >= 35 && request.getAlbumin() <= 50
                && request.getAlt() >= 7 && request.getAlt() <= 56 && request.getAlp() >= 30 && request.getAlp() <= 120
                && request.getUre() >= 2.5 && request.getUre() <= 7.5 && request.getBilirubinTp() >= 3.4 && request.getBilirubinTp() <= 17.1
                && request.getBilirubinTt() <= 7.0) {
            if (request.getGender().equalsIgnoreCase("male")) {
                if (request.getAst() <= 50 && request.getAst() >= 10 && request.getCreatinin() <= 106 && request.getCreatinin() >= 53
                        && request.getAcidUric() <= 8.0 && request.getAcidUric() >= 2.5) {
                    return true;
                } else {
                    return false;
                }
            }
            if (request.getGender().equalsIgnoreCase("female")) {
                if (request.getAst() <= 35 && request.getAst() >= 9 && request.getCreatinin() <= 97 && request.getCreatinin() >= 44
                        && request.getAcidUric() <= 7.5 && request.getAcidUric() >= 1.9) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
