package Project.TuHe.controllers;

import Project.TuHe.details.CustomUserDetails;
import Project.TuHe.entities.UserEntity;
import Project.TuHe.mappers.CustomUserDetailsMapper;
import Project.TuHe.security.JwtTokenUtil;
import Project.TuHe.services.UserService;
import Project.TuHe.validations.UserValidation;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
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

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final UserValidation userValidation;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final CustomUserDetails customUserDetails;
    private final CustomUserDetailsMapper customUserDetailsMapper;

    public AuthorizationController(JwtTokenUtil jwtTokenUtil, UserService userService, UserValidation userValidation, AuthenticationManager authenticationManager, ModelMapper modelMapper, CustomUserDetails customUserDetails, CustomUserDetailsMapper customUserDetailsMapper) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.userValidation = userValidation;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
        this.customUserDetails = customUserDetails;
        this.customUserDetailsMapper = customUserDetailsMapper;
    }

    @RequestMapping(value = "/signUp")
    public String register(@ModelAttribute("user") @Valid UserEntity user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registerPage";
        }

        String plainPassword = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(plainPassword);
        user.setPassword(encodedPassword);
        userService.registration(user);

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

    @GetMapping("/api/user")
    @ResponseBody
    public UserEntity getUserDetails(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return customUserDetailsMapper.toUserEntity(userDetails);
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
