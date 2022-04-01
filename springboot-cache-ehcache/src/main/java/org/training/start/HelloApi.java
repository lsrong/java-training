package org.training.start;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApi {
    @GetMapping("/hello")
    public String Hello(){
        return "Hello Spring Boot";
    }
}
