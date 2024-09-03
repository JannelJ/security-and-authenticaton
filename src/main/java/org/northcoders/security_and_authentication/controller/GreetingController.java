package org.northcoders.security_and_authentication.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1")
public class GreetingController {

    @GetMapping("/open/greeting")
    public String welcome(){
        return "Greetings";

    }
    @GetMapping("/protected/greeting")
    public String friendlyWelcome(){
        return "What's up!";
    }


}
