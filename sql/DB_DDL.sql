create schema sos_schema;
set sql_mode = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
create user 'app_user'@'%' identified by 'MyP@ssw0rd';
grant all privileges on sos_schema.* to 'app_user'@'%' with GRANT OPTION;
flush privileges;

CREATE TABLE `sos_schema`.`doctors` (
id INT AUTO_INCREMENT,
full_name VARCHAR(200),
specialization VARCHAR(200),
contact_number VARCHAR(200),
PRIMARY KEY(id)
);


CREATE TABLE `sos_schema`.`patients` (
  id INT AUTO_INCREMENT,
  full_name VARCHAR(200),
  address VARCHAR(500),
  age INT,
  contact_number VARCHAR(200),
  doctor_id INT,
  PRIMARY KEY(id), FOREIGN KEY(doctor_id) REFERENCES doctors(id)
  );
