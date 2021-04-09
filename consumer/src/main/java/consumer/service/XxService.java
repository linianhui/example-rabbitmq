package consumer.service;

import org.springframework.stereotype.Service;

@Service
public class XxService {

    public void handlerXx(XxInput xxInput) {
        System.out.println(xxInput.getName());
    }
}
