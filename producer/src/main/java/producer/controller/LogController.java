package producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import producer.LogAdapter;

@RestController
@RequestMapping(path = "log")
public class LogController {

    @Autowired
    LogAdapter log;

    @GetMapping()
    public Object listLog() {
        return log.listLog();
    }

}
