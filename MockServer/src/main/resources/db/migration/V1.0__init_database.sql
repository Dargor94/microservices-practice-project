CREATE TABLE IF NOT EXISTS identification_type
(
    identification_type_id int AUTO_INCREMENT NOT NULL,
    description            varchar(5)         NOT NULL,
    PRIMARY KEY (identification_type_id)
);

CREATE TABLE IF NOT EXISTS customer
(
    customer_id            bigint AUTO_INCREMENT NOT NULL,
    forename               varchar(50),
    surname                varchar(50),
    identification_number  bigint                NOT NULL,
    identification_type_id int                   NOT NULL,
    PRIMARY KEY (customer_id),
    CONSTRAINT IdentificationNumber UNIQUE (identification_number),
    CONSTRAINT FK_IDTypeCustomer FOREIGN KEY (identification_type_id)
        REFERENCES identification_type (identification_type_id)
);