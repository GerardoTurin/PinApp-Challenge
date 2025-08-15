CREATE TABLE IF NOT EXISTS bookings (
id SERIAL PRIMARY KEY,
name CHARACTER VARYING(255),
code CHARACTER VARYING(255),
status_config_detail_id BIGINT,
company_id BIGINT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT null,

CONSTRAINT fk_status_config_detail_id FOREIGN KEY (status_config_detail_id) REFERENCES config_details(id),
CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES companies(id)
);