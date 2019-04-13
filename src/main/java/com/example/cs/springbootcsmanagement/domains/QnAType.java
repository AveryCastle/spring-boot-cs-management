package com.example.cs.springbootcsmanagement.domains;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tst_cs_qna_type")
@Getter
@NoArgsConstructor
public class QnAType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "expo_order", length = 10)
    private int expoOrder = 0;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER, mappedBy = "upperQnAType")
    private Set<QnAType> subQnATypes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "up_qna_type_id", foreignKey = @ForeignKey(name = "fk_tst_cs_qna_type_id"))
    private QnAType upperQnAType;

    @Builder
    public QnAType(String title, int expoOrder, QnAType upperQnAType) {
        this.title = title;
        this.expoOrder = expoOrder;
        this.upperQnAType = upperQnAType;
    }
}
