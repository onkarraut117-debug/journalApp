package wannaBeDeveloper.journalApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wannaBeDeveloper.journalApp.Service.UserService;
import wannaBeDeveloper.journalApp.entity.User;

import java.util.List;

@RestController
@RequestMapping ("/admin")
public class AdminController
{
    @Autowired private UserService userService;
@GetMapping("/allUsers") public ResponseEntity<Object> getAllUser(){
    List<User>all=userService.getAll();
    if (all!=null  && !all.isEmpty()){
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
@PostMapping("createAdminUser") public void createUser(@RequestBody User user){
    userService.saveAdmin(user);

}
}
