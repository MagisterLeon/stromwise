package com.stromwise.skilltree.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final JavaMailSender javaMailSender;

    @Value("${company.mail.address}")
    private String companyMailAddress;
    @Value("${company.new.request.subject}")
    private String companyNewRequestSubject;

    public void sendEmail(ContactPojo contactEmail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        setMimeMessageHelper(mimeMessage, contactEmail);

        javaMailSender.send(mimeMessage);
    }

    private void setMimeMessageHelper(MimeMessage mimeMessage, ContactPojo contactEmail) {
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(companyMailAddress);
            helper.setReplyTo(contactEmail.getEmail());
            helper.setFrom(contactEmail.getEmail());
            helper.setText(contactEmail.getMessage(), true);
            helper.setSubject(java.text.MessageFormat.format(
                    companyNewRequestSubject,
                    contactEmail.getName(),
                    contactEmail.getSurname(),
                    contactEmail.getPhone(),
                    contactEmail.getEmail())
            );

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
