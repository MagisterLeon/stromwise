package com.stromwise.skilltree.email;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/skill-tree/v1/email")
@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public void sendEmailContactRequest(@Valid EmailPojo email) {
        emailService.sendEmail(email);
    }

}
