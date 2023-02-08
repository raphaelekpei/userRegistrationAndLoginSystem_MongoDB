package com.raphael.usersystem.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Date {
    private String id;
    private Integer day;
    private Integer month;
    private Integer year;
}
