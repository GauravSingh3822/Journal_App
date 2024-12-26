package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Resp.UserRepository;
import net.engineeringdigest.journalApp.api.response.WheatherResponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WeatherService weatherService;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
      User userIndb=  userService.findByUserName(userName);
      userIndb.setUserName(user.getUserName());
      userIndb.setPassword(user.getPassword());
      userService.saveNewUser(userIndb);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deletrUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WheatherResponse wheatherResponse= weatherService.getWeather("Mumbai");
        String greeting="";
        if(wheatherResponse!=null){
            greeting=",Wheather feels like"+weatherService.getWeather("Mumbai");
        }
        return new ResponseEntity<>("Hi "+ authentication.getName()+greeting,HttpStatus.OK);
    }



}
