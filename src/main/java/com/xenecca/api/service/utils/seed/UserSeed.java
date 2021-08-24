package com.xenecca.api.service.utils.seed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class UserSeed {

    @Value("${admin.user.first-name}")
    private String firstName;
    @Value("${admin.user.last-name}")
    private String lastName;
    @Value("${admin.user.username}")
    private String username;
    @Value("${admin.user.email}")
    private String email;
    @Value("${admin.user.password}")
    private String password;

}
