CREATE TABLE customer (
    customer_id varchar(255) NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    cpf varchar(11) NOT NULL,
    email varchar(255),
    birth_date DATE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

ALTER TABLE customer ADD CONSTRAINT cpf_unique UNIQUE (cpf)