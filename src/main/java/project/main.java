package project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import project.service.EmailService;

@SpringBootApplication
public class main {
//@Autowired
//private EmailService emailService;
    public static void main(String[] args) {
        SpringApplication.run(main.class, args);
    }
//@EventListener(ApplicationReadyEvent.class)
//    public void send(){
//        emailService.sendSimpleMessage("dejdara99@onet.pl","sadsad","dasads");
//}
}
