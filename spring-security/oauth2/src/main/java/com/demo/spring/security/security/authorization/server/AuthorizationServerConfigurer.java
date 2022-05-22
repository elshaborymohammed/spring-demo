package com.demo.spring.security.security.authorization.server;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

//curl -v -XPOST -u client:secret http://localhost:8080/oauth/token?grant_type=password&username=john&password=12345&scope=read
//http  -a client:secret --form POST :8080/oauth/token?grant_type=password&username=john&password=12345&scope=read
//http  -a client:secret --form POST :8080/oauth/token?grant_type=password&username=admin&password=12345678&scope=read
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;

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
//        clients.withClientDetails(clientDetailsService);

        clients.inMemory()
                .withClient("client")
                .secret("secret")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.checkTokenAccess("isAuthenticated()");
    }
}
