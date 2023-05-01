package com.iitu.doc.repository;

import com.iitu.doc.models.entity.*;
import com.iitu.doc.models.enums.TypeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocTypeRepository extends JpaRepository<DocType, Long> {
    DocType findByCode(TypeCode code);
}
