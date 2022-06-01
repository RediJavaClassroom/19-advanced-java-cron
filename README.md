## Using application

### Run application manually

Start Postgres:

```
docker run --publish 5432:5432 --env POSTGRES_PASSWORD=mysecretpassword --detach postgres
```

Run `DemoApplciation` in Intellij IDEA.

### Run application in docker

```
mvn clean package
docker-compose build
docker-compose up
```

Start application in IntelliJ IDEA

### API documentation

Visit `localhost:8080/swagger/ui`

Create user:

```
curl -X POST http://localhost:8080/users --data '{"email": "test@example.com", "name": "Test", "password": "password", "userType": "FREE"}' -H "Content-Type: application/json" -v 
```

Login user:
```
curl -X POST http://localhost:8080/login --data '{"email": "test@example.com", "password": "password"}' -H "Content-Type: application/json" -v
```

Create link:

```
curl -X POST http://localhost:8080/links --data '{"originalUrl": "https://mkysoft.com"}' -H "Content-Type: application/json"  --cookie "JSESSIONID=9879245D1AE19616DF99A03D4CA1F6C5; Path=/; HttpOnly" -v
```

Expand link:

```
curl http://localhost:8080/l/65e5d8dd --cookie "JSESSIONID=52069F0C02B2EA5C3280BF57F3765BE5; Path=/; HttpOnly" -v
```

