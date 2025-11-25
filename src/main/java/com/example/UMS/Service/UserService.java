package com.example.UMS.Service;

import com.example.UMS.Model.User;
import com.example.UMS.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
