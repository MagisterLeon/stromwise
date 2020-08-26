package com.stromwise.skilltree.contact;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@Value
public class ContactPojo {

    @NotEmpty(message = "Name may not be empty.")
    private String name;
    @NotEmpty(message = "Surname may not be empty.")
    private String surname;
    @Email(message = "Email should be valid.")
    private String email;
    @NotEmpty(message = "Phone may not be empty.")
    private int phone;
    @NotEmpty(message = "Message may not be empty.")
    private String message;
}
