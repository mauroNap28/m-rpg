package mauro.mrpg.service;

import mauro.mrpg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
