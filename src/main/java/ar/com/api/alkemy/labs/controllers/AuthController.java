package ar.com.api.alkemy.labs.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.api.alkemy.labs.entities.User;
import ar.com.api.alkemy.labs.models.requests.ErrorResponse;
import ar.com.api.alkemy.labs.models.requests.LoginRequest;
import ar.com.api.alkemy.labs.models.requests.NewUserRequest;
import ar.com.api.alkemy.labs.models.responses.LoginResponse;
import ar.com.api.alkemy.labs.models.responses.RegistrationResponse;
import ar.com.api.alkemy.labs.security.jwt.JWTTokenUtil;
import ar.com.api.alkemy.labs.services.JWTUserDetailsService;
import ar.com.api.alkemy.labs.services.UserService;

@RestController
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    private JWTUserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<?> postRegisterUser(@RequestBody NewUserRequest req, BindingResult results) {
        RegistrationResponse r = new RegistrationResponse();

        if (results.hasErrors()) {
            r.isOk = false;
            r.message = "Registration error";
            return ResponseEntity.badRequest().body(r);
        }
        User user = userService.addUser(req.userType, req.name, req.lastName, req.dni);
        r.isOk = true;

        if (req.userType.getValue() == 1) {
            r.message = "your file is: " + user.getStudent().getFile() + " use it to login";
        } else {
            r.message = "your file is: " + user.getManager().getFile() + " use it to login";
        }
        r.userId = user.getUserId();
        return ResponseEntity.ok(r);

    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest,
            BindingResult results) throws Exception {
        if (results.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorResponse.FromBindingResults(results));
        }

        User userLoggedIn = userService.login(authenticationRequest.username, authenticationRequest.password);

        UserDetails userDetails = userService.getUserAsUserDetail(userLoggedIn);
        Map<String, Object> claims = userService.getUserClaims(userLoggedIn);
        String token = jwtTokenUtil.generateToken(userDetails, claims);

        LoginResponse r = new LoginResponse();
        r.id = userLoggedIn.getUserId();
        r.userType = userLoggedIn.getTypeUserId();
        r.entityId = userLoggedIn.getEntityId();
        r.username = authenticationRequest.username;
        r.token = token;

        return ResponseEntity.ok(r);

    }

}