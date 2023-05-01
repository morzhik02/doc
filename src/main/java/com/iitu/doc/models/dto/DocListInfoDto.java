package com.iitu.doc.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "DTO for documents list info")
public class DocListInfoDto {
    @Schema(description = "Document id", example = "1")
    Long id;

    @Schema(description = "Document category", example = "Заявление")
    String type;

    @Schema(description = "Document status", example = "Завершенно")
    String status;
}
