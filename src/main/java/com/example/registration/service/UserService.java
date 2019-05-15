package com.example.registration.service;

import com.example.registration.email.CustomEmailComponent;
import com.example.registration.entity.ActivationToken;
import com.example.registration.entity.User;
import com.example.registration.exception.InvalidTokenException;
import com.example.registration.exception.UsernameExistsException;
import com.example.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private CustomEmailComponent customEmailComponent;
    private TemplateEngine templateEngine;

    @Autowired
    public UserService(UserRepository userRepository, CustomEmailComponent customEmailComponent, TemplateEngine templateEngine) {
        this.userRepository = userRepository;
        this.customEmailComponent = customEmailComponent;
        this.templateEngine = templateEngine;
    }

    public String registerUser (User user) {

        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        optionalUser.ifPresent(u -> {
            throw new UsernameExistsException("Username exists");
        });

        user.setActivationToken(new ActivationToken());
        userRepository.save(user);

        String activationToken = user.getActivationToken().getValue();

        Context context = new Context();
        context.setVariable("username", user.getUsername());
        context.setVariable("activation_token", activationToken);

        String body = templateEngine.process("registration-email-template", context);

        customEmailComponent.sendMessage(user.getEmail(), "token aktywacyjny", body);

        return activationToken;
    }

    public String activationUser (String token) {

        User user = userRepository.findByActivationToken_Value(token)
                .orElseThrow(() -> new InvalidTokenException("Invalid token"));

        if (user.isEnabled()) {
            return "user is already enabled";
        } else {
            user.setEnabled(true);
            userRepository.save(user);
            return "user enabled";
        }
    }

//    private void setUserToken (User user) {
//
//        String newGeneratedToken = UUID.randomUUID().toString().replace("-", "");
//
//        LocalDateTime dateTimeCreationToken = LocalDateTime.now();
//        ActivationToken token = new ActivationToken(newGeneratedToken, dateTimeCreationToken, dateTimeCreationToken.plusDays(2));
//        user.setActivationToken(token);
//        System.out.println(token.getValue());
//    }

}
