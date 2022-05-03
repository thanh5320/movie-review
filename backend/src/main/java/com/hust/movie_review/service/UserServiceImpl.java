package com.hust.movie_review.service;

import com.hust.movie_review.config.exception.InValidException;
import com.hust.movie_review.config.security.JwtUtils;
import com.hust.movie_review.data.mapper.user.UserMapper;
import com.hust.movie_review.data.request.user.CreateUserRequest;
import com.hust.movie_review.data.response.user.LoginResponse;
import com.hust.movie_review.data.response.user.UserInfoResponse;
import com.hust.movie_review.dto.LoginRequest;
import com.hust.movie_review.models.Role;
import com.hust.movie_review.models.User;
import com.hust.movie_review.models.UserDetailsImpl;
import com.hust.movie_review.repositories.RoleRepository;
import com.hust.movie_review.repositories.UserRepository;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           RoleRepository roleRepository,
                           JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
    }

    @SneakyThrows
    @Override
    public UserInfoResponse createUser(CreateUserRequest request) {
//    TODO: check info email, username, password
        checkAlreadyUser(request);
        User newUser = userMapper.createUserToUser(request);
        Set<Role> roles = Optional.ofNullable(request.getRoles())
                .orElse(new HashSet<>())
                .stream()
                .map(item -> roleRepository.findByName(item))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        newUser.setRoles(roles);

        User userEntity = userRepository.save(newUser);
        UserInfoResponse response = userMapper.UserToUserInfo(userEntity);
        response.setRoles(request.getRoles());
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest request, AuthenticationManager authenticationManager) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);

        Set<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toSet());

        return new LoginResponse()
                .setId(userDetails.getUser().getId())
                .setUsername(userDetails.getUsername())
                .setToken(jwt)
                .setRoles(roles);
    }

    @SneakyThrows
    private void checkAlreadyUser(CreateUserRequest request) {
        User oldUSer;
        oldUSer = userRepository.findByUsername(request.getUsername());
        if (ObjectUtils.isNotEmpty(oldUSer)) {
            throw new InValidException("Username already exists");
        }
        oldUSer = userRepository.findByEmail(request.getEmail());
        if (request.getEmail()!=null && ObjectUtils.isNotEmpty(oldUSer)) {
            throw new InValidException("Email already exists");
        }
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return UserDetailsImpl.build(user);
    }


}