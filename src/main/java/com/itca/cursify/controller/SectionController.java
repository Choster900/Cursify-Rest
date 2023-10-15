package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Section;
import com.itca.cursify.service.SectionService;
import com.itca.cursify.service.dto.SectionInDTO;
import com.itca.cursify.service.dto.UserInDTO;
import com.itca.cursify.service.dto.UserWithRoleDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/section")
public class SectionController {
    private SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }
    @PostMapping
    public Section createSection(@RequestBody SectionInDTO sectionInDTO) {
        return this.sectionService.addSectionToCourse(sectionInDTO);
    }
    @PutMapping("/{sectionId}")
    public Section updateSection(@PathVariable Long sectionId, @RequestBody SectionInDTO sectionInDTO) {
        return sectionService.modifySectionCourse(sectionId, sectionInDTO);
    }

    @PutMapping("changeState/{sectionId}")
    public Section updateSectionState(@PathVariable Long sectionId) {
        return sectionService.changeStateSection(sectionId);
    }
}
