CREATE TABLE IF NOT EXISTS locations (
id SERIAL PRIMARY KEY,
name CHARACTER VARYING(255),
address CHARACTER VARYING(255),
gps_coordinates CHARACTER VARYING(255),
gps_coordinates_type_config_detail_id BIGINT,
category_config_detail_id BIGINT,
status_config_detail_id BIGINT,
company_id BIGINT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT null,

CONSTRAINT fk_gps_coordinates_type_config_detail_id FOREIGN KEY (gps_coordinates_type_config_detail_id) REFERENCES config_details(id),
CONSTRAINT fk_category_config_detail_id FOREIGN KEY (category_config_detail_id) REFERENCES config_details(id),
CONSTRAINT fk_status_config_detail_id FOREIGN KEY (status_config_detail_id) REFERENCES config_details(id),
CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES companies(id)
);
