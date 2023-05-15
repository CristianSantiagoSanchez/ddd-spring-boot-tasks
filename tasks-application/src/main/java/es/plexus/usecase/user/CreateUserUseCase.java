package es.plexus.usecase.user;

import es.plexus.entity.user.User;
import es.plexus.exceptions.user.EmailUsedException;
import es.plexus.exceptions.user.UsernameUsedException;
import es.plexus.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;
    public User createUser(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        if (userRepository.findByEmail(user.getEmail()) != null){
            throw new EmailUsedException("Email: "+ user.getEmail() + " is in use");

        }
        if (userRepository.findByUsername(user.getUsername()) != null){
            throw new UsernameUsedException("Username " + user.getUsername() + " already exists");

        }

        return userRepository.create(user);
    }
}
