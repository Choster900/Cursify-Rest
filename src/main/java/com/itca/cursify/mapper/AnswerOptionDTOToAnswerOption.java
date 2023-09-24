package com.itca.cursify.mapper;

import com.itca.cursify.persistece.entity.AnswerOption;
import com.itca.cursify.persistece.entity.Question;
import com.itca.cursify.persistece.entity.User;
import com.itca.cursify.persistece.repository.QuestionRepository;
import com.itca.cursify.service.dto.AnswerOptionDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AnswerOptionDTOToAnswerOption implements IMapper<AnswerOptionDTO, AnswerOption> {
    private final QuestionRepository questionRepository;

    public AnswerOptionDTOToAnswerOption(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public AnswerOption map(AnswerOptionDTO in) {
        AnswerOption answerOption = new AnswerOption();
        answerOption.setOptionsText(in.getOptionsText());
        answerOption.setOptionIsCorrect(in.getOptionIsCorrect());
        Question question = questionRepository.findById(in.getQuestionId())
                .orElseThrow(() -> new IllegalStateException("User not found"));
        answerOption.setQuestion(question);
        answerOption.setCreatedAtOption(LocalDateTime.now());
        return answerOption;
    }
}
