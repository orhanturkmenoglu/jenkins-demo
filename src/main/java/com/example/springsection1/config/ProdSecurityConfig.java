package com.example.springsection1.config;

import com.example.springsection1.exceptionHandling.CustomAccessDeniedHandler;
import com.example.springsection1.exceptionHandling.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("prod")
public class ProdSecurityConfig {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // requestMatchers : hangi isteklerin korunacağını belirttik.
        http.requiresChannel(rcc -> rcc.anyRequest().requiresSecure()) // https kullanmak zorunda olacagımızı belirtiyoruz
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards")
                                .authenticated()
                                // bu isteklere de erişim verilecektir kullanıcı kimlik doğrulamasına bakılmaksızın.
                                .requestMatchers("/myNotices", "/myContact", "/register").permitAll()

                );
        // AbstractHttpConfigurer::disable : ile form tabanlı oturum açma işlemini kaldıracaktır.
        http.formLogin(withDefaults());
        // httpBasic (): yöntemi oturumu kullanıcı adı ve şifreyle açmak için kullaınır
        http.httpBasic(
                c -> c.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint())
        );

        // global düzeyde de tanımlanabilir
        // http.exceptionHandling(e -> e.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        // global üzeyde de tanımlanabilir
        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    /*  CompromisedPasswordChecker: Interface  yardımıyla
    HaveIBeenPwnedRestApiPasswordChecker : yardımıyla girilen şifreleri
    kontrol ederek daha güçlü bir şifre yazmamızı sağlar basit şifrelerden
    kaçınmamızı kontrol eder
    */
    // kendimize özel şifreleme yöntemide tanımlayabiliriz.
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }


}
