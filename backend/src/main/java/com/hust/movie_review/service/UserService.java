package com.hust.movie_review.service;

import com.hust.movie_review.models.CustomUserDetails;
import com.hust.movie_review.models.User;
import com.hust.movie_review.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = repository.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException(username);
        }

        return CustomUserDetails.build(optionalUser.get());
    }

    public UserDetails loadUserById(int id){
        Optional<User> optionalUser = repository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException(null);
        }

        return CustomUserDetails.build(optionalUser.get());
    }
}
