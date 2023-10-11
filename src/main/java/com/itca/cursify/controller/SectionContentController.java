package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.SectionContent;
import com.itca.cursify.service.SectionContentService;
import com.itca.cursify.service.dto.SectionContentDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/sectionContent")
public class SectionContentController {
    private final SectionContentService sectionContentService;

    public SectionContentController(SectionContentService sectionContentService) {
        this.sectionContentService = sectionContentService;
    }
    @PostMapping
    public SectionContent createNewContent(
            @RequestParam("fileVideoContent") MultipartFile fileVideoContent,
            @RequestParam("contentName") String contentName,
            @RequestParam("contentType") String contentType,
            @RequestParam("contentFileName") String contentFileName,
            @RequestParam("sectionId") Long sectionId
    ){
        SectionContentDTO sectionContentDTO = new SectionContentDTO();
        sectionContentDTO.setFileVideoContent(fileVideoContent);
        sectionContentDTO.setContentName(contentName);
        sectionContentDTO.setContentType(contentType);
        sectionContentDTO.setContentFileName(contentFileName);
        sectionContentDTO.setSectionId(sectionId);
        return this.sectionContentService.addContentInASection(sectionContentDTO);
    }
}
