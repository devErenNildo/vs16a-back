package com.erenildo.fakebank.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private final freemarker.template.Configuration fmConfiguration;

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String de;

    public EmailService(Configuration fmConfiguration, JavaMailSender mailSender) {
        this.fmConfiguration = fmConfiguration;
        this.mailSender = mailSender;
    }

    public void sendEmailConfirmationToken(String nome, String token, String email) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(de);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("Codigo de confirmação");
            mimeMessageHelper.setText(geContentFromTemplate(nome, token), true);

            mailSender.send(mimeMessage);
        } catch (MessagingException | IOException | TemplateException e) {
            System.out.println("ERRO ao enviar e-mail!");
            e.printStackTrace();
        }
    }



    private String geContentFromTemplate(String nome, String token) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", nome);
        dados.put("token", token);
        dados.put("emailSuporte", de);

        Template template = fmConfiguration.getTemplate("email-token.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }
}
