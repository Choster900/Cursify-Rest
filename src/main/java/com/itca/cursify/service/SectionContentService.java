package com.itca.cursify.service;

import com.itca.cursify.mapper.SectionContentInDTOToSectionContent;
import com.itca.cursify.persistece.entity.SectionContent;
import com.itca.cursify.persistece.repository.SectionContentRepository;
import com.itca.cursify.service.dto.SectionContentDTO;
import com.itca.cursify.service.dto.SectionInDTO;
import org.springframework.stereotype.Service;

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
}
