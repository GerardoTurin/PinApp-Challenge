# icube04-backend
Backend Java Spring Boot para PinApp.
Incluye arquitectura hexagonal, mensajería con RabbitMQ, JPA (PostgreSQL), migraciones con Flyway, documentación Swagger, logging con Logback y Actuator + Micrometer/Prometheus para métricas.

Context-path de la app: /icube04


## Arquitectura y Patrones
### Patrón de Arquitectura

Hexagonal (Ports & Adapters):

modules.<mod>.domain → Dominio (entidades, lógica, ports).

modules.<mod>.application → Casos de uso (use cases); orquesta dominio.

infrastructure.adapters.<mod> → Adapters (entrata/salida: controllers REST, repositorios JPA, mensajería, etc.).

infrastructure.configuration → configuración transversal (seguridad, email, messaging, swagger, etc.).

### Patrones de diseño
Repository (puertos + adapters JPA).

DTO / Mapper (adaptación entre dominio e IO).

Factory/Builder (creación de respuestas/DTO).

Strategy (seguridad/filters, configuración).

Observer/Event-Driven (RabbitMQ para eventos de dominio).

Layered + Hexagonal blend (reglas ArchUnit para fronteras).


## Requisitos

Java 21

Maven 3.9+

Docker

Cuenta SMTP (Mailtrap) para notificaciones de email en eventos de cliente creado.

En application-local.properties cambiar las variables de Mailtrap por las propias.



## Pasos para Correr el proyecto

Clonar repositorio:

git clone https://github.com/GerardoTurin/PinApp-Challenge


## Crea Contenedores Docker

Posicionarse en dentro de la carpeta "db" que está en la raiz del proyecto.

Ejecutar : docker-compose up -d




## Correr proyecto

En la raiz del proyecto:

Ejecutar: mvn spring-boot:run

### Opcional: Limpiar y Correr:

mvn clean install

mvn spring-boot:run


### Mensajería (RabbitMQ + Spring AMQP)

El servicio publica un evento de “cliente creado”.

Un listener (ClientCreatedEmailListener) consume desde la cola y envía una notificación por email.

Cola de ejemplo: client.created.queue (ver RabbitMQConfig).

UI de RabbitMQ: http://localhost:15672 (username:guest/password:guest)



### Logs y Métricas

### Actuator

Health: GET /icube04/actuator/health

Métricas: GET /icube04/actuator/metrics

Export Prometheus: GET /icube04/actuator/prometheus


### Métricas personalizadas del listener

Se instrumentó el listener con Micrometer:

Contador de procesados:

pinapp.messaging.client_created.email.processed

Prometheus: pinapp_messaging_client_created_email_processed_total

Tags: queue, outcome (success|error)

Timer de envío de email:

pinapp.messaging.client_created.email.seconds

Prometheus (histograma):
pinapp_messaging_client_created_email_seconds_bucket|count|sum|max

Tags: queue, outcome


#### Cómo validar:

##### Línea base:

/icube04/actuator/metrics/pinapp.messaging.client_created.email.processed
/icube04/actuator/metrics/pinapp.messaging.client_created.email.seconds


Crea un cliente (endpoint correspondiente).

Repite las URL y verifica que count incrementa.

En /actuator/prometheus busca las series anteriores.

#### Logs:

Se configura en logback-spring.xml para consola + archivo (/logs/app-YYYY-MM-DD.log).

Access logs de Tomcat habilitados.



## Swagger

UI: http://localhost:8080/icube04/doc/swagger-ui/index.html

UI DEPLOY: http://54.221.10.9:8080/icube04/doc/swagger-ui/index.html

OpenAPI JSON: http://localhost:8080/icube04/v3/api-docs

OpenAPI JSON Deploy: http://54.221.10.9:8080/icube04/v3/api-docs


## Pruebas
### Unitarias / Integración

Ejecutar: mvn test

### Tests de arquitectura (ArchUnit)

Ejecutar: mvn -Dtest=ArchitectureLayerTest test


## Rutas

RabbitMQ UI: http://localhost:15672

Actuator Health: http://localhost:8080/icube04/actuator/health

Actuator Health Deploy: http://localhost:8080/icube04/actuator/health

Actuator Metrics: http://localhost:8080/icube04/actuator/metrics

Actuator Metrics Deploy: http://54.221.10.9:8080/icube04/actuator/metrics

Prometheus (scrape): http://localhost:8080/icube04/actuator/prometheus

Prometheus (scrape) Deploy: http://54.221.10.9:8080/icube04/actuator/prometheus

## Deploy
### AWS URL Base: http://54.221.10.9:8080/icube04


## Conectar DB Prod

Host: 54.221.10.9

Port: 5432

User: postgres

Password: 123456

Database: pinApp