package producer.mq;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MqListener {


    /**
     * 触发
     * {@link org.springframework.amqp.rabbit.core.RabbitAdmin#initialize}
     * 自动声明exchange,queue,binding
     */
    @RabbitListener(queues = MqConfig.JUST_FOR_INIT_AUTO_DECLARATION)
    public void listenJustForInitAutoDeclaration(Object message) {
    }

}
