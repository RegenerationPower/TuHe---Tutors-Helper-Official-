package Project.TuHe;

import Project.TuHe.controllers.AuthorizationController;
import Project.TuHe.entities.UserEntity;
import Project.TuHe.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AuthorizationControllerTest {

    @InjectMocks
    private AuthorizationController authorizationController;

    @Mock
    private UserService userService;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @Test
    public void testRegister_SuccessfulRegistration_RedirectToHomePage() {
        UserEntity user = new UserEntity();
        user.setEmail("test@example.com");
        user.setUsername("testuser");
        user.setPassword("password");

        when(bindingResult.hasErrors()).thenReturn(false);

        String result = authorizationController.register(user, bindingResult, model);

        assertEquals("redirect:/", result);
        verify(bindingResult, times(1)).hasErrors();
        verify(userService, times(1)).registration(user);
        verify(model, times(0)).addAttribute(eq("error"), anyString());
    }

    @Test
    public void testRegister_ValidationErrors_ReturnRegisterPage() {
        UserEntity user = new UserEntity();
        user.setEmail("test@example.com");
        user.setUsername("testuser");
        user.setPassword("password");

        when(bindingResult.hasErrors()).thenReturn(true);

        String result = authorizationController.register(user, bindingResult, model);

        assertEquals("registerPage", result);
        verify(bindingResult, times(1)).hasErrors();
        verify(userService, times(0)).registration(user);
        verify(model, times(0)).addAttribute(eq("error"), anyString());
    }

    @Test
    public void testLogin_WithoutErrorMessage_ReturnLoginPage() {
        String error = null;

        String result = authorizationController.login(error, model);

        assertEquals("loginPage", result);
        verify(model, times(1)).addAttribute(eq("user"), any(UserEntity.class));
        verify(model, times(0)).addAttribute(eq("error"), anyString());
    }


}
