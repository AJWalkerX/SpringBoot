package com.ajwalker.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Slf4j
public class XSecurityConfig {

    @Bean
    public JWTTokenFilter getJwtTokenFilter() {
        return new JWTTokenFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
         * Spring filter chain ile tüm isteklerin üzerinden geçtiği noktaya kontrol sağlamakta
         * bizde bu method'u kullanarak ilgili otomatik ayarları maniğüle edebiliriz.
         * Burada tüm istekler kontrol edilerek yetkilendirme, oturum açma gibi istekler yönetilir.
         *
         * 1-> public, herkese açık erişim kısıtlamasız alanlar yaratmak için, permitAll()
         * NOT : genişletilmiş adreslendirme için /** kullanılır
         * 2-> authenticated(), belli alanlara erişim için oturum açmış olmak yeterli.
         * 3-> daha kısıtlı ve kullanıcıya özel alanlara erişim için hasAuthority("ADMIN")
         *
         */

        http.authorizeHttpRequests(req -> {
            req
                    .requestMatchers(
                            "/swagger-ui/**", "/v3/api-docs/**",
                            "/v1/dev/user/register", "v1/dev/user/login"
                    ) //eşleşecek end-pointlerin tam path'i yada genişletilmiş şekli yazılır.
                    .permitAll() //public olarak erişime izin verir!
                    .requestMatchers("/admin/**").hasAuthority("ADMIN") //admin rolüne sahip olanlar
                    .anyRequest() //diğer tüm istekler
                    .authenticated(); //oturum açmış olma zorunululuğu sağlar!
        });
        http.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);

        /*
         * _csrf nedir ?
         * sunucu, istemci ile arasında olan iletişimi doğrulamak ve güvenli şekilde iletişimde
         * kalmak için ilk istek ile oturum açıldığında istemciye bir csrfId gönderir ve bununla
         * istekleri karşılar. Böylece belli bir end-point'lere gelen istekleri güvenli kalmasını sağlar.
         * RestApi sisteminde csrf'in kapalı kalması sağlanır, güvenli iletişim için JWT gibi token
         * sistemleri ile iletişimin güvenliği sağlanır
         */
        http.csrf(AbstractHttpConfigurer::disable);
        http.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
