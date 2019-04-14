package com.example.cs.springbootcsmanagement.domains;

import com.example.cs.springbootcsmanagement.enums.PoCType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tst_cs_poc")
@Getter
@NoArgsConstructor
public class PoC extends BaseEntity {

    @Id
    @Column(name = "id", length = 5)
    private String id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public PoC(PoCType pocType) {
        this.id = pocType.getLegacyCode();
        this.name = pocType.getDescription();
    }
}
