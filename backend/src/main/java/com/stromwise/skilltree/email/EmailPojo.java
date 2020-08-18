package com.stromwise.skilltree.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailPojo {

    @NotEmpty(message = "Name may not be empty.")
    private String name;
    @NotEmpty(message = "Surname may not be empty.")
    private String surname;
    @Email(message = "Email should be valid.")
    private String email;
    @NotEmpty(message = "Phone may not be empty.")
    private String phone;
    @NotEmpty(message = "Message may not be empty.")
    private String message;
}
