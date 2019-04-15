package com.example.cs.springbootcsmanagement.domains;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tst_cs_poc_qna_type",
        uniqueConstraints = @UniqueConstraint(name = "uk_01_tst_cs_poc_qna_type", columnNames = {"poc_id", "qna_type_id"}))
@Getter
@NoArgsConstructor
public class PoCQnAType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "poc_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_tst_cs_poc_qna_type_poc_id"))
    private PoC poc;

    @ManyToOne
    @JoinColumn(name = "qna_type_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_tst_cs_poc_qna_type_qna_type_id"))
    private QnAType qnaType;

    @Column(name = "expo_order", length = 10, nullable = false)
    private int expoOrder = 0;

    @Builder
    public PoCQnAType(PoC poc, QnAType qnaType, int expoOrder) {
        this.poc = poc;
        this.qnaType = qnaType;
        this.expoOrder = expoOrder;
    }
}
