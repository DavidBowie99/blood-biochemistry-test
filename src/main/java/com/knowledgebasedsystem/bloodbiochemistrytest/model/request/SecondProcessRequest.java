package com.knowledgebasedsystem.bloodbiochemistrytest.model.request;

import com.knowledgebasedsystem.bloodbiochemistrytest.entity.Pathology;
import com.knowledgebasedsystem.bloodbiochemistrytest.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecondProcessRequest {
    private String name;//Tên

    private Integer age;//Tuổi

    private String gender;//Giới tính: Nam/Nữ

    private Float weight;//Cân nặng - ĐƠn vị: kg

    private Float height;//Chiều cao - Đơn vị: cm

    private Boolean pregnant;//Có đang mang thai: TRUE/FALSE

    private Float creatinin;//Chỉ số careatinin huyết thanh

    private List<Pathology> pathologyList;//Danh sách bệnh

    private List<QuestionSecondProcess> questionSecondProcesses;//Danh sách câu hỏi
}
