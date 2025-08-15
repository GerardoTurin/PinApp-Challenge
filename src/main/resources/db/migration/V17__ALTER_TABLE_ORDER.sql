ALTER TABLE orders ADD COLUMN active boolean default true;

ALTER TABLE orders_locations ADD COLUMN location_type_config_detail_id BIGINT;
ALTER TABLE orders_locations ADD CONSTRAINT fk_location_type_config_detail_id FOREIGN KEY (location_type_config_detail_id) REFERENCES config_details(id);
