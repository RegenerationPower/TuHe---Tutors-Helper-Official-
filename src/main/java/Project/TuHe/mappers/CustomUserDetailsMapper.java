package Project.TuHe.mappers;

import Project.TuHe.details.CustomUserDetails;
import Project.TuHe.entities.UserEntity;

public class CustomUserDetailsMapper {

    public UserEntity toUserEntity(CustomUserDetails userDetails) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDetails.getId());
        userEntity.setEmail(userDetails.getEmail());
        userEntity.setUsername(userDetails.getUsername());
        userEntity.setPassword(userDetails.getPassword());
        return userEntity;
    }

}
