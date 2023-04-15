package com.example.todoreactive.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todo")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TodoEntity {

    @Id
    private String id;

    private String title;
}
