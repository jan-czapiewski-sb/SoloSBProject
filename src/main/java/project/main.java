package project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class main {
    //@Autowired
//private EmailService emailService;
    public static void main(String[] args) {
        SpringApplication.run(main.class, args);
//        try {
//            for (int i = 0; i < 99999; i++) {
//
//                // it will sleep the main thread for 1 sec
//                // ,each time the for loop runs
//                Thread.sleep(1000);
//
//                // printing the value of the variable
//                System.out.println(i);
//            }
//        }
//        catch (Exception e) {
//
//            // catching the exception
//            System.out.println(e);
//        }
    }


//@EventListener(ApplicationReadyEvent.class)
//    public void send(){
//        emailService.sendSimpleMessage("dejdara99@onet.pl","sadsad","dasads");
//}
}
