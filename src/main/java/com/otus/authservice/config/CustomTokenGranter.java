package com.otus.authservice.config;

import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

public class CustomTokenGranter extends AbstractTokenGranter {

    CustomTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
                       OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }

}
