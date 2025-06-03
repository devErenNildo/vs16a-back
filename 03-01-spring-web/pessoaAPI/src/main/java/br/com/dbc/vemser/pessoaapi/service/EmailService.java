package br.com.dbc.vemser.pessoaapi.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class EmailService {

    private final freemarker.template.Configuration fmConfiguration;

    //@Value("${mail.pessoa.criar}")
    private String pessoaCriada;

    @Value("${spring.mail.username}")
    private String de;

//    @Value("${spring.mail.username}")
//    private String para;

    private final JavaMailSender emailSender;

//    private final Configuration freemarkerConfig;

    public void enviarEmailBoasVindas(String para, String nome, Integer id) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(de);
            mimeMessageHelper.setTo(para);
            mimeMessageHelper.setSubject("Assunto");
            mimeMessageHelper.setText(geContentFromTemplate(nome, id, para), true);

            emailSender.send(mimeMessage);
        } catch (IOException | TemplateException | MessagingException e) {
            System.out.println("ERRO ao enviar e-mail!");
            e.printStackTrace();
        }
    }

    public String geContentFromTemplate(String nome, Integer id, String para) throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        model.put("nome", nome);
        model.put("id", id);
        model.put("emailSuporte", "emailSuporte@Teste.com");


        Template template = fmConfiguration.getTemplate("email-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        return html;
    }

//    public void sendSimpleMessage() {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(de);
//        message.setTo(para);
//        message.setSubject("Assunto");
//        message.setText("Teste - minha mensagem! \n\nAtt,\nSistema.");
//        emailSender.send(message);
//    }
//
//    public void sendWithAttachment() throws MessagingException {
//        MimeMessage message = emailSender.createMimeMessage();
//
//        MimeMessageHelper helper = new MimeMessageHelper(message,
//                true);
//
//        helper.setFrom(de);
//        helper.setTo(para);
//        helper.setSubject("E-Mail com anexo");
//        helper.setText("Teste - minha mensagem coom anexo. \n\nAtt,\nSistema.");
//
//        File file1 = new File("imagem-t.jpg");
//        FileSystemResource file
//                = new FileSystemResource(file1);
//        helper.addAttachment(file1.getName(), file);
//
//        System.out.println("ARQUIVO-1: " +file1.getPath()
//                + " | " + file1.getName());
//
//        System.out.println("ARQUIVO: " +file.getPath()
//                + " | " + file.getFilename());
//
//        emailSender.send(message);
//    }

//    public void sendEmail() {
//        MimeMessage mimeMessage = emailSender.createMimeMessage();
//        try {
//
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//
//            mimeMessageHelper.setFrom(de);
//            mimeMessageHelper.setTo(para);
//            mimeMessageHelper.setSubject("Assunto");
//            mimeMessageHelper.setText(geContentFromTemplate(), true);
//
//            emailSender.send(mimeMessage);
//        } catch (MessagingException | IOException | TemplateException e) {
//            System.out.println("ERRO ao enviar e-mail!");
//            e.printStackTrace();
//        }
//    }


}
