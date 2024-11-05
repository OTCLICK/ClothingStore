package org.example.clothingstore.dto;

public class UserDTO {

    private String username;
    private String login;
    private String password;
    private String email;
    private String phone;

    public UserDTO(String username, String login, String password, String email, String phone) {
        setUsername(username);
        setLogin(login);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
