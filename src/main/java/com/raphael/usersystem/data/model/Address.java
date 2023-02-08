package com.raphael.usersystem.data.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String id;
    private String houseNumber;
    private String street;
    private String landmark;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
