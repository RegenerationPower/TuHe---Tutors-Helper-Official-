package Project.TuHe.controllers;

import Project.TuHe.entities.UserEntity;
import Project.TuHe.security.JwtTokenUtil;
import Project.TuHe.services.UserService;
import Project.TuHe.validations.UserValidation;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


@Controller
@Lazy
public class AuthorizationController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidation userValidation;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/signUp")
    public String register(@ModelAttribute("user") @Valid UserEntity user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registerPage";
        }

        // Set password
        String plainPassword = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(plainPassword);
        user.setPassword(encodedPassword);

        userService.registration(user);

        // Authenticate user
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        model.addAttribute("user", new UserEntity());
        return "loginPage";
    }

/*    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserEntity user) throws AuthenticationException {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(user.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }*/

    @InitBinder
    private void bindValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(userValidation);
    }
}
