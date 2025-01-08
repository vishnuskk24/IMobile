

DROP DATABASE IF EXISTS defaultdb;
create database defaultdb;
use defaultdb;
CREATE TABLE users (
    mobile_number BIGINT PRIMARY KEY,
    user_id VARCHAR(255) UNIQUE NOT NULL,
    account_holder_name VARCHAR(255) NOT NULL,
    gender VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    communication_address VARCHAR(500) NOT NULL,
    PAN VARCHAR(20) NOT NULL
);


INSERT INTO users (mobile_number, user_id, account_holder_name, gender, date_of_birth, password, email, communication_address, PAN) 
VALUES (1234567890, 'user001', 'John Doe', 'Male', '1990-01-15', 'password123', 'john.doe@example.com', '123 Main St, Anytown, USA', 'ABCDE1234F');

INSERT INTO users (mobile_number, user_id, account_holder_name, gender, date_of_birth, password, email, communication_address, PAN) 
VALUES (2345678901, 'user002', 'Jane Smith', 'Female', '1985-05-20', 'securepass456', 'jane.smith@example.com', '456 Elm St, Othertown, USA', 'FGHIJ5678K');

INSERT INTO users (mobile_number, user_id, account_holder_name, gender, date_of_birth, password, email, communication_address, PAN) 
VALUES (3456789012, 'user003', 'Emily Johnson', 'Female', '1992-12-01', 'emily789', 'emily.johnson@example.com', '789 Oak St, Sometown, USA', 'KLMNO9012P');

INSERT INTO users (mobile_number, user_id, account_holder_name, gender, date_of_birth, password, email, communication_address, PAN) 
VALUES (4567890123, 'user004', 'Michael Brown', 'Male', '1988-03-10', 'mike456', 'michael.brown@example.com', '321 Pine St, Anycity, USA', 'QRSTU3456V');

INSERT INTO users (mobile_number, user_id, account_holder_name, gender, date_of_birth, password, email, communication_address, PAN) 
VALUES (5678901234, 'user005', 'Olivia Davis', 'Female', '1995-07-22', 'olivia123', 'olivia.davis@example.com', '654 Maple St, Anyville, USA', 'VWXYZ6789A');


CREATE TABLE accounts (
    account_number BIGINT PRIMARY KEY auto_increment,
    bank_name VARCHAR(255) NOT NULL,
    balance DOUBLE NOT NULL,
    account_type VARCHAR(100) NOT NULL,
    ifsc_code VARCHAR(20) NOT NULL,
    opening_date DATE NOT NULL,
    mobile_number BIGINT not NULL,
    FOREIGN KEY (mobile_number) REFERENCES users(mobile_number)
);

INSERT INTO accounts (account_number, bank_name, balance, account_type, ifsc_code, opening_date, mobile_number) 
VALUES (10000001, 'Bank of America', 1500.75, 'Savings', 'BOFAUS3N', '2020-01-15', 1234567890);

INSERT INTO accounts (account_number, bank_name, balance, account_type, ifsc_code, opening_date, mobile_number) 
VALUES (10000002, 'Wells Fargo', 2500.00, 'Checking', 'WFBIUS6S', '2019-05-20', 2345678901);

INSERT INTO accounts (account_number, bank_name, balance, account_type, ifsc_code, opening_date, mobile_number) 
VALUES (10000003, 'Chase Bank', 3200.50, 'Savings', 'CHASUS33', '2018-12-01', 3456789012);

INSERT INTO accounts (account_number, bank_name, balance, account_type, ifsc_code, opening_date, mobile_number) 
VALUES (10000004, 'Citi Bank', 4800.00, 'Checking', 'CITIUS33', '2017-03-10', 4567890123);

INSERT INTO accounts (account_number, bank_name, balance, account_type, ifsc_code, opening_date, mobile_number) 
VALUES (10000005, 'HSBC', 6100.75, 'Savings', 'HSBCUS33', '2021-07-22', 5678901234);

CREATE TABLE digital_banking (
    digital_banking_id Integer PRIMARY KEY auto_Increment,
    mobile_number BIGINT NOT NULL,
    account_number BIGINT NOT NULL,
    account_type VARCHAR(100) NOT NULL,
    FOREIGN KEY (mobile_number) REFERENCES users(mobile_number),
    FOREIGN KEY (account_number) REFERENCES accounts(account_number)
);

INSERT INTO digital_banking (digital_banking_id, mobile_number, account_number, account_type) 
VALUES (00001, 1234567890, 10000001, 'Savings');

INSERT INTO digital_banking (digital_banking_id, mobile_number, account_number, account_type) 
VALUES (00002, 2345678901, 10000002, 'Checking');

INSERT INTO digital_banking (digital_banking_id, mobile_number, account_number, account_type) 
VALUES (00003, 3456789012, 10000003, 'Savings');

INSERT INTO digital_banking (digital_banking_id, mobile_number, account_number, account_type) 
VALUES (00004, 4567890123, 10000004, 'Checking');

INSERT INTO digital_banking (digital_banking_id, mobile_number, account_number, account_type) 
VALUES (00005, 5678901234, 10000005, 'Savings');


CREATE TABLE transactions (
    transaction_id INT PRIMARY KEY auto_increment,
    mode_of_transaction VARCHAR(255) NOT NULL,
    paid_to BIGINT NOT NULL,
    receiver_account_number BIGINT NOT NULL,
    amount DOUBLE NOT NULL,
    transaction_date_time DATETIME NOT NULL,
    remarks VARCHAR(500) NOT NULL,
    paid_from BIGINT NOT NULL,
    sender_account_number BIGINT NOT NULL,
    FOREIGN KEY (paid_to) REFERENCES users(mobile_number),
    FOREIGN KEY (receiver_account_number) REFERENCES accounts(account_number),
    FOREIGN KEY (paid_from) REFERENCES users(mobile_number),
    FOREIGN KEY (sender_account_number) REFERENCES accounts(account_number)
);
INSERT INTO transactions (transaction_id, mode_of_transaction, paid_to, receiver_account_number, amount, transaction_date_time, remarks, paid_from, sender_account_number) 
VALUES (1, 'Fund Transfer', 1234567890, 10000001, 500.00, '2024-05-31 10:15:00', 'Payment for groceries', 2345678901, 10000002);

INSERT INTO transactions (transaction_id, mode_of_transaction, paid_to, receiver_account_number, amount, transaction_date_time, remarks, paid_from, sender_account_number) 
VALUES (2, 'Fund Transfer', 2345678901, 10000002, 1000.00, '2024-05-30 15:30:00', 'Monthly rent payment', 3456789012, 10000003);

INSERT INTO transactions (transaction_id, mode_of_transaction, paid_to, receiver_account_number, amount, transaction_date_time, remarks, paid_from, sender_account_number) 
VALUES (3, 'Fund Transfer', 3456789012, 10000003, 750.50, '2024-05-29 12:45:00', 'Dinner with friends', 4567890123, 10000004);

INSERT INTO transactions (transaction_id, mode_of_transaction, paid_to, receiver_account_number, amount, transaction_date_time, remarks, paid_from, sender_account_number) 
VALUES (4, 'Fund Transfer', 4567890123, 10000004, 200.00, '2024-05-28 09:00:00', 'Movie tickets', 5678901234, 10000005);

INSERT INTO transactions (transaction_id, mode_of_transaction, paid_to, receiver_account_number, amount, transaction_date_time, remarks, paid_from, sender_account_number) 
VALUES (5, 'Fund Transfer', 5678901234, 10000005, 1200.75, '2024-05-27 18:20:00', 'Shopping spree', 1234567890, 10000001);