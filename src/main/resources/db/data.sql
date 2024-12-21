TRUNCATE TABLE wallet cascade;
TRUNCATE TABLE customer cascade;
INSERT INTO wallet (id, balance, account_number) VALUES
(200, 200.00, '1234567890'),
(201, 0.00, '1234567890'),
(202, 600.00, '1234567890');

INSERT INTO customer (wallet_id, first_name, last_name, email, password, gender, phone_number) VALUES
    (200, 'John', 'Smith', 'john.smith@gmail.com', 'password123', 'MALE', '12345678910'),
    (201, 'Jane', 'Doe', 'jane.doe@gmail.com', 'password123', 'FEMALE', '12345678911'),
    (202, 'Alice', 'Wonder', 'alice.wonder@gmail.com', 'password123', 'FEMALE', '12345678912');
