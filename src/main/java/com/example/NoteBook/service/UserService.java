package com.example.NoteBook.service;

import com.example.NoteBook.domain.Role;
import com.example.NoteBook.domain.User;
import com.example.NoteBook.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Denis Karandashev
 * @project NoteBook
 * @date 24.05.2020
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }

//    public void updateProfile(User user, String password, String coAuthor) {
//        if (!StringUtils.isEmpty(password)){
//            user.setPassword(password);
//        }
//        if (!StringUtils.isEmpty(coAuthor)){
//            user.setCoAuthor(coAuthor);
//        }
//
//        userRepo.save(user);
//    }

    public void updateProfile(User user, String coauthor) {
        if (!StringUtils.isEmpty(coauthor)){
            user.setCoauthor(coauthor);
        }
        userRepo.save(user);
    }
}
