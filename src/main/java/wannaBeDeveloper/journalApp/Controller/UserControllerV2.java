package wannaBeDeveloper.journalApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import wannaBeDeveloper.journalApp.apiResponse.WeatherResponse;
import wannaBeDeveloper.journalApp.Repository.UserRepository;
import wannaBeDeveloper.journalApp.Service.UserService;
import wannaBeDeveloper.journalApp.Service.WeatherService;
import wannaBeDeveloper.journalApp.entity.User;

@RestController
@RequestMapping("/user")

public class UserControllerV2 {
@Autowired private UserService userService;
@Autowired private UserRepository userRepository;
@Autowired private WeatherService weatherService;

@PutMapping
public ResponseEntity<?>updateUser(@RequestBody User user){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    User userInDb = userService.findByUserName(userName);
    if(userInDb!=null){
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
@DeleteMapping
public ResponseEntity<?>deleteUserById(){
    Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
    userRepository.deleteByUserName(authentication.getName());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

    @GetMapping
    public ResponseEntity<String> greeting(@RequestParam String city) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        WeatherResponse weatherResponse = weatherService.getWeather(city); // <-- FIXED
        String greeting;

        if (weatherResponse != null && weatherResponse.getCurrent() != null) {
            greeting = "Hi " + authentication.getName() +
                    ", weather in " +city +
                    " feels like " + weatherResponse.getCurrent().getTemperature() + "°C";
        } else {
            greeting = "Hi " + authentication.getName() + ", unable to fetch weather info for."+city;
        }

        return ResponseEntity.ok(greeting);
    }




}
//controller->service->repository
