package com.stromwise.skilltree.contact;

public class ContactConverter {

    public ContactEntity transform(ContactPojo contactPojo){
        return ContactEntity.builder()
                .name(contactPojo.getName())
                .surname(contactPojo.getSurname())
                .email(contactPojo.getEmail())
                .phone(contactPojo.getPhone())
                .build();
    }
}
