package com.iitu.doc.service.impl;

import com.iitu.doc.converter.doc.DocCreateDtoConverter;
import com.iitu.doc.converter.doc.DocListInfoDtoConverter;
import com.iitu.doc.exceptions.DiplomaCoreException;
import com.iitu.doc.models.constants.ApiMessages;
import com.iitu.doc.models.dto.DocChangeStatusDto;
import com.iitu.doc.models.dto.DocCreatedDto;
import com.iitu.doc.models.dto.DocListInfoDto;
import com.iitu.doc.models.dto.DocSearchDto;
import com.iitu.doc.models.entity.DocStatus;
import com.iitu.doc.models.entity.DocType;
import com.iitu.doc.models.entity.Document;
import com.iitu.doc.models.entity.User;
import com.iitu.doc.models.enums.StatusCode;
import com.iitu.doc.models.enums.TypeCode;
import com.iitu.doc.repository.DocStatusRepository;
import com.iitu.doc.repository.DocTypeRepository;
import com.iitu.doc.repository.DocumentRepository;
import com.iitu.doc.service.DocumentService;
import com.iitu.doc.service.UserService;
import com.iitu.doc.util.JwtUtil;
import com.iitu.doc.util.spec.DocSpec;
import com.iitu.doc.util.specification.SpecificationBuilder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DocumentServiceImpl implements DocumentService {

    DocumentRepository documentRepository;
    DocCreateDtoConverter docCreateDtoConverter;
    DocStatusRepository docStatusRepository;
    DocTypeRepository docTypeRepository;
    UserService userService;
    DocListInfoDtoConverter docListInfoDtoConverter;

    @Override
    @Transactional
    public void save(DocCreatedDto docCreatedDto) {
        try {
            Document document = docCreateDtoConverter.convert(docCreatedDto);
            log.info("DOCUMENT POPSLE SAVE:" + document);
            documentRepository.save(document);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void changeStatus(DocChangeStatusDto docChangeStatusDto) {
        Document doc = getDoc(docChangeStatusDto.getId());
        doc.setStatus(docStatusRepository.findByCode(docChangeStatusDto.getStatusCode()));
        documentRepository.save(doc);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocListInfoDto> findAll(DocSearchDto dto) {
        User user = userService.findByUsername(JwtUtil.getUsername());
        String role = JwtUtil.getRole();

        Specification<Document> postSpec = new SpecificationBuilder<>();

        switch (role) {
            case "USER" -> postSpec.and(DocSpec.userFilter(user.getId()));
//            case "MODERATOR" -> postSpec.and(
//                    DocSpec.districtFilter(user.getDistrict().getName()));
//            case "ADMIN" -> postSpec.and(DocSpec.cityFilter(user.getCity().getName()));
        }


        if (Objects.nonNull(dto.getType())) {
            postSpec.and(DocSpec.typeFilter(dto.getType()));
        }
        if (Objects.nonNull(dto.getStatus())) {
            postSpec.and(DocSpec.statusFilter(dto.getStatus()));
        }

        postSpec.and(DocSpec.docOrderByCreatedDate());

        return docListInfoDtoConverter.convert(documentRepository.findAll(postSpec));
    }

//    @Override
//    public Document createDocument(DocType docType, User username) {
//        User user = userService.getUser(username.getUsername());
////        DocType docType = docTypeRepository.findByCode(TypeCode.valueOf(d));
//        DocStatus docStatus = docStatusRepository.findByCode(StatusCode.NEW);
//        Document document = new Document();
//        document.setUser(user);
//        document.setType(docType);
//        document.setStatus(docStatus);
//        return documentRepository.save(document);
//    }

    private Document getDoc(Long id) {
        return documentRepository.findById(id).orElseThrow(() -> new DiplomaCoreException(
                HttpStatus.BAD_REQUEST, ApiMessages.DOC_NOT_FOUND, "Document with this id not found"));
    }
}
