package com.stromwise.skilltree.contact;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/skill-tree/v1/contact")
@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

  //  @ApiOperation(value = "Send email to company's address")
   // @ApiResponse(code = 200, message = "Successful sending email")
    @PostMapping(value = "/request")
    public void sendEmailContactRequest(@Valid ContactPojo email) {
        contactService.sendEmail(email);
    }

}
