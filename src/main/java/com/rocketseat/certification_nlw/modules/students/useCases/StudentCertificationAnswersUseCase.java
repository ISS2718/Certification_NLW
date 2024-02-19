package com.rocketseat.certification_nlw.modules.students.useCases;

import com.rocketseat.certification_nlw.modules.questions.entities.QuestionEntity;
import com.rocketseat.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.rocketseat.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.rocketseat.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import com.rocketseat.certification_nlw.modules.students.entities.AnswersCertificationsEntity;
import com.rocketseat.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.rocketseat.certification_nlw.modules.students.entities.StudentEntity;
import com.rocketseat.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import com.rocketseat.certification_nlw.modules.students.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

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

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {
        // Verificar se já tem a certificação
        var hasCertification = verifyIfHasCertificationUseCase.execute(new VerifyHasCertificationDTO(dto.getEmail(), dto.getTechnology()));

        if(hasCertification) {
            throw new Exception("Você já possui uma certificação para essa tecnologia!");
        }
        
        // Buscar alternativas das perguntas
            // - Correta ou Incorreta
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getQuestionsAnswers().stream().forEach(questionAnswer -> {
            var question = questionsEntity.stream().filter(q -> q.getId().equals(questionAnswer.getQuestionID()))
            .findFirst().get();

            var findAlternative = question.getAlternatives().stream().filter(alternative -> alternative.isCorrect()).findFirst().get();

            if(findAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                questionAnswer.setCorrect(true);
                correctAnswers.getAndIncrement();
            } else {
                questionAnswer.setCorrect(false);
            }

            var answerCertificationEntity = AnswersCertificationsEntity.builder()
            .answerID(questionAnswer.getAlternativeID())
            .questionID(questionAnswer.getQuestionID())
            .isCorrect(questionAnswer.isCorrect())
            .build();

            answersCertifications.add(answerCertificationEntity);
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
        
        // Salvar informações do certificação
        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
        .technology(dto.getTechnology())
        .studentID(studentId)
        .grade(correctAnswers.get())
        .build();

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

        answersCertifications.stream().forEach(answer -> {
            answer.setCertificationID(certificationStudentCreated.getId());
            answer.setCertificationStudentEntity(certificationStudentEntity);
        });

        certificationStudentEntity.setAnswersCertificationsEntitys(answersCertifications);
        certificationStudentRepository.save(certificationStudentEntity);

        return certificationStudentCreated;
    }
}
