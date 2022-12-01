CREATE TABLE address (
  id BIGINT NOT NULL AUTO_INCREMENT,
  client_id BIGINT NOT NULL,
  description VARCHAR(100) DEFAULT NULL,
  address_name VARCHAR(60) DEFAULT NULL,
  number VARCHAR(10) DEFAULT NULL,
  complement VARCHAR(60) DEFAULT NULL,
  neighborhood VARCHAR(60) DEFAULT NULL,
  city VARCHAR(60) DEFAULT NULL,
  state CHAR(2) DEFAULT NULL,
  zip_code VARCHAR(8) DEFAULT NULL,
  is_charge_address CHAR(1) DEFAULT NULL,
  is_service_address CHAR(1) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_address_client FOREIGN KEY (client_id) REFERENCES client (id)
)
