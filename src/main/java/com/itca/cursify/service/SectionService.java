package com.itca.cursify.service;

import com.itca.cursify.mapper.SectionInDTOToSection;
import com.itca.cursify.persistece.entity.Section;
import com.itca.cursify.persistece.entity.enums.Published;
import com.itca.cursify.persistece.repository.SectionRepository;
import com.itca.cursify.service.dto.SectionInDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;
    private final SectionInDTOToSection sectionInDTOToSection;
    public SectionService(SectionRepository sectionRepository, SectionInDTOToSection sectionInDTOToSection) {
        this.sectionRepository = sectionRepository;
        this.sectionInDTOToSection = sectionInDTOToSection;
    }
    public Section addSectionToCourse(SectionInDTO sectionInDTO){
        Section section = sectionInDTOToSection.map(sectionInDTO);
        return sectionRepository.save(section);
    }
    public Section modifySectionCourse(Long sectionId, SectionInDTO sectionInDTO){
        Optional<Section> sectionOptional = sectionRepository.findById(sectionId);
        Section section = sectionOptional.get();
        Section updateSection = sectionInDTOToSection.map(sectionInDTO);
        section.setSectionTitle(updateSection.getSectionTitle());
        section.setModifiedAtSection(LocalDateTime.now());
        return sectionRepository.save(section);
    }
    public Section changeStateSection(Long sectionId) {
        Optional<Section> sectionOptional = sectionRepository.findById(sectionId);
        Section section = sectionOptional.get();

        Published currentState = section.getSectionState();//Getting SectionState
        Published newState = (currentState == Published.PRIVADO) ? Published.PUBLICADO : Published.PRIVADO;

        section.setSectionState(newState);
        section.setModifiedAtSection(LocalDateTime.now());
        return sectionRepository.save(section);
    }

}
