package com.example.module15hw171;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
public class TestControler {

    @GetMapping("/test")
    public ModelAndView testMethod() {
        ModelAndView result = new ModelAndView("index");
        result.addObject("currentTime", LocalDateTime.now());
        return result;
    }

}
