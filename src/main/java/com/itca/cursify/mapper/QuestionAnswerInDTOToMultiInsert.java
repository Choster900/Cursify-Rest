package com.itca.cursify.mapper;

import com.itca.cursify.persistece.entity.AnswerOption;
import com.itca.cursify.persistece.entity.Exam;
import com.itca.cursify.persistece.entity.Question;
import com.itca.cursify.persistece.repository.ExamRepository;
import com.itca.cursify.service.dto.QuestionWithAnswersDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionAnswerInDTOToMultiInsert implements IMapper<QuestionWithAnswersDTO, Question>{

    private final ExamRepository examRepository;

    public QuestionAnswerInDTOToMultiInsert(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Question map(QuestionWithAnswersDTO in) {
        Question question = new Question();
        question.setQuestionText(in.getQuestionText());
        Exam exam = this.examRepository.findById(in.getExamId())
                .orElseThrow(() -> new IllegalStateException("Course not found" + in.getExamId()));
        question.setExamQuestions(exam);

        //
        List<QuestionWithAnswersDTO.AnswerDTO> answerDTOList = in.getAnswers();
        //Entity
        List<AnswerOption> answerOptionList = new ArrayList<>();

        for (QuestionWithAnswersDTO.AnswerDTO answerDTO : answerDTOList) {
            AnswerOption answerOption = new AnswerOption();
            answerOption.setOptionText(answerDTO.getOptionText());
            answerOption.setOptionIsCorrect(answerDTO.getOptionIsCorrect());

            answerOption.setQuestion(question);
            answerOptionList.add(answerOption);
        }
        // Asignar la lista de respuestas a la pregunta
        question.setAnswerOptions(answerOptionList);
        return question;
    }
}
