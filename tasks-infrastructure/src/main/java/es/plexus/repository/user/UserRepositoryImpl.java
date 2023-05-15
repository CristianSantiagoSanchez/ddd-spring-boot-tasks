package es.plexus.repository.user;

import es.plexus.entity.user.User;
import es.plexus.jpa.user.UserJPA;
import es.plexus.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    UserJpaRepository userJpaRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return this.userMapper.toListEntity(userJpaRepository.findAll());
    }

    @Override
    public User findByEmail(String email) {
        Optional<UserJPA> userJPA = userJpaRepository.findByEmail(email);
        if (userJPA.isEmpty()){
            return null;
        }
        return this.userMapper.toEntity(userJPA.get());
    }

    @Override
    public User findByUsername(String username) {
        Optional<UserJPA> userJPA = userJpaRepository.findByUsername(username);
        if (userJPA.isEmpty()){
            return null;
        }
        return this.userMapper.toEntity(userJPA.get());
    }

    @Override
    public User findById(int id) {
        Optional<UserJPA> userJPA = userJpaRepository.findById(id);
        if (userJPA.isEmpty()){
            return null;
        }
        return this.userMapper.toEntity(userJPA.get());
    }

    @Override
    public User create(User user) {
        return this.userMapper.toEntity(userJpaRepository.save(this.userMapper.toJpa(user)));
    }

    @Override
    public User update(User user) {
        return this.userMapper.toEntity(userJpaRepository.save(this.userMapper.toJpa(user)));
    }

    @Override
    public void deleteById(int id) {
        userJpaRepository.deleteById(id);
    }
}
