package com.example.UMS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

@NotEmpty(message = "enter name")
@Size(min = 5,max = 20,message = "name length must be from 5-20 character")
@Column(columnDefinition = "varchar(20) not null")
@Pattern(regexp = "^[A-Za-z]+$", message = "name must be in letters")
    private String name;

    @NotEmpty(message = "enter username")
    @Size(min = 5,max = 20,message = "username length must be from 5-20 character")
    @Column(columnDefinition = "varchar(20) not null unique")
    @Pattern(regexp = "^[A-Za-z0-9._-]+$", message = "username can have special character as . _ - only")
    private String username;

    @NotEmpty(message = "enter email")
    @Email(message = "enter valid email")
    @Size(max = 40, message = "email too long")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;

    @NotEmpty(message = "enter password")
    @Column(columnDefinition = "varchar(100) not null")
    private String password;
    @NotEmpty(message = "choose role")
    @Pattern(regexp = "^(?i)(user|admin)$",message = "roles are admin and user only")
    @Column(columnDefinition = "varchar(5)")
    private String role;
    @NotNull(message = "enter age")
    @Min(value = 16,message = "minimum is 16y.o")
    @Positive(message = "enter positive number")
    private Integer age;
}
