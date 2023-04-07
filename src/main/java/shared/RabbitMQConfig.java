package shared;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

public class RabbitMQConfig {
    private final ConnectionFactory connectionFactory = this.connectionFactory();

    public RabbitMQConfig() {
    }
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("host.docker.internal:5672");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("mdas-api");
        return connectionFactory;
    }
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(this.connectionFactory);
        rabbitTemplate.setRoutingKey("domain-events");
        return rabbitTemplate;
    }
}

