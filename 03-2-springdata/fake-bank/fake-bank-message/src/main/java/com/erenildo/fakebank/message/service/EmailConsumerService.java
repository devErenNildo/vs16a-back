package com.erenildo.fakebank.message.service;


import com.erenildo.fakebank.message.dtos.EmailMessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailConsumerService {

    private final Configuration fmConfiguration;

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String de;

    @KafkaListener(topics = "send-email", groupId = "email-group")
    public void sendEmailConfirmationToken(String message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        EmailMessageDTO dto = objectMapper.readValue(message, EmailMessageDTO.class);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(de);
            mimeMessageHelper.setTo(dto.getDestino());
            mimeMessageHelper.setSubject("Codigo de confirmação");
            mimeMessageHelper.setText(getContentFromTemplate(dto.getNome(), dto.getCogigo()), true);

            mailSender.send(mimeMessage);
        } catch (MessagingException | IOException | TemplateException e) {
            System.out.println("ERRO ao enviar e-mail!");
            e.printStackTrace();
        }
    }

    private String getContentFromTemplate(String nome, String token) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", nome);
        dados.put("token", token);
        dados.put("emailSuporte", de);

        Template template = fmConfiguration.getTemplate("email-token.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }
}
