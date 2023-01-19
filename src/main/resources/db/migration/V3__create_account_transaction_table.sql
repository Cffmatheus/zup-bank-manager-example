CREATE TABLE account_transaction (
                         transaction_id SERIAL PRIMARY KEY,
                         account_number varchar(255) NOT NULL,
                         deposit MONEY,
                         withdraw MONEY,
                         account_number_to varchar(255),
                         account_number_from varchar(255),
                         created_at TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP NOT NULL,
                         CONSTRAINT fk_account_number
                             FOREIGN KEY (account_number)
                                 REFERENCES account(account_number)
)