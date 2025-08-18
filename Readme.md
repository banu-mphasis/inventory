# Sensor Service - Inventory Management System

A Spring Boot microservice for managing inventory sensor readings and monitoring stock levels in real-time.

## Features

- **Real-time Inventory Monitoring**: Track inventory levels through IoT sensors
- **Multi-location Support**: Monitor inventory across different warehouses and storage locations
- **Environmental Monitoring**: Track temperature and humidity alongside inventory data
- **Low Stock Alerts**: Identify products with inventory below specified thresholds
- **Sensor Status Tracking**: Monitor sensor health and operational status
- **RESTful API**: Comprehensive REST endpoints for all operations
- **API Documentation**: Interactive Swagger UI for API exploration
- **Database Integration**: H2 in-memory database for development and testing

## Technology Stack

- **Java 17**
- **Spring Boot 3.1.2**
- **Spring Data JPA**
- **H2 Database** (in-memory for development)
- **Maven** for dependency management
- **Swagger/OpenAPI 3** for API documentation
- **Spring Boot Actuator** for monitoring

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+ (or use the Maven wrapper included)

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application:

```bash
mvn spring-boot:run
```

Or using the Maven wrapper:
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080/sensor-service`

### Accessing the Application

- **API Base URL**: `http://localhost:8080/sensor-service/api/sensors`
- **Swagger UI**: `http://localhost:8080/sensor-service/swagger-ui.html`
- **H2 Console**: `http://localhost:8080/sensor-service/h2-console`
  - JDBC URL: `jdbc:h2:mem:sensordb`
  - Username: `sa`
  - Password: `password`
- **Health Check**: `http://localhost:8080/sensor-service/actuator/health`

## API Endpoints

### Sensor Readings

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/sensors/readings` | Create a new sensor reading |
| GET | `/api/sensors/readings` | Get all sensor readings |
| GET | `/api/sensors/readings/{id}` | Get sensor reading by ID |
| PUT | `/api/sensors/readings/{id}` | Update a sensor reading |
| DELETE | `/api/sensors/readings/{id}` | Delete a sensor reading |
| GET | `/api/sensors/readings/count` | Get total count of readings |

### Query Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/sensors/readings/sensor/{sensorId}` | Get readings by sensor ID |
| GET | `/api/sensors/readings/product/{productId}` | Get readings by product ID |
| GET | `/api/sensors/readings/location/{location}` | Get readings by location |
| GET | `/api/sensors/readings/status/{status}` | Get readings by sensor status |
| GET | `/api/sensors/readings/latest/{sensorId}` | Get latest readings for a sensor |
| GET | `/api/sensors/readings/low-stock?threshold={value}` | Get low stock readings |
| GET | `/api/sensors/readings/date-range?start={datetime}&end={datetime}` | Get readings in date range |
| GET | `/api/sensors/ids` | Get all unique sensor IDs |

### Sensor Statuses

- `ACTIVE` - Sensor is operational
- `INACTIVE` - Sensor is not operational
- `MAINTENANCE` - Sensor is under maintenance
- `ERROR` - Sensor has encountered an error
- `LOW_BATTERY` - Sensor battery is low

## Sample Data

The application comes pre-loaded with sample sensor readings for testing. You can view this data through the API endpoints or the H2 console.

## Example Usage

### Create a New Sensor Reading

```bash
curl -X POST http://localhost:8080/sensor-service/api/sensors/readings \
  -H "Content-Type: application/json" \
  -d '{
    "sensorId": "SENSOR-007",
    "productId": "PROD-007",
    "location": "Warehouse C - Aisle 3",
    "quantity": 85,
    "status": "ACTIVE",
    "temperature": 23.0,
    "humidity": 45.5
  }'
```

### Get Low Stock Items

```bash
curl "http://localhost:8080/sensor-service/api/sensors/readings/low-stock?threshold=10"
```

### Get Readings by Location

```bash
curl "http://localhost:8080/sensor-service/api/sensors/readings/location/Warehouse%20A%20-%20Aisle%201"
```

## Development

### Building the Project

```bash
mvn clean compile
```

### Running Tests

```bash
mvn test
```

### Creating a JAR

```bash
mvn clean package
```

The JAR file will be created in the `target/` directory.

## Configuration

Key configuration properties in `application.properties`:

- `server.port`: Application port (default: 8080)
- `spring.datasource.url`: Database connection URL
- `management.endpoints.web.exposure.include`: Actuator endpoints to expose
- `logging.level.*`: Logging levels for different packages

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is licensed under the MIT License.
