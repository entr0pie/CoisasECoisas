CREATE DATABASE coisas_e_coisas;
USE coisas_e_coisas;

CREATE TABLE Customer (
	id INT NOT NULL AUTO_INCREMENT,
    CPF VARCHAR(11) NOT NULL,
    name VARCHAR(64) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Worker (
	id INT NOT NULL AUTO_INCREMENT,
    CPF VARCHAR(11) NOT NULL,
    name VARCHAR(64) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    address VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Service (
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Request (
	id INT NOT NULL AUTO_INCREMENT,
    contract_date DATE NOT NULL,
    customer_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES Customer(id)
);


CREATE TABLE Worker_Service (
	id INT NOT NULL AUTO_INCREMENT,
    service_id INT NOT NULL,
    worker_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (service_id) REFERENCES Service(id),
    FOREIGN KEY (worker_id) REFERENCES Worker(id)
);


CREATE TABLE Request_Service (
	id INT NOT NULL AUTO_INCREMENT,
    request INT NOT NULL,
    service INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (request) REFERENCES Request (id),
    FOREIGN KEY (service) REFERENCES Service (id)
);

INSERT INTO Customer(CPF, name, surname) VALUES
("111", "Luva de Pedreiro", "O melhor do Mundo");

INSERT INTO Worker(CPF, name, surname, phone_number, address) VALUES 
("222", "Agostinho", "Carrara", "419999999", "Rio de Janeiro");

INSERT INTO Service(name, type, price) VALUES 
("Troca de Pia", "Hidraulico", 200.00),
("Troca de Lampada", "Eletrico", 50.00),
("Fazer paredes", "Alvenaria", 1500.0);

INSERT INTO Worker_Service (service_id, worker_id) VALUES 
(1, 1),
(2, 1),
(3, 1);

INSERT INTO Request (contract_date, customer_id) VALUES 
('2023-05-01', 1),
('2021-01-01', 1),
('2020-02-02', 1);

INSERT INTO Request_Service (request, service) VALUES 
(1, 1),
(1, 2),
(1, 3);

SELECT * FROM Customer;
SELECT * FROM Worker;
SELECT * FROM Service;
SELECT * FROM Request;
SELECT * FROM Worker_Service;

-- DROP DATABASE coisas_e_coisas;

