package es.plexus.repository.user;


import es.plexus.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {


    public List<User> findAll();

    public User findByEmail(String email);

    public User findByUsername(String username);

    public User findById(int id);

    public User create(User user);
    public User update(User user);
    public void deleteById(int id);
}


