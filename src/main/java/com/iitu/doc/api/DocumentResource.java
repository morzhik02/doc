package com.iitu.doc.api;

import com.iitu.doc.models.dto.DocChangeStatusDto;
import com.iitu.doc.models.dto.DocCreatedDto;
import com.iitu.doc.models.entity.Document;
import com.iitu.doc.models.enums.TypeCode;
import com.iitu.doc.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/doc")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Documents API", description = "Methods for work with documents")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class DocumentResource {
    DocumentService documentService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Method to save new post")
    public ResponseEntity<String> save(@RequestParam(value = "type") TypeCode typeCode){
        DocCreatedDto docCreatedDto = DocCreatedDto.builder().typeCode(typeCode).build();
        log.info("docCreatedDto:{}", docCreatedDto);
        documentService.save(docCreatedDto);
        log.info("docCreate save прошел");
        return ResponseEntity.ok("Document created successfully");
    }

    @PatchMapping("/status")
    @Operation(summary = "Method to change status of posts")
    public ResponseEntity<String> changeStatusOfDoc(@RequestBody DocChangeStatusDto docChangeStatusDto){
        documentService.changeStatus(docChangeStatusDto);
        return ResponseEntity.ok("Document status changed successfully");
    }



}
