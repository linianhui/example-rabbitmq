package producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import producer.mq.MqSender;

@RestController
@RequestMapping(path = "mq")
public class MqController {

    @Autowired
    MqSender mqSender;

    @PostMapping(path = "direct")
    public void direct(
        @RequestBody Object message
    ) {
        mqSender.directSend(message);
    }
}
