package trackapp.icube04backend.common.messaging.publishers;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import trackapp.icube04backend.common.messaging.events.ClientCreatedEvent;
import trackapp.icube04backend.infrastructure.configuration.messaging.RabbitMQConfig;
import trackapp.icube04backend.modules.client_module.domain.models.Client;

@Component
@RequiredArgsConstructor
public class ClientEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishClientCreated(Client client) {
        ClientCreatedEvent evt = ClientCreatedEvent.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .age(client.getAge())
                .dateOfBirth(client.getDateOfBirth())
                .userId(client.getUserId())
                .companyId(client.getCompanyId())
                .build();

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_CLIENT,
                RabbitMQConfig.RK_CLIENT_CREATED,
                evt
        );
    }
}
