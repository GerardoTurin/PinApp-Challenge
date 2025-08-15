


CREATE TABLE clients (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    company_id BIGINT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    age INT,
    date_of_birth DATE,

    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES companies(id)
);