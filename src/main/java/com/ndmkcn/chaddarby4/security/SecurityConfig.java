package com.ndmkcn.chaddarby4.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    // Burada ise veri tabanı desteği getirdik ve normal elle eklenmiş kullanıcılar artık yok
    // gerçek uygulamalarda nasıl olacaksa o şekilde olacak.

//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }

    // Burada ise örneğin kullandığımız spring tarafından standart tablolar değilde başka tablolarımız
    // var ve bunları biz birbirine bağlayım spring bootun bu tablolar üzerinden yetkilendirme işlemlerini
    // yapmasını istiyoruz işte bu durumlarda aşağıdaki kodu kullanıyoruz ki genel olarak böyle kullanım
    // en uygun yapıdır.
    // Yani kısaca burada yapmak istediğimiz şey Spring security configurasyonumzu güncellemek ve
    // kendimize ait özel tabloları kullanmak yani springin varsayılan tablolarının dışında
    // isimlendirilmiş ve kullanılan tablolardan bahsediyoruz.ve bunlar için uygun sql sorgularını vereceğiz.

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        // Bir kullanıcıyı kullanıcı adına göre almak için sorgu tanımladık.
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT user_id,pw,active FROM members WHERE user_id=?"
        );
        // Kullanıcı adına göre yetkileri/rolleri almak için sorgu tanımlayın
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT user_id,role FROM roles WHERE user_id=?"
        );
        return jdbcUserDetailsManager;
    }

    // Burada ise istek metoduna göre belirtilen yol için role verdik yani yetkilendirme yaptık.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                configurer ->configurer
                        .requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
        );
        // use HTTP basic auth
        http.httpBasic();
        // disable Cross Site request forgery(csrf)
        http.csrf().disable();
        return http.build();
    }
    // Burada kullanıcıları bu metod ile kendimiz oluşturduk veri tabanı yoktu.
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails selim= User.builder()
//                .username("selim")
//                .password("{noop}selim123.")
//                .roles("EMPLOYEE")
//                .build();
//        UserDetails mary= User.builder()
//                .username("mary")
//                .password("{noop}mary123.")
//                .roles("EMPLOYEE","MANAGER")
//                .build();
//        UserDetails susan= User.builder()
//                .username("susan")
//                .password("{noop}susan123.")
//                .roles("EMPLOYEE","MANAGER","ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(selim,mary,susan);
//    }
}
