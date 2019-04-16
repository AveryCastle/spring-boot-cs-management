package com.example.cs.springbootcsmanagement.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class QnATypeDTO implements Serializable {

    private Long id;

    private String title;

    private int ordering;

    private Set<QnATypeDTO> subTypes = new LinkedHashSet<>();

    @QueryProjection
    public QnATypeDTO(Long id, String title, int ordering, Set<QnATypeDTO> subTypes) {
        this.id = id;
        this.title = title;
        this.ordering = ordering;
        this.subTypes = subTypes;
    }
}
