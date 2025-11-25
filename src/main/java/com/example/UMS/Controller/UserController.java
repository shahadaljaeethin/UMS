package com.example.UMS.Controller;

import com.example.UMS.Api.ApiResponse;
import com.example.UMS.Model.User;
import com.example.UMS.Service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/ums/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getAll(){return ResponseEntity.status(200).body(userService.getAll());}
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse(user.getUsername()+" added to the system"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody @Valid User user,Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if(userService.updateUser(id,user))
            return ResponseEntity.status(200).body(new ApiResponse("user updated"));
        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
    if(userService.deleteUser(id))
        return ResponseEntity.status(200).body(new ApiResponse("user deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }
    @GetMapping("/log in")
    public ResponseEntity<?> logIn(@RequestBody String[] info){
        String message = userService.logIn(info);
        if(message.equalsIgnoreCase("log in successfully"))
            return ResponseEntity.status(200).body(new ApiResponse(message));
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email){
        if(userService.getByEmail(email)==null) return ResponseEntity.status(400).body(new ApiResponse("no user has this email"));
        return  ResponseEntity.status(200).body(userService.getByEmail(email));
    }
    @GetMapping("/role/{role}")
    public ResponseEntity<?> getByRole(@PathVariable String role)
    {
    if(userService.getByRole(role)==null) return ResponseEntity.status(400).body(new ApiResponse("invalid role"));
    if(userService.getByRole(role).isEmpty()) return ResponseEntity.status(400).body(new ApiResponse("no user with this role yet"));
        return ResponseEntity.status(200).body(userService.getByRole(role));

    }
    @GetMapping("/age/{range}")
    public ResponseEntity<?> getByAge(@PathVariable Integer range){
        if(userService.getByAge(range).isEmpty()) return ResponseEntity.status(400).body(new ApiResponse("no user with this age range"));
        return ResponseEntity.status(200).body(userService.getByAge(range));
    }


}
