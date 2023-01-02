
# SOS TEST

This project is created for coding task.


## Deployment

To deploy this project run

```bash
  docker-compose up -d
```


## API Reference

Please use SOAP UI and use the SOAP UI projects saved under the folder `integration_testing`. List of API are given below.


#### APIs created for Doctors

```http
  GET http://localhost:8182/doctors
```
```http
  GET http://localhost:8182/doctor/${id}
```
```http
  GET http://localhost:8182/doctor?name=${name}
```
```http
  POST http://localhost:8182/doctor
```
```http
  PUT http://localhost:8182/doctor
```
```http
  DELETE http://localhost:8182/doctor/${id}
```


#### APIs created for Patients

```http
  GET http://localhost:8184/patients
```
```http
  GET http://localhost:8184/patient/${id}
```
```http
  GET http://localhost:8184/patient?name=${name}
```
```http
  POST http://localhost:8183/patient
```
```http
  PUT http://localhost:8183/patient
```
```http
  DELETE http://localhost:8183/patient/${id}
```


