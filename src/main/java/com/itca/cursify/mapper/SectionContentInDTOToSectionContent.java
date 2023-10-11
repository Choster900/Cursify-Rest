package com.itca.cursify.mapper;

import com.itca.cursify.persistece.entity.Section;
import com.itca.cursify.persistece.entity.SectionContent;
import com.itca.cursify.persistece.entity.enums.Published;
import com.itca.cursify.persistece.repository.SectionRepository;
import com.itca.cursify.service.Storage.StorageService;
import com.itca.cursify.service.dto.SectionContentDTO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
@Component
public class SectionContentInDTOToSectionContent implements IMapper<SectionContentDTO, SectionContent>{
    private final StorageService storageService;
    private final HttpServletRequest servletRequest;
    private final SectionRepository sectionRepository;

    public SectionContentInDTOToSectionContent(StorageService storageService, HttpServletRequest servletRequest, SectionRepository sectionRepository) {
        this.storageService = storageService;
        this.servletRequest = servletRequest;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public SectionContent map(SectionContentDTO in) {
        SectionContent sectionContent = new SectionContent();
        sectionContent.setContentName(in.getContentName());

        sectionContent.setContentFileName("/media/" + in.getContentFileName());
        sectionContent.setContentType(in.getContentType());

        String path = storageService.store(in.getFileVideoContent());
        String host = servletRequest.getRequestURL().toString().replace(servletRequest.getRequestURI(), "");

        Section section = sectionRepository.findById(in.getSectionId())
                .orElseThrow(() -> new IllegalArgumentException("Seccion no encontrada con ID: "+ in.getSectionId()));
        sectionContent.setSection(section);

        sectionContent.setContentState(Published.PRIVADO);
        sectionContent.setCreatedAtContent(LocalDateTime.now());
        return sectionContent;
    }
}
