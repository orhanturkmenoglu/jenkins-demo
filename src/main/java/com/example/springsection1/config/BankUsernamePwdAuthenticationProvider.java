package com.example.springsection1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

// kendi authentication providerımız olacak
@Component
@RequiredArgsConstructor
@Profile("!prod")
public class BankUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       String username = authentication.getName();
       String password = authentication.getCredentials().toString();

       UserDetails userDetails = userDetailsService.loadUserByUsername(username);

       // şifre doğrulamadan direk giriş yapabiliriz
        return new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());

    }

    // UsernamePasswordAuthenticationToken : destekliyeceği token türü.
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
