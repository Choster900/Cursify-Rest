package com.itca.cursify.service;

import com.itca.cursify.mapper.SectionContentInDTOToSectionContent;
import com.itca.cursify.persistece.entity.SectionContent;
import com.itca.cursify.persistece.repository.SectionContentRepository;
import com.itca.cursify.service.dto.CourseInDTO;
import com.itca.cursify.service.dto.SectionContentDTO;
import com.itca.cursify.service.dto.SectionInDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SectionContentService {
    private final SectionContentRepository sectionContentRepository;
    private final SectionContentInDTOToSectionContent sectionContentInDTOToSectionContent;
    public SectionContentService(SectionContentRepository sectionContentRepository, SectionContentInDTOToSectionContent sectionContentInDTOToSectionContent) {
        this.sectionContentRepository = sectionContentRepository;
        this.sectionContentInDTOToSectionContent = sectionContentInDTOToSectionContent;
    }
    public SectionContent addContentInASection(SectionContentDTO sectionContentDTO){
        SectionContent sectionContent = sectionContentInDTOToSectionContent.map(sectionContentDTO);
        return this.sectionContentRepository.save(sectionContent);
    }
    public SectionContent modifiContent(Long contentId, SectionContentDTO sectionContentDTO){
        Optional<SectionContent> contentOptional = sectionContentRepository.findById(contentId);
        SectionContent content = contentOptional.get();
        SectionContent updateSectionContent = sectionContentInDTOToSectionContent.map(sectionContentDTO);

        content.setContentName(updateSectionContent.getContentName());
        content.setSection(updateSectionContent.getSection());
        content.setContentFileName(updateSectionContent.getContentFileName());
        content.setContentType(updateSectionContent.getContentType());
        content.setModifiedAtContent(LocalDateTime.now());
        return this.sectionContentRepository.save(content);

    }
}
