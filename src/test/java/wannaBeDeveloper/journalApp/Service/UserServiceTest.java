package wannaBeDeveloper.journalApp.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wannaBeDeveloper.journalApp.Repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class UserServiceTest {
    @Autowired private UserRepository userRepository;

    @Autowired private UserService userService;
    @ParameterizedTest
    @ValueSource(strings = {
            "onkar",
            "shyam",

    })
    public void testFindByUserName(String name){

        assertNotNull(userRepository.findByUserName(name));
    }
}
