package restapi.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import restapi.codingbat.entity.User;
import restapi.codingbat.payload.Result;
import restapi.codingbat.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public HttpEntity<?> getUsers(){
        List<User> users = userService.getUsersService();
        return ResponseEntity.status(!users.isEmpty()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(users);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getUser(@PathVariable Integer id){
        User user = userService.getUserService(id);
        return ResponseEntity.status(user!=null?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(user);
    }
    @PostMapping("/add")
    public HttpEntity<?> addUser(@RequestBody User user){
        Result result = userService.addUserService(user);
        return ResponseEntity.status(result.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(result);
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editUser(@PathVariable Integer id,@RequestBody User user){
        Result result = userService.editUserService(user, id);
        return ResponseEntity.status(result.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(result);
    }
    @DeleteMapping("/delete")
    public HttpEntity<Result> deleteUser(@PathVariable Integer id){
        Result result = userService.deleteUserService(id);
        return ResponseEntity.status(result.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(result);
    }
}
