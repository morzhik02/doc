package com.iitu.doc.converter.doc;

import com.iitu.doc.models.dto.DocListInfoDto;
import com.iitu.doc.models.entity.Document;
import com.iitu.doc.util.JwtUtil;
import com.iitu.doc.util.converter.AbstractConverter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DocListInfoDtoConverter  extends AbstractConverter<Document, DocListInfoDto> {

    @Override
    public void fill(Document source, DocListInfoDto target) {
        String role = JwtUtil.getRole();
        target.setId(source.getId());
        target.setType(source.getType().getName());
        if ("MODERATOR".equals(role) || "ADMIN".equals(role)) {
            target.setStatus(source.getStatus().getName());
        }
    }
}