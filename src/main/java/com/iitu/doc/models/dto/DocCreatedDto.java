package com.iitu.doc.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iitu.doc.models.enums.StatusCode;
import com.iitu.doc.models.enums.TypeCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@ToString
@Schema(description = "DTO for create posts")
public class DocCreatedDto {
    @Schema(description = "Document type code", example = "STATEMENT")
    @NotNull(message = "Document type field required")
    TypeCode typeCode;

}
