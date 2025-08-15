CREATE TABLE orders (
id SERIAL PRIMARY KEY,
order_date DATE,
entity_id BIGINT,
booking_id BIGINT,
operation_type_config_detail_id BIGINT,
status_config_detail_id BIGINT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT null,

CONSTRAINT fk_entity_id FOREIGN KEY (entity_id) REFERENCES entidades(id),
CONSTRAINT fk_booking_id FOREIGN KEY (booking_id) REFERENCES bookings(id),
CONSTRAINT fk_operation_type_config_detail_id FOREIGN KEY (operation_type_config_detail_id) REFERENCES config_details(id),
CONSTRAINT fk_status_config_detail_id FOREIGN KEY (status_config_detail_id) REFERENCES config_details(id)
);

CREATE TABLE orders_statuses (
id SERIAL PRIMARY KEY,
order_id BIGINT,
status_config_detail_id BIGINT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders(id),
CONSTRAINT fk_status_config_detail_id FOREIGN KEY (status_config_detail_id) REFERENCES config_details(id)
);

CREATE TABLE orders_locations (
id SERIAL PRIMARY KEY,
order_id BIGINT,
location_id BIGINT,
status_config_detail_id BIGINT,
position BIGINT,

CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders(id),
CONSTRAINT fk_location_id FOREIGN KEY (location_id) REFERENCES locations(id),
CONSTRAINT fk_status_config_detail_id FOREIGN KEY (status_config_detail_id) REFERENCES config_details(id)
);