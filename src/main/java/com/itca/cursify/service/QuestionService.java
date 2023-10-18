package com.itca.cursify.service;

import com.itca.cursify.mapper.QuestionAnswerInDTOToMultiInsert;
import com.itca.cursify.mapper.QuestionInDTOToQuestion;
import com.itca.cursify.persistece.entity.AnswerOption;
import com.itca.cursify.persistece.entity.Question;
import com.itca.cursify.persistece.repository.AnswerOptionRepository;
import com.itca.cursify.persistece.repository.QuestionRepository;
import com.itca.cursify.service.dto.QuestionInDTO;
import com.itca.cursify.service.dto.QuestionWithAnswersDTO;
import com.itca.cursify.service.dto.QuestionsWithAnswers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionInDTOToQuestion questionInDTOToQuestion;
    private final QuestionAnswerInDTOToMultiInsert questionAnswerInDTOToMultiInsert;
    private final AnswerOptionRepository answerOptionRepository;
    public QuestionService(QuestionRepository questionRepository, QuestionInDTOToQuestion questionInDTOToQuestion, QuestionAnswerInDTOToMultiInsert questionAnswerInDTOToMultiInsert, AnswerOptionRepository answerOptionRepository) {
        this.questionRepository = questionRepository;
        this.questionInDTOToQuestion = questionInDTOToQuestion;
        this.questionAnswerInDTOToMultiInsert = questionAnswerInDTOToMultiInsert;
        this.answerOptionRepository = answerOptionRepository;
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

    public Question createQuestionMultiplesAnswer(QuestionWithAnswersDTO questionWithAnswersDTO){
        Question question = this.questionAnswerInDTOToMultiInsert.map(questionWithAnswersDTO);
        return  this.questionRepository.save(question);
    }

   /* public Question updateQuestionWithAnswers(Long questionId, QuestionWithAnswersDTO questionWithAnswersDTO) {
        Question question;

        if (questionId != null) {
            // Si se proporciona un ID de pregunta, busca la pregunta en la base de datos
            question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new IllegalStateException("Question not found" + questionId));
        } else {
            // Si no se proporciona un ID de pregunta, crea una nueva
            question = new Question();
        }

        // Actualiza la información de la pregunta
        question.setQuestionText(questionWithAnswersDTO.getQuestionText());

        // Procesa las respuestas
        List<QuestionWithAnswersDTO.AnswerDTO> answerDTOList = questionWithAnswersDTO.getAnswers();
        List<AnswerOption> answerOptionList = new ArrayList<>();

        for (QuestionWithAnswersDTO.AnswerDTO answerDTO : answerDTOList) {
            AnswerOption answerOption;

            if (answerDTO.getOptionId() != null) {
                // Si se proporciona un ID de respuesta, busca la respuesta en la base de datos
                answerOption = answerOptionRepository.findById(answerDTO.getOptionId())
                        .orElseThrow(() -> new IllegalStateException("Answer not found" + answerDTO.getOptionId()));
            } else {
                // Si no se proporciona un ID de respuesta, crea una nueva
                answerOption = new AnswerOption();
            }

            // Actualiza la información de la respuesta
            answerOption.setOptionsText(answerDTO.getOptionText());
            answerOption.setOptionIsCorrect(answerDTO.getOptionIsCorrect());
            answerOption.setQuestion(question);

            answerOptionList.add(answerOption);
        }

        // Asigna la lista de respuestas a la pregunta
        question.setAnswerOptions(answerOptionList);

        return questionRepository.save(question);
    }*/

}
