package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;


// BEGIN

// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }



    // BEGIN
    @Bean
    public Day day() {
        return new Day();
    }

    @Bean
    public Night night() {
        return new Night();
    }

    @Bean
    public Daytime daytime() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        if (hour >= 6 && hour <= 22) {
            return day();
        } else {
            return night();
        }
    }
    // END
}
