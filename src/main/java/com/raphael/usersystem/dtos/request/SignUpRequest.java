package com.raphael.usersystem.dtos.request;

import com.raphael.usersystem.data.model.Address;
import com.raphael.usersystem.data.model.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToOne;
import javax.validation.constraints.*;


@Getter
@Setter
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "first name cannot be empty")
    private String firstName;

    @NotBlank(message = "last name cannot be empty")
    private String lastName;

    @NotBlank(message = "username cannot be empty")
    @Size(min = 8, message = "username cannot be less than 8 characters")
    @Size(max = 10, message = "username cannot be more than 10 characters")
    private String username;

    @NotBlank(message = "email cannot be empty")
    @Email(message = "invalid email address entered")
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "password can contain only characters and digits and size must be 5")
    private String password;

    @NotBlank(message = "phone number cannot be empty")
    @Pattern(regexp = "^(?:(?:(?:\\+?234(?:\\h1)?|01)\\h*)?(?:\\(\\d{3}\\)|\\d{3})|\\d{4})(?:\\W*\\d{3})?\\W*\\d{4}$\n",
            message = "invalid phone number entered")
    private String phoneNumber;

    @NotBlank(message = "address cannot be empty")
    @OneToOne
    private Address address;

    @NotBlank
    private String gender;

    @NotBlank(message = "nationality cannot be empty")
    private String nationality;

    @NotBlank(message = "date of birth cannot be empty")
    private Date dateOfBirth;

    @Min(18)
    @Max(60)
    private Integer age; // how to get age from date of birth




}
