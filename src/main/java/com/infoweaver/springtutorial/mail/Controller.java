package com.infoweaver.springtutorial.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ruobing Shang 2022-10-06 9:14
 */

@RestController
public class Controller {
    private final MailService mailService;

    @Autowired
    public Controller(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/send-simple-mail")
    public void sendSimpleMail() {
        mailService.sendSimpleMail(
                "1070221274@qq.com",
                "Spring-Boot-Mail-Test",
                "这是阿冰的自动邮件测试");
    }

    @GetMapping("/send-mime-mail")
    public void sendMimeMail() {
        mailService.sendMimeMail(
                "1070221274@qq.com",
                "Spring-Boot-Mail-Test",
                "<h1>相遇即是缘分</h1>" +
                        "<h2>这是阿冰的测试邮件</h2>"
        );
    }
}