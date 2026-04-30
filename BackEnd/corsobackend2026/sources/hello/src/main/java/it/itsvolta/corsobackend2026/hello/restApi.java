package it.itsvolta.corsobackend2026.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 @RestController
 @RequestMapping("/")
public class restApi {
    
    @GetMapping("/hello")
    public String getHello() {
        return "Hello, World!";
    }
}
