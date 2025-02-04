package shared.application;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import shared.domain.DomainEvent;
import shared.domain.EventPublisher;

import java.util.Set;

public class RabbitMqEventPublisher implements EventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqEventPublisher() {
        RabbitMQConfig config = new RabbitMQConfig();
        this.rabbitTemplate = config.rabbitTemplate();
    }

    @Override
    public void publish(DomainEvent event) {
        this.sendEvent(event);
    }
    @Override
    public void publish(Set<DomainEvent> events) {
        events.forEach(event ->
                sendEvent(event));
    }
    private void sendEvent(DomainEvent event) {
        this.rabbitTemplate.convertAndSend(event.type(), event);    }
}
