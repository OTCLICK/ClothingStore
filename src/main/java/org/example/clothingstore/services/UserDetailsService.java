package org.example.clothingstore.services;

import org.example.clothingstore.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {

    UserDetails loadUserByUsername(String username);

}
