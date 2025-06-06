# online-doctor

![Project scope](./online-doctor.png)

### Login Endpoints:


**/register**
```
{
  "username": "jao",
  "password": "123456789",
  "role": "ROLE_DOCTOR",
  "token": ""
}
```

**/login**
```
{
  "username": "jao",
  "password": "123456789"
}
```
___

### Patient Endpoints:


Post: **/patient**
```
{
  "email": "johndoe@example.com",
  "name": "John",
  "surname": "Doe",
  "nationality": "Brazilian",
  "phone": "+55 11 91234-5678",
  "address": "Rua Exemplo, 123, São Paulo, SP",
  "age": 30,
  "gender": "Male"
}
```
- Get: **/patient**  
- Get All: **/patient/{id}** 
- Get by Username: **/patient/username/{username}**  
- Put: **/patient/{id}**  
```
{
  "email": "johndoe@example.com",
  "name": "John",
  "surname": "Doe",
  "nationality": "Brazilian",
  "phone": "+55 11 91234-5678",
  "address": "Rua Exemplo, 123, São Paulo, SP",
  "age": 30,
  "gender": "Male"
}
```

- Delete: **/patient/{id}**  

___

### Doctor Endpoints:

- Post: **/doctor**
```
{
  "email": "dr.Maria@example.commm",
  "name": "Maria",
  "surname": "Doe",
  "nationality": "American",
  "phone": "+1-555-123456",
  "address": "123 Medical St, New York, NY",
  "specialisation": "Cardiology",
  "registrationCode": "DOC-123456",
  "price": 150.0,
  "rating": 4.7,
  "experience": 10
}
```
- Get: **/doctor**  
- Get All: **/doctor/{id}**  
- Get by Username: **/doctor/username/{username}**  
- Put: **/doctor/{id}**  
```
{
  "email": "dr.Maria@example.commm",
  "name": "Maria",
  "surname": "Doe",
  "nationality": "American",
  "phone": "+1-555-123456",
  "address": "123 Medical St, New York, NY",
  "specialisation": "Cardiology",
  "registrationCode": "DOC-123456",
  "price": 150.0,
  "rating": 4.7,
  "experience": 10
}
```

- Delete: **/doctor/{id}** 

___

### Availability Endpoints:

Post: **/availability**

```
{
  "date": "2025-06-10",
  "start": "09:00",
  "end": "11:30"
}
```

Get: **/availability**
Get: **/availability/{specialisation}/{date}**
Get: **/availability/doctor/{id}**
Delete: **/availability/{id}**

### Medical Appointment Endpoints:

Get: **/appointments/user**
Post: **/appointments/{availabilityId}/doctor/{doctorId}**
Post: **/appointments/{appointmentId}/doctor/{doctorId}/cancel**