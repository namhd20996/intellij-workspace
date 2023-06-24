package com.example.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

@Controller
public class HomeController {

    @Autowired
    private ServletContext context;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/home-page")
    public String homePage() {
        return "home";
    }

    @PostMapping("/home-page")
    public String doPostPage(HttpServletRequest req,
                             @RequestParam("attachment") MultipartFile multipartFile) throws MessagingException, UnsupportedEncodingException, FileNotFoundException, IOException {
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String subject = req.getParameter("subject");
        String content = req.getParameter("content");

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String mailSubject = fullname + " has sent a message";
        String mailContent = "<p><b>Sender Name:<b> " + email + "<p>";
        mailContent += "<p><b>Subject:<b> " + subject + "<p>";
        mailContent += "<p><b>Content:<b> " + content + "<p>";
        mailContent += "<hr><img src='cid:logoImage'/>";

        helper.setFrom("hoangduynam20996@gmail.com", "Mail Test");
        helper.setTo("namhdps25979@fpt.edu.vn");
        helper.setSubject(mailSubject);
        helper.setText(mailContent, true);

        ClassPathResource resource = new ClassPathResource("/templates/images/product.png");
        helper.addInline("logoImage", resource);

//        String updloadDir = context.getRealPath("uploads");
//        File fileDir = new File(updloadDir);
//        if (!fileDir.exists()) {
//            fileDir.mkdirs();
//        }
//        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        File servletFile = new File(fileDir + File.separator + filename);
//        System.out.println(servletFile.toString());
//        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(servletFile));
//        bufferedOutputStream.write(multipartFile.getBytes());
//        bufferedOutputStream.close();

        if (!multipartFile.isEmpty()) {
            String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            InputStreamSource source = new InputStreamSource() {
                @Override
                public InputStream getInputStream() throws IOException {
                    return multipartFile.getInputStream();
                }
            };
            helper.addAttachment(filename, source);
        }
        mailSender.send(message);
        return "success";
    }
}
