package es.plexus.repository.task;

import es.plexus.jpa.task.TaskJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskJPA, Integer> {


}
