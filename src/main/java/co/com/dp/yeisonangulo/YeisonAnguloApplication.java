package co.com.dp.yeisonangulo;

import java.time.Clock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class YeisonAnguloApplication {

    public static void main(String[] args) {
        SpringApplication.run(YeisonAnguloApplication.class, args);
    }

    @Bean
    public Clock systemClock() {
        return Clock.systemUTC();
    }
}
