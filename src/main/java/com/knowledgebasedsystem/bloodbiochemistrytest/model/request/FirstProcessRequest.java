package com.knowledgebasedsystem.bloodbiochemistrytest.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FirstProcessRequest {

    private String name;//Tên

    private Integer age;//Tuổi

    private String gender;//Giới tính: Nam/Nữ

    private Float weight;//Cân nặng - ĐƠn vị: kg

    private Float height;//Chiều cao - Đơn vị: cm

    private Boolean pregnant;//Có đang mang thai: TRUE/FALSE

    private Float hba1c;//HbA1C - Đơn vị: %

    private Float glucose;//Chỉ số đường huyết - đơn vị: mmol/L

    private Float bilirubinTP;//Chỉ số bilirubin toàn phần

    private Float bilirubinTT;//Chỉ số bilirubin trực tiếp

    private Float alp;//Chỉ số ALP

    private Float ast;//Chỉ số AST

    private Float alt;//Chỉ số ALT

    private Float albumin;//Chỉ số albumin

    private Float ure;//Chỉ số ure máu

    private Float creatinin;//Chỉ số careatinin huyết thanh

    private Float acidUric;//Chỉ số acid uric
}
