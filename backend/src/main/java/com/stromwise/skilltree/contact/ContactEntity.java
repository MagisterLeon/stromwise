package com.stromwise.skilltree.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Table(name = "contact")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    @Email private String email;
    private Integer phone;
}
