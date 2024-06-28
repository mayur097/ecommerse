package com.ecom.ecommerse.email;

import com.ecom.ecommerse.kafka.order.Products;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ecom.ecommerse.email.EmailTemplates.ORDER_CONFIRMATION;
import static com.ecom.ecommerse.email.EmailTemplates.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender ;

    private final SpringTemplateEngine templateEngine;

    @Async  //when you ricive notification dont want to block
    public void sentPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    )
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
            messageHelper.setFrom("contact@msg.com");
            final String templateName= PAYMENT_CONFIRMATION.getTemplate();

            Map<String,Object> variables=new HashMap<>();
            variables.put("customerName",customerName);
            variables.put("amount",amount);
            variables.put("orderReference",orderReference);

            Context context=new Context();
            context.setVariables(variables);
            messageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate,true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("email succesfully sent to {} with template {}",destinationEmail,htmlTemplate);
        } catch (MessagingException e) {
            log.error("warning cannot send email",destinationEmail);
        }
    }


    @Async  //when you ricive notification dont want to block
    public void sentOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Products> products
    )
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
            messageHelper.setFrom("contact@msg.com");
            final String templateName= ORDER_CONFIRMATION.getTemplate();

            Map<String,Object> variables=new HashMap<>();
            variables.put("customerName",customerName);
            variables.put("amount",amount);
            variables.put("orderReference",orderReference);
            variables.put("products",products);

            Context context=new Context();
            context.setVariables(variables);
            messageHelper.setSubject(ORDER_CONFIRMATION.getSubject());
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate,true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("email succesfully sent to {} with template {}",destinationEmail,htmlTemplate);
        } catch (MessagingException e) {
            log.error("warning cannot send email",destinationEmail);
        }
    }

}
