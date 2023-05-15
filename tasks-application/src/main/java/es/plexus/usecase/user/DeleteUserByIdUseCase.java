package es.plexus.usecase.user;

import es.plexus.entity.user.User;
import es.plexus.exceptions.user.UserNotFoundException;
import es.plexus.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteUserByIdUseCase {
    @Autowired
    private UserRepository userRepository;
    public void deleteUserById(int id){
        User user = userRepository.findById(id);
        if (user == null)
            throw new UserNotFoundException("UserId: "+ id + " do not exist.");
        userRepository.deleteById(id);
    }
}
