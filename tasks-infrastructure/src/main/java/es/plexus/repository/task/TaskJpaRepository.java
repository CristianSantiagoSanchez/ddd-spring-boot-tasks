package es.plexus.repository.task;

import es.plexus.jpa.task.TaskJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskJPA, Integer> {

    Optional<TaskJPA> findByIdAndUserId(Integer id, Integer userId);
    List<TaskJPA> findByUserId(Integer userId);
}
