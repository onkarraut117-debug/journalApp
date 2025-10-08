package wannaBeDeveloper.journalApp.RepositoryImplTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wannaBeDeveloper.journalApp.Repository.UserRepositoryImpl;

@SpringBootTest  class UserRepositoryImplTest {
    @Autowired private UserRepositoryImpl userRepository;
    @Test  void testSaveNewUser(){
Assertions.assertNotNull(userRepository.getUserForSentimentAnalysis());
    }

}
