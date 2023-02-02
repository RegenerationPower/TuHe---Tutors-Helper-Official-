package Project.TuHe.validations;

import Project.TuHe.entities.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEntity.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserEntity userEntity = (UserEntity) target;
        if (userEntity.userWithSuchUsernameExist(userEntity.getUsername())) {
            errors.rejectValue("username", null, "This username already exists");
        }
    }
}
