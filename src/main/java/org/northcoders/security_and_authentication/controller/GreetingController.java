package org.northcoders.security_and_authentication.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("api/v1")
public class GreetingController {

    @GetMapping("/open/greeting")
    public String welcome(){
        return "Greetings";

    }
    @GetMapping("/protected/greeting")
    public String friendlyWelcome(@AuthenticationPrincipal OAuth2User user){
        Optional<String> username = user.getAttributes().entrySet().stream()
                .filter(stringObjectEntry -> stringObjectEntry.getKey().equals("login"))
                .map(stringObjectEntry -> (String) stringObjectEntry.getValue())
                .findFirst();


        return "Hi, " + username.get() + "! What's up?";
    }


}
