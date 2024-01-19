package mauro.mrpg.service;

import mauro.mrpg.dto.UserProfileRequest;
import mauro.mrpg.model.User;
import mauro.mrpg.model.UserProfile;
import mauro.mrpg.repository.UserProfileRepository;
import mauro.mrpg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * Creates an UserProfile for a user.
     */
    public UserProfile createRegistry(UserProfileRequest request, Long idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        if (Objects.nonNull(user)) {
            user.getUserProfile().setName(request.getName());
            user.getUserProfile().setSurname(request.getSurname());
            user.getUserProfile().setBirthDate(request.getBirthDate());
            user.getUserProfile().setPhoneNumber(request.getPhoneNumber());
            userRepository.save(user);
            return user.getUserProfile();
        } else {
            throw new RuntimeException("User id not found.");
        }
    }

    public UserProfile getUserProfile(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id).orElse(null);
        if (Objects.nonNull(userProfile)) {
            return userProfile;
        } else {
            throw new RuntimeException("User profile not found.");
        }
    }
}
