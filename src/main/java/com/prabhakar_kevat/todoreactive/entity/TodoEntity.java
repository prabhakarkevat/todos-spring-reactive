package com.prabhakar_kevat.todoreactive.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table("TODOS") 
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TodoEntity {

    @Id
    private Long id;

    private String title;

    private Boolean completed;
}
