package com.stromwise.skilltree.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Service
@Log4j2
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${company.mail.address}")
    private String companyMailAddress;
    @Value("${company.new.request.subject}")
    private String companyNewRequestSubject;

    public void sendEmail(EmailPojo email) {
        MimeMessage mail = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(companyMailAddress);
            helper.setReplyTo(email.getEmail());
            helper.setFrom(email.getEmail());
            helper.setFrom(email.getEmail());
            helper.setText(email.getMessage(), true);
            helper.setSubject(java.text.MessageFormat.format(
                    companyNewRequestSubject,
                    email.getName(),
                    email.getSurname(),
                    email.getPhone(),
                    email.getEmail())
            );

            javaMailSender.send(mail);

            log.info(java.text.MessageFormat.format(companyNewRequestSubject, email.getName(), email.getSurname(), email.getPhone(), email.getEmail()));
        } catch (MessagingException e) {
            log.info("Email has not been sent. Please check content of email.");
            log.debug(e.getMessage());
        }
    }
}
