package com.iitu.doc.converter.doc;


import com.iitu.doc.models.dto.DocCreatedDto;
import com.iitu.doc.models.entity.DocType;
import com.iitu.doc.models.entity.Document;
import com.iitu.doc.models.enums.StatusCode;
import com.iitu.doc.repository.DocStatusRepository;
import com.iitu.doc.repository.DocTypeRepository;
import com.iitu.doc.service.UserService;
import com.iitu.doc.util.DateTimeUtil;
import com.iitu.doc.util.JwtUtil;
import com.iitu.doc.util.converter.AbstractConverter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DocCreateDtoConverter extends AbstractConverter<DocCreatedDto, Document> {

    DocTypeRepository docTypeRepository;
    DocStatusRepository docStatusRepository;
    UserService userService;

    @Override
    public void fill(DocCreatedDto source, Document target) {
        String username = JwtUtil.getUsername();
        target.setUser(userService.findByUsername(username));
        target.setStatus(docStatusRepository.findByCode(StatusCode.NEW));
        target.setType(docTypeRepository.findByCode(source.getTypeCode()));
        log.info("Document target:" + target);
    }
}
