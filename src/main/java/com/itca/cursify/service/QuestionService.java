package com.itca.cursify.service;

import com.itca.cursify.mapper.QuestionInDTOToQuestion;
import com.itca.cursify.persistece.entity.Question;
import com.itca.cursify.persistece.repository.QuestionRepository;
import com.itca.cursify.service.dto.QuestionInDTO;
import com.itca.cursify.service.dto.QuestionsWithAnswers;
import com.itca.cursify.service.dto.UserWithRoleDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionInDTOToQuestion questionInDTOToQuestion;
    public QuestionService(QuestionRepository questionRepository, QuestionInDTOToQuestion questionInDTOToQuestion) {
        this.questionRepository = questionRepository;
        this.questionInDTOToQuestion = questionInDTOToQuestion;
    }
    public Question createQuestionForExam(QuestionInDTO questionInDTO) {
        Question question = this.questionInDTOToQuestion.map(questionInDTO);
        return this.questionRepository.save(question);
    }
    public List<QuestionsWithAnswers> findQuestionsByExamId(Long examId) {
        List<Question> questionsList = questionRepository.findByExamQuestionsExamId(examId);
        List<QuestionsWithAnswers> questionsWithAnswersList = new ArrayList<>();

        for (Question question : questionsList) {
            questionsWithAnswersList.add(new QuestionsWithAnswers(
                    question.getQuestionId(),
                    question.getQuestionText(),
                    question.getAnswerOptions()
            ));
        }

        return questionsWithAnswersList;
    }


}
