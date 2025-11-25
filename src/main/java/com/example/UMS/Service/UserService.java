package com.example.UMS.Service;

import com.example.UMS.Model.User;
import com.example.UMS.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void addUser(User user){
         userRepository.save(user);
    }

    public List<User> getAll(){return userRepository.findAll();}

    public boolean updateUser(Integer id,User userInfo){
        User user = userRepository.findUserById(id);
        if(user==null) return false;

        user.setName(userInfo.getName());
        user.setUsername(userInfo.getUsername());
        user.setEmail(userInfo.getEmail());
        user.setAge(userInfo.getAge());
        user.setRole(userInfo.getRole());
        user.setPassword(userInfo.getPassword());
        userRepository.save(user);
        return true;

    }


    public boolean deleteUser(Integer id){
        User user = userRepository.findUserById(id);
        if(user==null) return false;
        userRepository.delete(user);
        return true;
    }


    public String logIn(String[] info){
        try {
            String username =  info[0];
            String password = info[1];
            User user = userRepository.logIn(username, password);
            if (user == null) return "Username or Password wrong";
            return "log in successfully";
           }catch (ArrayIndexOutOfBoundsException e){
            return "log-in info is missing";
        }
        catch (Exception e){return e.getMessage();}
    }

    public User getByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public List<User> getByRole(String role){
        if(role.equalsIgnoreCase("user")||role.equalsIgnoreCase("admin")){
            return userRepository.findUserByRole(role);
        }
        return null;
    }

    public List<User> getByAge(Integer range){

        return userRepository.getUsersByAge(range);
    }

}
