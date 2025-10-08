package wannaBeDeveloper.journalApp.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import wannaBeDeveloper.journalApp.Repository.UserRepository;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameTests() {
        // Mock repository
        when(userRepository.findByUserName(anyString()))
                .thenReturn(wannaBeDeveloper.journalApp.entity.User.builder()
                        .userName("onkar")
                        .password("fghffhj")
                        .roles(List.of("ROLE_USER"))
                        .build());

        // Call service
        UserDetails user = userDetailsService.loadUserByUsername("onkar");

        // Assertions
        Assertions.assertNotNull(user);
        Assertions.assertEquals("onkar", user.getUsername());
    }
}
