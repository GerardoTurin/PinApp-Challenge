package trackapp.icube04backend.common.messaging.listeners;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.common.messaging.events.ClientCreatedEvent;
import trackapp.icube04backend.infrastructure.configuration.messaging.RabbitMQConfig;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientCreatedEmailListener {

    private final JavaMailSender mailSender;
    private final MeterRegistry meterRegistry; // <-- NUEVO

    // Nombres de métricas
    private static final String METRIC_TIMER   = "pinapp.messaging.client_created.email.seconds";
    private static final String METRIC_COUNTER = "pinapp.messaging.client_created.email.processed";

    // Destinatario configurable (para Mailtrap cualquier dirección vale)
    @Value("${notifications.client.created.to:newClient@pinapp.com}")
    private String notifyTo;

    @Value("${spring.mail.from}")
    private String from;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_CLIENT_CREATED)
    public void onClientCreated(ClientCreatedEvent evt) {
        log.info("Recibido ClientCreatedEvent: {}", evt);

        // --- MÉTRICAS (inicio) ---
        Timer.Sample sample = Timer.start(meterRegistry);
        String outcome = "success";
        // --- MÉTRICAS (fin) ---

        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(from);
            msg.setTo(notifyTo);
            msg.setSubject("[PinApp] Nuevo cliente creado #" + evt.getId());
            msg.setText("""
                    Se ha creado un nuevo cliente:

                    Id: %d
                    Nombre: %s %s
                    Edad: %s
                    Fecha Nac.: %s
                    UserId: %s
                    CompanyId: %s

                    -- Este email fue generado automáticamente --
                    """.formatted(
                    evt.getId(),
                    nullSafe(evt.getFirstName()),
                    nullSafe(evt.getLastName()),
                    String.valueOf(evt.getAge()),
                    String.valueOf(evt.getDateOfBirth()),
                    String.valueOf(evt.getUserId()),
                    String.valueOf(evt.getCompanyId())
            ));

            mailSender.send(msg);
            log.info("Notificación de cliente creado enviada a {}", notifyTo);

        } catch (Exception ex) {
            outcome = "error";
            log.error("Error enviando email de nuevo cliente id={}", evt.getId(), ex);
            // Re-lanzamos para que Rabbit (según tu config de ack/retry) pueda reintentar
            throw ex;
        } finally {
            // Contador de procesados
            Counter.builder(METRIC_COUNTER)
                    .tag("queue", RabbitMQConfig.QUEUE_CLIENT_CREATED)
                    .tag("outcome", outcome) // success | error
                    .register(meterRegistry)
                    .increment();

            // Tiempo de proceso (con histograma para p95/p99)
            sample.stop(
                    Timer.builder(METRIC_TIMER)
                            .description("Tiempo para procesar y enviar email por cliente creado")
                            .tag("queue", RabbitMQConfig.QUEUE_CLIENT_CREATED)
                            .tag("outcome", outcome)
                            .publishPercentileHistogram()
                            .register(meterRegistry)
            );
        }
    }

    private String nullSafe(String s) { return s == null ? "" : s; }
}
