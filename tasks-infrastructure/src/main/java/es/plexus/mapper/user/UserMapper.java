package es.plexus.mapper.user;

import es.plexus.entity.user.User;
import es.plexus.jpa.user.UserJPA;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserJPA toJpa(User user);
    User toEntity(UserJPA userJpa);
    List<UserJPA> toListJpa(List<User> users);
    List<User> toListEntity(List<UserJPA> userJpas);
}
