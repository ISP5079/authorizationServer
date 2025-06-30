package com.isp.authorizationserver.config.security;

import com.isp.authorizationserver.domain.model.User;
import com.isp.authorizationserver.domain.model.UserRoleClient;
import com.isp.authorizationserver.domain.port.out.UserRepository;
import com.isp.authorizationserver.domain.port.out.UserRoleClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleClientRepository userRoleClientRepository;

    public CustomUserDetailsService(UserRepository userRepository, UserRoleClientRepository userRoleClientRepository) {
        this.userRepository = userRepository;
        this.userRoleClientRepository = userRoleClientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = ClientIdContextHolder.getClientId();
        User user = userRepository.findByEmailOrUsername(username);
        UserRoleClient userRoleClient = userRoleClientRepository.findByUserAndClientId(user, clientId);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPasswordHash())
                .roles(userRoleClient.getRoleClientAllowed().getIdRole().getName())
                .build();
    }

}
