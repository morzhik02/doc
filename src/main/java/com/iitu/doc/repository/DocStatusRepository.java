package com.iitu.doc.repository;

import com.iitu.doc.models.entity.DocStatus;
import com.iitu.doc.models.entity.DocType;
import com.iitu.doc.models.enums.StatusCode;
import com.iitu.doc.models.enums.TypeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocStatusRepository extends JpaRepository<DocStatus, Long> {
    DocStatus findByCode(StatusCode code);
}
