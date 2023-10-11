package com.itca.cursify.persistece.repository;

import com.itca.cursify.persistece.entity.SectionContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  SectionContentRepository extends JpaRepository<SectionContent,Long> {
}
