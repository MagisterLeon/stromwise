package com.stromwise.skilltree.contact;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/skill-tree/v1/contact")
@RequiredArgsConstructor
@RestController
public class ContactController {

    private final ContactService contactService;

    @ApiOperation(value = "Send email to company's address")
    @PostMapping(value = "/request")
    public void sendEmailContactRequest(@Valid ContactPojo email) {
        contactService.sendEmail(email);
    }

}
