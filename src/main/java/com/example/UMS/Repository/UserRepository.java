package com.example.UMS.Repository;

import com.example.UMS.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserById(Integer id);
    User findUserByEmail(String email);
    List<User> findUserByRole(String role);
    @Query("SELECT ")
    List<User> getUsersByAge(Integer range);

    @Query("")
    User logIn(String username,String password);

}
