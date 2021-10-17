package pl.edu.pjwstk.jaz;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppWebSecurityConfig {

    @Bean
    public FilterRegistrationBean<ExampleFilter> exampleFilter(UserSession userSession) {
        FilterRegistrationBean<ExampleFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new ExampleFilter(userSession));
        registrationBean.addUrlPatterns("/auth0/*");

        return registrationBean;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
