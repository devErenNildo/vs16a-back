package com.erenildo.muitaconta.messages.service;

import com.erenildo.muitaconta.messages.dtos.EmailTokenDTO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final Configuration fmConfiguration;

    @Value("${spring.mail.username}")
    private String de;

    public EmailService(
            JavaMailSender mailSender,
            Configuration fmConfiguration
    ) {
        this.mailSender = mailSender;
        this.fmConfiguration = fmConfiguration;
    }

    public void enviarTokenConfirmacao(EmailTokenDTO dto) {

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
