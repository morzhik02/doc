package com.iitu.doc.models.dto;

import com.iitu.doc.models.enums.StatusCode;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "DTO for change document status")
public class DocChangeStatusDto {

    @Schema(description = "Document id", example = "1")
    Long id;

    @Schema(description = "Document status code", example = "CANCELED")
    StatusCode statusCode;
}
