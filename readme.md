Employee ID: T2551 <br>
Employee Name: Varun Sharma

# Employee/Department Management Based CRUD APIs

This project contains employee informations and perform CRUD APIs

### Install and Run Redis for Caching

####Install
```markdown
brew install redis
```
####Run the server
```markdown
brew services start redis
```
#### Monitor Redis
```markdown
redis-cli MONITOR
```
###Run Tests
```markdown
mvn test
```
###Run the application
```markdown
mvn spring-boot:run
```

Alternatively, you can also navigate to target directory and run the application using
```markdown
cd target
java -jar emp-api-0.0.1-SNAPSHOT.jar
```

## Operations on Employee

### GET Requests

#### Get all Employees

```markdown
curl http://localhost:8083/api/employee
```

#### Get Employee by thier id

```markdown
curl http://localhost:8083/api/employee/id/{{empId}}
```

#### Get Employees by thier Department

```markdown
curl http://localhost:8083/api/employee/dept/{{deptId}}
```

### POST Requests

#### Add a new Employee

```markdown
curl -X POST -H "Content-Type:application/json" \
 -d '{"name":"Varun","email":"varun@gmail.com","dob":"2000-16-10","deptId":1}' \
 http://localhost:8083/api/employee
```

### PUT Requests

#### Update an Employee

```markdown
curl -X PUT http://localhost:8083/api/employee/<id>?name=<name>&email=<email>&deptId=<deptId>
```

### DELETE Requests

#### Delete an Employee

```markdown
curl -X DELETE http://localhost:8083/api/employee/<id>
```

## Operations on Depertmant

### GET Requests

#### Get all Departments

```markdown
curl http://localhost:8083/api/department
```

#### Get Department by id

```markdown
curl http://localhost:8083/api/department/{{deptId}}
```

### POST Requests

#### Add a new Department

```markdown
curl -X POST -H "Content-Type:application/json" \
 -d '{"name":"FASTag","manager":"abc"}' \
 http://localhost:8083/api/department
```

### PUT Requests

#### Update a Department

```markdown
curl -X PUT http://localhost:8083/api/department/<id>?name=<name>&manager=<manager>
```

### DELETE Requests

#### Delete a department

```markdown
curl -X DELETE http://localhost:8083/api/department/<id>
```
