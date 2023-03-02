package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import project.model.Task;
import project.response.TaskResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final TaskService taskService;

    public EmailService(JavaMailSender javaMailSender, TaskService taskService) {
        this.javaMailSender = javaMailSender;
        this.taskService = taskService;
    }

    //    @Scheduled(cron = "0 45 15 * * MON-FRI")
    @Scheduled(cron = "0 20 11 * * MON-FRI")
    public void sendSimpleMessage() {
        List<String> collect = taskService.getAllUndoneTasks().stream()
                .map(TaskResponse::getTitleOfTask)
                .toList();

        StringBuilder stringBuilder = new StringBuilder();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("notificationToMikolaj@gmail.com");
        message.setTo("kacper.dufrat@gmail.com");
        message.setSubject("UNDONE TASKS");
        for (String c : collect) {
            stringBuilder.append("\n" + c);
        }
        message.setText("Undone tasks :" + stringBuilder);
        javaMailSender.send(message);
        System.out.println("mail send");
    }
}