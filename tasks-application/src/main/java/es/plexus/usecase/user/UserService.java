package es.plexus.usecase.user;


import es.plexus.entity.user.User;
import es.plexus.exceptions.user.EmailUsedException;
import es.plexus.exceptions.user.UserNotFoundException;
import es.plexus.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("UserId: "+ id + " do not exist.");
        return userRepository.findById(id);
    }

    public User createUser(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        if (userRepository.findOneByEmail(user.getEmail()).isEmpty()){
            return userRepository.save(user);
        }
        throw new EmailUsedException("Email: "+ user.getEmail() + " is in use");
    }

    public void deleteUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("UserId: "+ id + " do not exist.");
        userRepository.deleteById(id);
    }

    public void updateUserById(int id, User userUpdated){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("UserId: "+ id + " do not exist.");
        if(! (user.get().getEmail().equals(userUpdated.getEmail()))){
            if (!userRepository.findOneByEmail(userUpdated.getEmail()).isEmpty())
                throw new EmailUsedException("Email: "+ userUpdated.getEmail() + " is in use");
        }
        userUpdated.setPassword(new BCryptPasswordEncoder().encode(userUpdated.getPassword()));
        userRepository.save(userUpdated);
    }
}
