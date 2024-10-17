package com.ajwalker.spring_boot_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/proje")
public class SpringBoot1Application {

    @GetMapping("/mrb")
    public String merhaba() {
        return "merhaba";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot1Application.class, args);
    }

}
