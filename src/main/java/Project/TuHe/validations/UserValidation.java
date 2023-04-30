package Project.TuHe.validations;

import Project.TuHe.DTOs.UserDTO;
import Project.TuHe.entities.UserEntity;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidation implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return UserEntity.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target,@NonNull Errors errors) {
        UserEntity userEntity = (UserEntity) target;
        if (userEntity.userWithSuchUsernameExist(userEntity.getUsername())) {
            errors.rejectValue("username", "error.username", "This username already exists");
        }
        if (userEntity.userWithSuchEmailExist(userEntity.getEmail())) {
            errors.rejectValue("email", "error.email", "This email already exists");
        }
    }

    public void validate(UserDTO userDTO, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required.username", "Username is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Password is required");
    }
}

