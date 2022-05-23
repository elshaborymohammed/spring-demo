package com.demo.spring.security.security.authorization.server;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

//http -a client:secret --form POST :8080/oauth/token grant_type=password username=admin password=12345678 scope=read
//http -a resourceserver:resourceserversecret --form POST :8080/oauth/check_token token=
//http :9090/hello Authorization:'bearer '
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        InMemoryClientDetailsService clientDetailsService = new InMemoryClientDetailsService();
//
//        BaseClientDetails clientDetails = new BaseClientDetails();
//        clientDetails.setClientId("client");
//        clientDetails.setClientSecret("secret");
//        clientDetails.setScope(List.of("read"));
//        clientDetails.setAuthorizedGrantTypes(List.of("password"));
//
//        clientDetailsService.setClientDetailsStore(Map.of("client", clientDetails));

        clients.inMemory()
                .withClient("client")
                .secret("secret")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read", "write")
                .and()
                .withClient("resourceserver")
                .secret("resourceserversecret");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()").passwordEncoder(passwordEncoder).checkTokenAccess("isAuthenticated()");
    }
}
