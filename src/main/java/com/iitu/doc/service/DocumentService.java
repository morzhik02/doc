package com.iitu.doc.service;

import com.iitu.doc.models.dto.DocChangeStatusDto;
import com.iitu.doc.models.dto.DocCreatedDto;
import com.iitu.doc.models.dto.DocListInfoDto;
import com.iitu.doc.models.dto.DocSearchDto;
import com.iitu.doc.models.entity.DocType;
import com.iitu.doc.models.entity.Document;
import com.iitu.doc.models.entity.User;
import com.iitu.doc.models.enums.TypeCode;

import java.util.List;

public interface DocumentService {
    void save(DocCreatedDto docCreatedDto);
    void changeStatus(DocChangeStatusDto docChangeStatusDto);
    List<DocListInfoDto> findAll(DocSearchDto dto);
//    Document createDocument(DocType docType, User username);
}
