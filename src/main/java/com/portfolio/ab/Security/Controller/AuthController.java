package com.portfolio.ab.Security.Controller;

import com.portfolio.ab.Security.Dto.JwtDto;
import com.portfolio.ab.Security.Dto.LoginUser;
import com.portfolio.ab.Security.Dto.NewUser;
import com.portfolio.ab.Security.Entity.Role;
import com.portfolio.ab.Security.Entity.User;
import com.portfolio.ab.Security.Enums.RoleName;
import com.portfolio.ab.Security.Service.RoleService;
import com.portfolio.ab.Security.Service.UserService;
import com.portfolio.ab.Security.jwt.JwtProvider;
import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/new")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Message("Verify data // email is not valid"), HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByUserName(newUser.getUserName())) {
            return new ResponseEntity(new Message("Username already exists"), HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(newUser.getEmail())) {
            return new ResponseEntity(new Message("Email address already exists"), HttpStatus.BAD_REQUEST);
        }

        User user = new User(newUser.getName(), newUser.getUserName(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());

        if (newUser.getRoles().contains("admin")) {
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        }

        user.setRoles(roles);
        userService.save(user);

        return new ResponseEntity(new Message("User was created successfully"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new ResponseEntity(new Message("Verify data"), HttpStatus.BAD_REQUEST);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUser.getUserName(), loginUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity(jwtDto, HttpStatus.OK);

    }

}
