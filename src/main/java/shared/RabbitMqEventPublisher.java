package shared;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Set;

public class RabbitMqEventPublisher implements EventPublisher{
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqEventPublisher() {

        this.rabbitTemplate = new RabbitMQConfig().rabbitTemplate();
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
        this.rabbitTemplate.convertAndSend("favourite_pokemon_added", event.type());
    }
}
