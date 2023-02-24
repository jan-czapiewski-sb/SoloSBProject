package project.servicetests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import project.service.EmailService;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {
    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    void sendSimpleMessage_correct() {
        //given
        //when
        //then
    }
}
