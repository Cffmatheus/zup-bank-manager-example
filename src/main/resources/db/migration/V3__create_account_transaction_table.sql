CREATE TABLE account_transaction (
                         transaction_id SERIAL PRIMARY KEY,
                         transaction_type VARCHAR(20) NOT NULL,
                         amount MONEY NOT NULL,
                         origin_account_number varchar(255) REFERENCES account(account_number),
                         destination_account_number varchar(255),
                         created_at TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP
)