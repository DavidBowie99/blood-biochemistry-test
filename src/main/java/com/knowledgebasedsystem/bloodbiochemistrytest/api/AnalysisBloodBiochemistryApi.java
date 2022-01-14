package com.knowledgebasedsystem.bloodbiochemistrytest.api;

import com.knowledgebasedsystem.bloodbiochemistrytest.model.request.FirstProcessRequest;
import com.knowledgebasedsystem.bloodbiochemistrytest.model.request.SecondProcessRequest;
import com.knowledgebasedsystem.bloodbiochemistrytest.model.response.FirstProcessResponse;
import com.knowledgebasedsystem.bloodbiochemistrytest.model.response.SecondProcessResponse;
import com.knowledgebasedsystem.bloodbiochemistrytest.service.AnalysisBloodBiochemistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/blood-biochemistry-test")
public class AnalysisBloodBiochemistryApi {

    private final AnalysisBloodBiochemistryService analysisBloodBiochemistryService;

    @Autowired
    public AnalysisBloodBiochemistryApi(AnalysisBloodBiochemistryService analysisBloodBiochemistryService) {
        this.analysisBloodBiochemistryService = analysisBloodBiochemistryService;
    }

    @RequestMapping(value = "/first-process", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FirstProcessResponse firstProcessing(@RequestBody FirstProcessRequest request){
        FirstProcessResponse response = analysisBloodBiochemistryService.firstProcess(request);
        return response;
    }

    @RequestMapping(value = "/second-process", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SecondProcessResponse secondProcessing(@RequestBody SecondProcessRequest request){
        SecondProcessResponse response = analysisBloodBiochemistryService.secondProcess(request);
        return response;
    }
}
