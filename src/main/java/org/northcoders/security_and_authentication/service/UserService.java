package org.northcoders.security_and_authentication.service;

import org.northcoders.security_and_authentication.model.User;
import org.northcoders.security_and_authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    public UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        Map<String, Object> attributes = userRequest.getAdditionalParameters();
//        String id = (String) attributes.get("id");
//        String username = (String) attributes.get("login");
//        String fullName = (String) attributes.get("name");

        DefaultOAuth2UserService defaultOAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2UserToReturn = defaultOAuth2UserService.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2UserToReturn.getAttributes();

        String id = oAuth2UserToReturn.getName();
        String username = oAuth2UserToReturn.getAttribute("login");
        String fullName = oAuth2UserToReturn.getAttribute("name");

        Optional<User> foundUser = userRepository.findByGithubUsername(username);

        if (foundUser.isEmpty()) {
            User newUser = new User(Long.parseLong(id), username, fullName);
            userRepository.save(newUser);
        }

        return new DefaultOAuth2User(
                Collections.singleton(new OAuth2UserAuthority(attributes)),
                attributes,
                "id"
        );
    }


}
