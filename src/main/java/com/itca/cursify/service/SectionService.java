package com.itca.cursify.service;

import com.itca.cursify.mapper.SectionInDTOToSection;
import com.itca.cursify.persistece.entity.Section;
import com.itca.cursify.persistece.repository.SectionRepository;
import com.itca.cursify.service.dto.SectionInDTO;
import org.springframework.stereotype.Service;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;
    private final SectionInDTOToSection sectionInDTOToSection;
    public SectionService(SectionRepository sectionRepository, SectionInDTOToSection sectionInDTOToSection) {
        this.sectionRepository = sectionRepository;
        this.sectionInDTOToSection = sectionInDTOToSection;
    }
    public Section addSecctionToCourse(SectionInDTO sectionInDTO){
        Section section = sectionInDTOToSection.map(sectionInDTO);
        return sectionRepository.save(section);

    }
}
