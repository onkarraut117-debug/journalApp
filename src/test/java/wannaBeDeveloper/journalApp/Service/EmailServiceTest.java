package wannaBeDeveloper.journalApp.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class EmailServiceTest {

    @Autowired EmailService emailService;

    @Test void testSendEmail(){
        emailService.sendEmail("onkarraut0747@gmail.com","Testing Java Mail Sender","Ap kaise hai");
    }

}
