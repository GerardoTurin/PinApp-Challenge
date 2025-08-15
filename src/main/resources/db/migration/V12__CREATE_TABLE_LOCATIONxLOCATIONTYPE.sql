CREATE TABLE IF NOT EXISTS locations_locationstypes (
id SERIAL PRIMARY KEY,
location_id BIGINT,
location_type_config_detail_id BIGINT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT null,

CONSTRAINT fk_location_id FOREIGN KEY (location_id) REFERENCES locations(id),
CONSTRAINT fk_location_type_config_detail_id FOREIGN KEY (location_type_config_detail_id) REFERENCES config_details(id)
);