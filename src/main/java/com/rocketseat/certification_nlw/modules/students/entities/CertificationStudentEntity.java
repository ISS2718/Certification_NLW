package com.rocketseat.certification_nlw.modules.students.entities;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationStudentEntity {
    private UUID studentID;
    private UUID id;
    private String technology;
    private int grate;
    private List<AnswersCertificationsEntitys> answersCertificationsEntitys;
}
