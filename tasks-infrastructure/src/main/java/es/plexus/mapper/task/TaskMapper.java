package es.plexus.mapper.task;

import es.plexus.entity.task.Task;
import es.plexus.entity.user.User;
import es.plexus.jpa.task.TaskJPA;
import es.plexus.jpa.user.UserJPA;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskJPA toJpa(Task task);
    Task toEntity(TaskJPA taskJPA);
    List<TaskJPA> toListJpa(List<Task> tasks);
    List<Task> toListEntity(List<TaskJPA> taskJPAS);
}
