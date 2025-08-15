CREATE TABLE IF NOT EXISTS entidades (
 id SERIAL PRIMARY KEY,
identification VARCHAR(50),
identification_type_config_detail_id BIGINT,
entidad_type_config_detail_id BIGINT,
name CHARACTER VARYING(255),
lastname CHARACTER VARYING(255),
address CHARACTER VARYING(255),
phone VARCHAR (100),
email VARCHAR(150),
status_config_detail_id BIGINT,
company_id BIGINT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT null,

CONSTRAINT fk_identification_type_config_detail_id FOREIGN KEY (identification_type_config_detail_id) REFERENCES config_details(id),
CONSTRAINT fk_entidad_type_config_detail_id FOREIGN KEY (entidad_type_config_detail_id) REFERENCES config_details(id),
CONSTRAINT fk_status_config_detail_id FOREIGN KEY (status_config_detail_id) REFERENCES config_details(id)
);