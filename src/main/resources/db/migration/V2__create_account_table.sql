CREATE TABLE account (
                          account_number varchar(255) NOT NULL PRIMARY KEY,
                          customer_id varchar(255) NOT NULL,
                          balance MONEY NOT NULL,
                          created_at TIMESTAMP NOT NULL,
                          updated_at TIMESTAMP,
                          CONSTRAINT fk_customer_id
                            FOREIGN KEY (customer_id)
                            REFERENCES customer(customer_id)
)