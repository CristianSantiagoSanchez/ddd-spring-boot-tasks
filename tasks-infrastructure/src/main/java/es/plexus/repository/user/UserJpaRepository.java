package es.plexus.repository.user;

import es.plexus.jpa.user.UserJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJPA, Integer> {

    public Optional<UserJPA> findByEmail(String email);
    public Optional<UserJPA> findByUsername(String username);
}
