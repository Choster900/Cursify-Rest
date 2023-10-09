package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Section;
import com.itca.cursify.service.SectionService;
import com.itca.cursify.service.dto.SectionInDTO;
import com.itca.cursify.service.dto.UserInDTO;
import com.itca.cursify.service.dto.UserWithRoleDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/section")
public class SectionController {
    private SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }
    @PostMapping
    public Section createUser(@RequestBody SectionInDTO sectionInDTO) {
        return this.sectionService.addSecctionToCourse(sectionInDTO);
    }
}
