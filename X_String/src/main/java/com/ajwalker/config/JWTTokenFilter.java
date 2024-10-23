package com.ajwalker.config;

import com.ajwalker.utility.JwtManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class JWTTokenFilter extends OncePerRequestFilter {

    @Autowired // ilgili değişken için nesene(bean) yaratmak için kullanırız.
    private JwtManager jwtManager;
    @Autowired
    private JwtUserDetails jwtUserDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      /*
      * Bu kısım tüm isteklerin üzerinden geçtiği kısım. Burada isteklerin içersinde
      * bulunan TOKEN - JWT bilgisini okuyup, doğrulamasını ve kişinin kimliğini tespit
      * ederek oturum açmasını sağlayacağız.
      */
      final String authorizationHeader = request.getHeader("Authorization");
      if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
          String token = authorizationHeader.substring(7);
          Optional<Long> userId = jwtManager.validateToken(token);
          if (userId.isPresent()) {
              UserDetails userDetails = jwtUserDetails.getUserById(userId.get());
              //Spring bizim kimliğimizi doğrulayabilceği kendi içerisinde yetkileri yönetebileceği token
              UsernamePasswordAuthenticationToken authenticationToken
                      = new UsernamePasswordAuthenticationToken(null, null, new ArrayList<>());
              // geçerli güvenlik çemberinin içerisinde oturum açan kullanıcıya ait token bilgisini geçtiğimiz kısım
              SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          }
      }
      filterChain.doFilter(request, response);
    }
}
