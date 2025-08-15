


CREATE TABLE user_connection (
    id BIGSERIAL PRIMARY KEY,
    company_id BIGINT,
    user_id BIGINT,
    username VARCHAR(255),
    password VARCHAR(255),

    CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES companies(id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);