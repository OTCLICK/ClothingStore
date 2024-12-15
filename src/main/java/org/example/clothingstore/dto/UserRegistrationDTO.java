package org.example.clothingstore.dto;

import jakarta.validation.constraints.*;

public class UserRegistrationDTO {

    private String username;

    private String fullname;

    private String email;

    private int age;

    private String password;

    private String confirmPassword;

    public UserRegistrationDTO() {}

    @NotEmpty(message = "User name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotEmpty(message = "Full name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    @NotEmpty(message = "Email cannot be null or empty!")
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Min(0)
    @Max(90)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @NotEmpty(message = "Password cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @NotEmpty(message = "Confirm Password cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "username='" + username + '\'' +
                ", fullName='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
//public class UserRegistrationDTO {
//
//
//    String username;
//
//
//    String login;
//
//
//    String password;
//
//
//    String email;
//
//
//    String phone;
//
//
//    String confirmPassword;
//
//public UserRegistrationDTO() {}
//
//    @NotBlank
//    @Size(min = 5, max = 20)
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @NotBlank
//    @Size(min = 5, max = 20)
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    @NotBlank
//    @Size(min = 8, max = 17)
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @NotBlank
//    @Email
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    @NotBlank
//    @Pattern(regexp = "^(\\+7|8)?\\d{10}$")
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    @NotBlank
//    @Size(min = 8, max = 17)
//    public String getConfirmPassword() {
//        return confirmPassword;
//    }
//
//    public void setConfirmPassword(String confirmPassword) {
//        this.confirmPassword = confirmPassword;
//    }
//}
