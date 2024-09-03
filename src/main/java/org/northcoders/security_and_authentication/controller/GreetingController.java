package org.northcoders.security_and_authentication.controller;


import org.northcoders.security_and_authentication.repository.UserRepository;
import org.northcoders.security_and_authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("api/v1")
public class GreetingController {

    @GetMapping("/open/greeting")
    public String welcome() {
        return "Greetings";

    }

    @GetMapping("/protected/greeting")
    public String friendlyWelcome(@AuthenticationPrincipal OAuth2User user) {
        String id = user.getName();
        String username = user.getAttribute("login");
        String fullName = user.getAttribute("name");
        String firstName = fullName.split(" ")[0];

        return "Hi, " + StringUtils.capitalize(firstName) + "! What's up?";
    }


}
