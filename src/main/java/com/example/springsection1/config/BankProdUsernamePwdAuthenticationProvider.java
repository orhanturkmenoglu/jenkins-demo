package com.example.springsection1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// kendi authentication providerımız olacak
@Component
@RequiredArgsConstructor
@Profile("prod")
public class BankProdUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       String username = authentication.getName();
       String password = authentication.getCredentials().toString();

       UserDetails userDetails = userDetailsService.loadUserByUsername(username);

       if(passwordEncoder.matches(password,userDetails.getPassword())){
           // bunun içeirisine uygulamaya özel şartlart ekleyebiliriz
           if(!userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
               throw  new BadCredentialsException("Invalid Role !");
           }
           return new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());
       }else {
           throw new BadCredentialsException("Invalid Password");
       }

    }

    // UsernamePasswordAuthenticationToken : destekliyeceği token türü.
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
