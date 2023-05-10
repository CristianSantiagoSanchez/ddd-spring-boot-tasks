package es.plexus.repository.user;


import es.plexus.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    public Optional<User> findOneByEmail(String email);
    public List<User> findAll();

    public Optional<User> findById(int id);

    public User save(User user);

    public void deleteById(int id);

    public User findOneByUsername(String username);
}
