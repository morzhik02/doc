package com.iitu.doc.util.spec;

import com.iitu.doc.models.entity.BaseEntity;
import com.iitu.doc.models.entity.DocStatus;
import com.iitu.doc.models.entity.DocType;
import com.iitu.doc.models.entity.Document;
import com.iitu.doc.models.enums.StatusCode;
import com.iitu.doc.models.enums.TypeCode;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;

@UtilityClass
public class DocSpec {
    public Specification<Document> userFilter(Long userId) {
        return (r, cq, cb) -> cb.equal(r.get(Document.Fields.user).get(BaseEntity.Fields.id), userId);
    }

    public Specification<Document> typeFilter(TypeCode type) {
        return (r, cq, cb) -> cb.equal(r.get(Document.Fields.type).get(DocType.Fields.code),
                type);
    }

    public Specification<Document> statusFilter(StatusCode status) {
        return (r, cq, cb) -> cb.equal(r.get(Document.Fields.status).get(DocStatus.Fields.code), status);
    }

    public Specification<Document> docOrderByCreatedDate() {
        return (r, cq, cb) -> {
            Path<Document> field = r.get(Document.Fields.createdAt);
            switchDirection(Sort.Direction.DESC, cq, cb, field);
            return cb.and();
        };
    }

    private void switchDirection(
        Sort.Direction direction, CriteriaQuery<?> cq, CriteriaBuilder cb, Path<?> field) {
        switch (direction) {
            case ASC:
                cq.orderBy(cb.asc(field));
                break;
            case DESC:
                cq.orderBy(cb.desc(field));
                break;
        }
    }
}
