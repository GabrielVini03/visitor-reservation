package visitorreservation.visitorreservationapi.controller.resources;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
@Log4j2
public class HelloWorldController {
    @GetMapping
    ResponseEntity<String> hello(){
        System.out.println("Hello World");
        log.info("Hello world");
        return ResponseEntity.ok("Hello World");
    }
}
