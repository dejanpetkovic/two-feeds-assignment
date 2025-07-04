# Sports Feed Processor

This project processes feed request from multiple providers and normalizes them into a standard messages. 
It validates requests, converts them into standard messages, and queues them in mocked message queue.

---

## Security notes

- For production, consider adding API key header in requests per provider and rate limiting (e.g., using Resilience4J).

---

## Configuration

application.properties:

Application listens on port 9000 by default, property:
server.port=9000

### Custom configuration

You can create a folder named `config` at the same directory level as the jar file. 
Place your `application.properties`  inside `config` folder to override the default settings.

## How to run this?

1. Make sure you have Java 21+ installed.
2. Navigate to the project directory.
3. Run the following commands:

`mvn clean install`  
`mvn spring-boot:run`

Or if you want to run the JAR:
java -jar target\feeds-1.0.0.jar

## How to use API?

### Alpha Provider

Send a POST request to `/provider-alpha/feed` with request:

#### Odds change

```json
{
	"msg_type": "odds_update",
	"event_id": "event12345",
	"values": {
	"1": 1.5,
	"X": 3.3,
	"2": 4.5
	}
}
```

#### Bet settlement

```json
{
	"msg_type": "settlement",
	"event_id": "event12345",
	"outcome": "1"
}
```

### Beta Provider

Send a POST request to `/provider-beta/feed` with request:

#### Odds change

```json
{
	"type": "ODDS",
	"event_id": "event54321",
	"odds": {
	"home": 2,
	"draw": 3.5,
	"away": 4.0
	}
}
```

#### Bet settlement

```json
{
	"type": "SETTLEMENT",
	"event_id": "event54321",
	"result": "draw"
}
```