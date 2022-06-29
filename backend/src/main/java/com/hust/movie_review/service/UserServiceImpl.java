package com.hust.movie_review.service;

import com.hust.movie_review.config.exception.ApiException;
import com.hust.movie_review.config.exception.InValidException;
import com.hust.movie_review.config.security.JwtUtils;
import com.hust.movie_review.data.request.LoginRequest;
import com.hust.movie_review.data.request.user.CreateUserRequest;
import com.hust.movie_review.data.request.user.UpdateUserRequest;
import com.hust.movie_review.data.response.user.LoginResponse;
import com.hust.movie_review.data.response.user.UserInfoResponse;
import com.hust.movie_review.models.Role;
import com.hust.movie_review.models.User;
import com.hust.movie_review.models.UserDetailsImpl;
import com.hust.movie_review.repositories.ICommentRepository;
import com.hust.movie_review.repositories.IReviewRepository;
import com.hust.movie_review.repositories.IRoleRepository;
import com.hust.movie_review.repositories.IUserRepository;
import com.hust.movie_review.service.template.IUserService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseService<User, Integer> implements IUserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    private final ICommentRepository commentRepository;

    private final IReviewRepository reviewRepository;
    public UserServiceImpl(IUserRepository userRepository,
                           IRoleRepository roleRepository,
                           JwtUtils jwtUtils,
                           PasswordEncoder passwordEncoder,
                           ICommentRepository commentRepository,
                           IReviewRepository reviewRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.commentRepository = commentRepository;
        this.reviewRepository = reviewRepository;
    }

    @SneakyThrows
    @Override
    public UserInfoResponse createUser(CreateUserRequest request) {
        checkAlreadyUser(request);

        // set property for new user
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setStatus(true);
        newUser.setEmail(request.getEmail());

        // get role object from role name string and assign for new user
        Set<Role> roles = Optional.ofNullable(request.getRoles())
                .orElse(new HashSet<>())
                .stream()
                .map(roleRepository::findByName)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        newUser.setRoles(roles);

        // save new user
        User userEntity = userRepository.save(newUser);

        // set response
        UserInfoResponse response = new UserInfoResponse();
        response.setId(userEntity.getId());
        response.setEmail(userEntity.getEmail());
        response.setUsername(userEntity.getUsername());
        response.setFulName(userEntity.getFullName());
        response.setStatus(true);
        response.setRoles(request.getRoles());

        return response;
    }

    @Override
    public LoginResponse login(LoginRequest request, AuthenticationManager authenticationManager) {
        // validate username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        // set token
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);

        // get role of user
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority().split("_")[1])
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

    @SneakyThrows
    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return UserDetailsImpl.build(user);
    }

    public User getCurrentUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @SneakyThrows
    @Override
    public UserInfoResponse updateUser(UpdateUserRequest request) {
        Optional<User> userOptional = userRepository.findById(request.getId());
        if (!userOptional.isPresent()) {
            throw new ApiException("user is not exist!");
        }
        User  user = userOptional.get();

        if(ObjectUtils.isNotEmpty(request.getUsername())){
            User userByUserName = userRepository.findByUsername(request.getUsername());
            if (ObjectUtils.isNotEmpty(userByUserName)) throw new ApiException("username already exists");
            user.setUsername(request.getUsername());
        }

        if(ObjectUtils.isNotEmpty(request.getPassword()))
            user.setPassword(passwordEncoder.encode(request.getPassword()));

        if(ObjectUtils.isNotEmpty(request.getEmail())){
            User userByEmail = userRepository.findByEmail(request.getEmail());
            if (ObjectUtils.isNotEmpty(userByEmail)) throw new ApiException("email already exists");
            user.setEmail(request.getEmail());
        }

        if(ObjectUtils.isNotEmpty(request.getFullName()))
            user.setFullName(request.getFullName());

        if(ObjectUtils.isNotEmpty(request.getPhoneNumber()))
            user.setPhoneNumber(request.getPhoneNumber());

        userRepository.save(user);

        // set response
        UserInfoResponse response = new UserInfoResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setFulName(user.getFullName());
        response.setStatus(true);
        response.setRoles(
                Optional.ofNullable(user.getRoles()).orElse(new HashSet<>())
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );
        response.setPhoneNumber(user.getPhoneNumber());

        return response;
    }

    @SneakyThrows
    @Override
    public String deleteUser(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()) throw new ApiException("user is not exist");
        User user = userOptional.get();
        Optional.ofNullable(user.getComments()).orElse(new HashSet<>())
                        .forEach(item->{
                            commentRepository.delete(item);
                        });
        Optional.ofNullable(user.getReviews()).orElse(new HashSet<>())
                .forEach(item->{
                    reviewRepository.delete(item);
                });
        userRepository.delete(userOptional.get());
        return "success";
    }

    public User loadUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            return null;
        }

        return user.get();
    }
}