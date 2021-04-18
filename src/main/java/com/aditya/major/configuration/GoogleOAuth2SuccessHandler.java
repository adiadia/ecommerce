package com.aditya.major.configuration;

import com.aditya.major.model.Role;
import com.aditya.major.model.User;
import com.aditya.major.repository.RoleRepository;
import com.aditya.major.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String email = token.getPrincipal().getAttributes().get("email").toString();
        if(!userRepository.findUserByEmail(email).isPresent()){
            User user = new User();
            user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
            user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
            user.setEmail(email);
            List<Role> roleList = new ArrayList<>();
            roleList.add((Role) roleRepository.findById(2).get());
            user.setRole(roleList);
            userRepository.save(user);

        }
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse,"/");

    }
}
