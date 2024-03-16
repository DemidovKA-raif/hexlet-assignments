package exercise.controller;


import exercise.daytime.Day;
import exercise.daytime.Daytime;
import exercise.daytime.Night;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
@RestController
@RequestMapping()
public class WelcomeController {
    @Autowired
    private Daytime daytime;

    @Autowired
    private Day day;

    @Autowired
    private Night night;

    @GetMapping("/welcome")
    public String welcome() {
        String daytimeName = daytime.getName();
        if (daytimeName.equals("day")) {
            day.beanCreated();
            return "It is day time now! Welcome to Spring!";
        } else {
            night.beanCreated();
            return "It is night now! Welcome to Spring!";
        }
    }
}


// END
