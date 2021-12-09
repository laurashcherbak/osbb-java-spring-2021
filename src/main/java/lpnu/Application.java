/*

*/


package lpnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

    @RequestMapping("/test")
    String test() {
        return "Server is working!!!";
    }

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


