package mauro.mrpg.service;

import mauro.mrpg.config.JwtService;
import mauro.mrpg.config.SecurityConfig;
import mauro.mrpg.dto.RegistrationRequest;
import mauro.mrpg.model.User;
import mauro.mrpg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    public User registerUser(RegistrationRequest request) {
        User user = new User();
        if (!userRepository.existsByUsername(request.getUsername())) {
            user.setUsername(request.getUsername());
            user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Username taken.");
        }
    }

    public String login(RegistrationRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("Wrong credentials."));
        if (new BCryptPasswordEncoder().matches(request.getPassword(), user.getPassword()))
            return jwtService.GenerateToken(user.getUsername());
        else
            throw new RuntimeException("Wrong credentials.");
    }
}
