package com.rocketseat.certification_nlw.modules.students.useCases;

import com.rocketseat.certification_nlw.modules.questions.entities.QuestionEntity;
import com.rocketseat.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.rocketseat.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.rocketseat.certification_nlw.modules.students.entities.AnswersCertificationsEntity;
import com.rocketseat.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.rocketseat.certification_nlw.modules.students.entities.StudentEntity;
import com.rocketseat.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import com.rocketseat.certification_nlw.modules.students.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) {
        // Buscar alternativas das perguntas
            // - Correta ou Incorreta
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());

        dto.getQuestionsAnswers().stream().forEach(questionAnswer -> {
            var question = questionsEntity.stream().filter(q -> q.getId().equals(questionAnswer.getQuestionID()))
            .findFirst().get();

            var findAlternative = question.getAlternatives().stream().filter(alternative -> alternative.isCorrect()).findFirst().get();

            if(findAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                questionAnswer.setCorrect(true);
            } else {
                questionAnswer.setCorrect(false);
            }

        });

        // Verificar se já existe Student (UUID) para o email
        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentId;
        if(student.isEmpty()) {
            // Criar Student
            var studentCreated = studentRepository.save(StudentEntity.builder().email(dto.getEmail()).build());
            studentId = studentCreated.getId();
        } else {
            studentId = student.get().getId();
        }

        // Salvar respostas da certificação
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();
        
        // Salvar informações do certificação
        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
        .technology(dto.getTechnology())
        .studentID(studentId)
        .answersCertificationsEntitys(answersCertifications)
        .build();

        return certificationStudentRepository.save(certificationStudentEntity);
    }
}
