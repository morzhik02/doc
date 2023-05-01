package com.iitu.doc.models.dto;

import com.iitu.doc.models.enums.StatusCode;
import com.iitu.doc.models.enums.TypeCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "DTO for docs search")
public class DocSearchDto {

    @Schema(description = "Document category filter", example = "STATEMENT")
    TypeCode type;

    @Schema(description = "Document status filter", example = "CANCELED")
    StatusCode status;
}
