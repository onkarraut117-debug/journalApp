package wannaBeDeveloper.journalApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import wannaBeDeveloper.journalApp.scheduler.UserScheduler;

@SpringBootTest
public class UserSchedularTest {
@Autowired
private UserScheduler userScheduler;

    @Test void testFetchUserAndSendMail(){
userScheduler.fetchUserAndSendMail();
    }
}
