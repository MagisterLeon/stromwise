package com.stromwise.skilltree.contact;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Value
public class ContactPojo {

    @NotEmpty(message = "Name may not be empty.")
    String name;
    @NotEmpty(message = "Surname may not be empty.")
    String surname;
    @Email(message = "Email should be valid.")
    String email;
    @NotNull(message = "Phone may not be empty.")
    int phone;
    @NotEmpty(message = "Message may not be empty.")
    String message;
}
