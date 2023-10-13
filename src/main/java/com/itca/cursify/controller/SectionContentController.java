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
    @PutMapping("/{contentId}")
    public  SectionContent updateContent(
            @RequestParam(required = false) MultipartFile fileVideoContent,
            @RequestParam(required = false) String contentName,
            @RequestParam(required = false) String contentType,
            @RequestParam(required = false) String contentFileName,
            @RequestParam("sectionId") Long sectionId,
            @RequestParam(required = false) Long contentId
    ){
        SectionContentDTO sectionContentDTO = new SectionContentDTO();
        sectionContentDTO.setFileVideoContent(fileVideoContent);
        sectionContentDTO.setSectionId(sectionId);
        sectionContentDTO.setContentName(contentName);
        sectionContentDTO.setContentType(contentType);
        sectionContentDTO.setContentFileName(contentFileName);

        return this.sectionContentService.modifiContent(contentId,sectionContentDTO);
    }

}
