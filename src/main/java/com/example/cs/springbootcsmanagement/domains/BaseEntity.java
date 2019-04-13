package com.example.cs.springbootcsmanagement.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity {

    @CreatedBy
    @Column(name = "reg_id", length = 50, nullable = false, updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "reg_dt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedBy
    @Column(name = "upd_id", length = 50, nullable = false)
    private String modifiedBy;

    @LastModifiedDate
    @Column(name = "upd_dt", nullable = false)
    private LocalDateTime modifiedAt;
}
