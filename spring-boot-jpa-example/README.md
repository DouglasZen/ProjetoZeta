
# Configuração do banco de dados MySQL

Utilizar a configuração conforme arquivo `/resources/application.properties`.
Conforme abaixo

```
spring.datasource.username = u_projetozeta
spring.datasource.password = a1s2d3
spring.datasource.url = jdbc:mysql://localhost/db_projetozeta
spring.jpa.hibernate.ddl-auto=update
```

# Rodando pprojeto

```
mvn spring-boot:run
```

# Exemplos testes

### Create Place:
```
curl -X POST -H "Content-Type: application/json" -d '{
	"nome": "Sheed"
}' "http://localhost:8090/api/places"
```

### Read Place
```
curl -X GET -H "Content-Type: application/json" "http://localhost:8090/api/places"
```


### Update Place
```
curl -X PUT -H "Content-Type: application/json" -d '{
	"nome":"Sheed Editado"
}' "http://localhost:8090/api/places/1"
```

### Delete Place
```
curl -X DELETE "http://localhost:8090/api/places/1"
```


### Create Place/Tags:
```
curl -X POST -H "Content-Type: application/json" -d '{
	"nome" : "Woods", "tags" : [{"tag" : "tag"}]}
' "http://localhost:8090/api/places"
```
