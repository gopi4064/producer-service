# Producer Service

The Producer Service is responsible for generating and sending messages to one or more message queues using **Java
Message Service (JMS)**. This service enables communication between systems by producing messages consumed by other
services, such as the Consumer Service. It supports dynamic configuration of queues and periodic health-check messages
to ensure system stability.

---

## Features

- **Dynamic Queue Configuration**: Configure queues dynamically to accommodate different messaging requirements.
- **Health Check Messages**: Periodically sends "heartbeat" messages to monitor system health and ensure messages are
  processed as expected.
- **Integration with JMS**: Fully utilizes the Java Message Service (JMS) API for reliable and asynchronous message
  communication.
- **Multi-System Communication**: Acts as a bridge to notify and communicate with various systems via messages.

---

## Requirements

- **Java 17+**: The service is written in Java and requires Java Development Kit (JDK) 17 or later.
- **Jakarta EE**: The service utilizes Jakarta EE for additional features such as dependency injection and JMS
  integration.
- **Build Tool**: Gradle or Maven for project management and dependencies.
- **Message Broker**: A JMS-compliant message broker such as ActiveMQ, RabbitMQ, or IBM MQ.

---

## Technologies Used

- **Jakarta EE**: For enterprise-level Java application development, including JMS.
- **Lombok**: Simplifies the Java code by reducing boilerplate code.
- **Spring MVC**: Enables easier integration for RESTful endpoints if required in the future.
- **JMS (Java Message Service)**: Used for message production to queues and topics.

---

## How It Works

1. **Message Production**:
    - The Producer Service generates and sends messages to configured queues.
    - Messages are structured and serialized before being transmitted.

2. **Health Checks**:
    - Periodic health messages are dispatched to ensure proper functioning of the system.
    - Can be configured to run at specific intervals.

3. **Dynamic Configuration**:
    - Queue locations and properties can be dynamically reconfigured without restarting the service.

---

## Installation

1. Clone this repository:

    ```bash
    git clone https://github.com/your-repo/producer-service.git
    ```

2. Navigate into the project directory:

    ```bash
    cd producer-service
    ```

   3. Build the project:
      Maven:
      ```bash
      mvn clean package
      ```


4. Configure the `application.properties` or `application.yml` file to specify your JMS provider, queue names, and other
   settings.

5. Run the service:

    ```bash
    java -jar target/producer-service.jar
    ```

---

## Configuration

The service properties can be set in the `application.yml` or `application.properties` file located in the
`src/main/resources` directory. Below is an example configuration:

```yaml
jms:
  broker-url: tcp://localhost:61616
  queue-name: myQueue
  health-check-interval: 5000
```

- **broker-url**: The URL of your JMS provider (e.g., ActiveMQ).
- **queue-name**: The name of the queue where messages will be sent.
- **health-check-interval**: Interval for sending health-check messages (in milliseconds).

---

## Example Usage

Here is an example of how you would send messages with the Producer Service:

```java
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Queue;
import jakarta.jms.Session;
import jakarta.jms.MessageProducer;
import jakarta.jms.TextMessage;

public class ProducerService {
    public static void main(String[] args) {
        try {
            ConnectionFactory factory = // Obtain JMS ConnectionFactory
                    Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("myQueue");

            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage("Hello, World!");
            producer.send(message);

            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## Testing

Run unit tests using:

```bash
mvn test
```

or

Using below request

http://localhost:8080/send?queue=new&message=HelloWorld

## License

This project is licensed under the **MIT License**. You are free to use, modify, and distribute it.

---

## Contribution

1. Fork the repository.
2. Create a feature branch from `main`.
3. Submit a pull request with detailed information.
