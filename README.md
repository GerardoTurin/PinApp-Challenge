# icube04-backend

Backend Java Spring Boot para PinApp. Implementa una arquitectura hexagonal y utiliza tecnologías como RabbitMQ, JPA (PostgreSQL), Flyway, Swagger, Logback y Actuator con Micrometer/Prometheus para métricas.

**Context-path de la app:** `/icube04`

---

## Tabla de Contenidos
1. [Arquitectura y Patrones](#arquitectura-y-patrones)
2. [Requisitos](#requisitos)
3. [Pasos para Correr el Proyecto](#pasos-para-correr-el-proyecto)
4. [Mensajería (RabbitMQ)](#mensajería-rabbitmq)
5. [Logs y Métricas](#logs-y-métricas)
6. [Swagger](#swagger)
7. [Pruebas](#pruebas)
8. [Rutas](#rutas)
9. [Deploy](#deploy)
10. [Conectar a la Base de Datos](#conectar-a-la-base-de-datos)

---

## Arquitectura y Patrones

### Patrón de Arquitectura
**Hexagonal (Ports & Adapters):**
- `modules.<mod>.domain`: Dominio (entidades, lógica, ports).
- `modules.<mod>.application`: Casos de uso (use cases); orquesta dominio.
- `infrastructure.adapters.<mod>`: Adapters (entrada/salida: controllers REST, repositorios JPA, mensajería, etc.).
- `infrastructure.configuration`: Configuración transversal (seguridad, email, messaging, swagger, etc.).

### Patrones de Diseño
- **Repository:** Puertos + adapters JPA.
- **DTO / Mapper:** Adaptación entre dominio e IO.
- **Factory/Builder:** Creación de respuestas/DTO.
- **Strategy:** Seguridad/filters, configuración.
- **Observer/Event-Driven:** RabbitMQ para eventos de dominio.
- **Layered + Hexagonal blend:** Reglas ArchUnit para fronteras.

---

## Requisitos
- **Java:** 21
- **Maven:** 3.9+
- **Docker**
- **Cuenta SMTP:** (Mailtrap) para notificaciones de email en eventos de cliente creado.

> **Nota:** En `application-local.properties`, reemplazar las variables de Mailtrap con las propias.

---

## Pasos para Correr el Proyecto

### 1. Clonar el Repositorio
```bash
git clone https://github.com/GerardoTurin/PinApp-Challenge
cd PinApp-Challenge/icube04-backend
```


### 2. Crear Contenedores Docker
```bash
cd db
docker-compose up -d
```

### 3. Correr el Proyecto
```bash
cd ..
mvn spring-boot:run

#### Opcional: Limpiar y correr:
mvn clean install
mvn spring-boot:run
```

<hr></hr>

### 4. Mensajeria (RabbitMQ)
UI Local: http://localhost:15672 (username: guest, password: guest)

UI Producción: http://54.221.10.9:15672 (username: guest, password: guest)

Eventos:  

Publica un evento de cliente creado.

Listener: 

ClientCreatedEmailListener consume desde la cola y envía una notificación por email.

Cola de ejemplo: 

client.created.queue (ver configuración en RabbitMQConfig).  
<hr></hr>


<hr></hr>

### 5. Logs y Métricas

Actuator

Health: GET /icube04/actuator/health

Métricas: GET /icube04/actuator/metrics

Prometheus Export: GET /icube04/actuator/prometheus

Métricas Personalizadas

Listener Instrumentado con Micrometer:  

Contador de procesados: pinapp.messaging.client_created.email.processed Prometheus: pinapp_messaging_client_created_email_processed_total Tags: queue, outcome (success|error)  

Timer de envío de email: pinapp.messaging.client_created.email.seconds Prometheus (histograma): pinapp_messaging_client_created_email_seconds_bucket|count|sum|max Tags: queue, outcome  

Validación:  

Crear un cliente (endpoint correspondiente).


Verificar que el contador incrementa en:

/icube04/actuator/metrics/pinapp.messaging.client_created.email.processed

/icube04/actuator/metrics/pinapp.messaging.client_created.email.seconds

Revisar las series en /actuator/prometheus.

Logs:

Configuración en logback-spring.xml (consola + archivo: /logs/app-YYYY-MM-DD.log).

Access logs de Tomcat habilitados.

<hr></hr>

<hr></hr>


### 6. Swagger

UI Local: http://localhost:8080/icube04/doc/swagger-ui/index.html

UI Producción: http://54.221.10.9:8080/icube04/doc/swagger-ui/index.html

OpenAPI JSON Local: http://localhost:8080/icube04/v3/api-docs

OpenAPI JSON Producción: http://54.221.10.9:8080/icube04/v3/api-docs

<hr></hr>


<hr></hr>

### 7. Pruebas

Unitarias / Integración
```bash
mvn test
```

Tests de Arquitectura (ArchUnit)
```bash
mvn -Dtest=ArchitectureLayerTest test
```
<hr></hr>

<hr></hr>

### 8. Rutas

RabbitMQ UI: http://localhost:15672

Actuator Health: http://localhost:8080/icube04/actuator/health

Actuator Metrics: http://localhost:8080/icube04/actuator/metrics

Prometheus (scrape): http://localhost:8080/icube04/actuator/prometheus

<hr></hr>

<hr></hr>

### 9. Deploy

AWS URL Base

http://54.221.10.9:8080/icube04

<hr></hr>

<hr></hr>

 ### 10. Conectar a la Base de Datos

Host: 54.221.10.9

Port: 5432

User: postgres

Password: 123456

Database: pinApp
<hr></hr>


