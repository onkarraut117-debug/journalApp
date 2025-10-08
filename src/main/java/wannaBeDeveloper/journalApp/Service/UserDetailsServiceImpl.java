package wannaBeDeveloper.journalApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import wannaBeDeveloper.journalApp.Repository.UserRepository;
import wannaBeDeveloper.journalApp.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword()) // must be BCrypt encoded
                    .roles(user.getRoles().stream().map(r -> r.replace("ROLE_", "")).toArray(String[]::new))
                    .build();
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }
}
