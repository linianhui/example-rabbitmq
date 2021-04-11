package producer.controller;

import common.LogAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "log")
public class LogController {

    @Autowired
    LogAdapter log;

    @GetMapping()
    public Object listLog() {
        return log.listLog();
    }

    @DeleteMapping()
    public void delete() {
        log.clear();
    }

}
