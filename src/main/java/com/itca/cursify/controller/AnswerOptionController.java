package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.AnswerOption;
import com.itca.cursify.service.AnswerOptionService;
import com.itca.cursify.service.dto.AnswerOptionDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/answersOptions")
public class AnswerOptionController {
    private final AnswerOptionService answerOptionService;

    public AnswerOptionController(AnswerOptionService answerOptionService) {
        this.answerOptionService = answerOptionService;
    }
    @PostMapping
    public AnswerOption addAnswerOptionInQuestion(@RequestBody AnswerOptionDTO answerOptionDTO){
        return this.answerOptionService.addOptionInQuestion(answerOptionDTO);
    }
    @PutMapping("/{optionId}")
    public AnswerOption updateAnswerOptionInQuestion(@PathVariable Long optionId,
                                                     @RequestBody AnswerOptionDTO answerOptionDTO){
        return this.answerOptionService.updateOptionInQuestion(optionId,answerOptionDTO);
    }
}
