package com.itca.cursify.service;

import com.itca.cursify.mapper.AnswerOptionDTOToAnswerOption;
import com.itca.cursify.persistece.entity.AnswerOption;
import com.itca.cursify.persistece.repository.AnswerOptionRepository;
import com.itca.cursify.service.dto.AnswerOptionDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
    public AnswerOption updateOptionInQuestion(Long optionId,AnswerOptionDTO answerOptionDTO){
        Optional<AnswerOption> answerOptionOptional = this.answerOptionRepository.findById(optionId);
        AnswerOption answerOption = answerOptionOptional.get();
        AnswerOption answerUpdate = this.answerOptionDTOToAnswerOption.map(answerOptionDTO);
        answerOption.setOptionText(answerUpdate.getOptionText());
        answerOption.setQuestion(answerUpdate.getQuestion());
        answerOption.setOptionIsCorrect(answerUpdate.getOptionIsCorrect());
        answerOption.setModifiedAtOption(LocalDateTime.now());
        return this.answerOptionRepository.save(answerOption);
    }

}
