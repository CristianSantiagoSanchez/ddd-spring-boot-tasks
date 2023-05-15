package es.plexus.usecase.user;


import es.plexus.entity.user.User;
import es.plexus.exceptions.user.EmailUsedException;
import es.plexus.exceptions.user.UserNotFoundException;
import es.plexus.exceptions.user.UsernameUsedException;
import es.plexus.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UpdateUserByIdUseCase {

    @Autowired
    private UserRepository userRepository;

    public void updateUserById(int id, User userUpdated){
        User user = userRepository.findById(id);
        if (user == null)
            throw new UserNotFoundException("UserId: "+ id + " do not exist.");
        if(! (user.getEmail().equals(userUpdated.getEmail()))){
            if (userRepository.findByEmail(userUpdated.getEmail()) != null)
                throw new EmailUsedException("Email: "+ userUpdated.getEmail() + " is in use");
        }
        if(!(user.getUsername().equals(userUpdated.getUsername()))){
            if (userRepository.findByUsername(userUpdated.getUsername()) != null){
                throw new UsernameUsedException("Username " + userUpdated.getUsername() + " is in use");
            }
        }
        userUpdated.setPassword(new BCryptPasswordEncoder().encode(userUpdated.getPassword()));
        userRepository.update(userUpdated);
    }
}
