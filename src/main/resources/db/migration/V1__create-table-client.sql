CREATE TABLE client (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(150) DEFAULT NULL,
  document VARCHAR(14) DEFAULT NULL,
  email VARCHAR(255) DEFAULT NULL,
  telephone VARCHAR(14) DEFAULT NULL,
  registration_date DATE DEFAULT NULL,
  PRIMARY KEY (id)
)