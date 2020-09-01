package com.stromwise.skilltree.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contact")
@Builder
@Entity
@Data
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    @Email
    private String email;
    @Column(name = "phone")
    private Integer phone;
}
