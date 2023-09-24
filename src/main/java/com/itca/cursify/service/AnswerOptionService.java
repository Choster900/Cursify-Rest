package com.itca.cursify.service;

import com.itca.cursify.mapper.AnswerOptionDTOToAnswerOption;
import com.itca.cursify.persistece.entity.AnswerOption;
import com.itca.cursify.persistece.repository.AnswerOptionRepository;
import com.itca.cursify.service.dto.AnswerOptionDTO;
import org.springframework.stereotype.Service;

@Service
public class AnswerOptionService {
    private final AnswerOptionRepository answerOptionRepository;
    private final AnswerOptionDTOToAnswerOption answerOptionDTOToAnswerOption;
    public AnswerOptionService(AnswerOptionRepository answerOptionRepository, AnswerOptionDTOToAnswerOption answerOptionDTOToAnswerOption) {
        this.answerOptionRepository = answerOptionRepository;
        this.answerOptionDTOToAnswerOption = answerOptionDTOToAnswerOption;
    }
    public AnswerOption addOptionInQuestion(AnswerOptionDTO answerOptionDTO){
        AnswerOption answerOption = answerOptionDTOToAnswerOption.map(answerOptionDTO);
        return this.answerOptionRepository.save(answerOption);
    }

}
