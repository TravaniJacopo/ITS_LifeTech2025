package it.itsvolta.corsobackend2026.studentmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.itsvolta.corsobackend2026.studentmanager.dto.DemoInitDTO;
import it.itsvolta.corsobackend2026.studentmanager.service.DemoInitService;

@RestController
@RequestMapping("/init/demo")
public class DemoInitController {
    
    private final DemoInitService demoInitService;


    public DemoInitController(DemoInitService demoInitService) {
        this.demoInitService = demoInitService;
    }

    @GetMapping("")
    public DemoInitDTO showDemo() 
    {
        return demoInitService.getDemo();
    }

    @PostMapping("")
    public void initializeDemo()
    {
        demoInitService.InizializeDemo();
    }

}
