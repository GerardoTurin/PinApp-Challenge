package trackapp.icube04backend.infrastructure.configuration.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    public static final String EXCHANGE_CLIENT = "client.events.exchange";
    public static final String QUEUE_CLIENT_CREATED = "client.created.queue";
    public static final String RK_CLIENT_CREATED = "client.created";

    @Bean
    public DirectExchange clientExchange() {
        return new DirectExchange(EXCHANGE_CLIENT);
    }

    @Bean
    public Queue clientCreatedQueue() {
        return QueueBuilder.durable(QUEUE_CLIENT_CREATED).build();
    }

    @Bean
    public Binding clientCreatedBinding() {
        return BindingBuilder.bind(clientCreatedQueue())
                .to(clientExchange())
                .with(RK_CLIENT_CREATED);
    }

    // Mensajes en JSON
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // RabbitTemplate con el converter
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf, Jackson2JsonMessageConverter conv) {
        RabbitTemplate rt = new RabbitTemplate(cf);
        rt.setMessageConverter(conv);
        return rt;
    }
}
