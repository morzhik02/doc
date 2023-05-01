package com.iitu.doc.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.print.Doc;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "docs")
public class Document extends AuditableEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_code")
    DocType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_code")
    DocStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @CreationTimestamp
    Timestamp createdAt;

    @UpdateTimestamp
    Timestamp updatedAt;

}
