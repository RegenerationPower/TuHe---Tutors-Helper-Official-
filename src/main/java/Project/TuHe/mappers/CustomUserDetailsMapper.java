package Project.TuHe.mappers;

import Project.TuHe.DTOs.UserDTO;
import Project.TuHe.details.CustomUserDetails;
import Project.TuHe.entities.UserEntity;

public class CustomUserDetailsMapper {

    public static CustomUserDetails fromUserDTO(UserDTO userDTO) {
        CustomUserDetails customUserDetails = new CustomUserDetails(new UserEntity());
        customUserDetails.setUsername(userDTO.getUsername());
        customUserDetails.setPassword(userDTO.getPassword());
        return customUserDetails;
    }

    public UserEntity toUserEntity(CustomUserDetails userDetails) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDetails.getId());
        userEntity.setEmail(userDetails.getEmail());
        userEntity.setUsername(userDetails.getUsername());
        userEntity.setPassword(userDetails.getPassword());
        return userEntity;
    }

}
