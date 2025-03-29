package com.example.DarkWebM.Service;

import com.example.DarkWebM.Config.AuthRequest;
import com.example.DarkWebM.Config.AuthenticationResponse;
import com.example.DarkWebM.Config.JwtService;
import com.example.DarkWebM.Config.RegisterRequest;
import com.example.DarkWebM.Model.Role;
import com.example.DarkWebM.Model.User;
import com.example.DarkWebM.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user and generates a JWT token.
     */
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = createUser(registerRequest);
        userRepository.save(user);
        return generateAuthResponse(user);
    }

    /**
     * Authenticates a user and generates a JWT token.
     */
    public AuthenticationResponse login(AuthRequest authRequest) {
        var user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Manually verify password before authentication
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        return generateAuthResponse(user);
    }

    /**
     * Creates a new User entity.
     */
    private User createUser(RegisterRequest registerRequest) {
        return User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .phone(registerRequest.getPhone())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER) // Ensure role is always set
                .build();
    }

    /**
     * Generates an authentication response containing the JWT token.
     */
    private AuthenticationResponse generateAuthResponse(User user) {
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .id(user.getId())  // ✅ Include user ID in response
                .accessToken(jwtToken)
                .build();
    }

}
