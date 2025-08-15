
CREATE TABLE positions (
    id BIGSERIAL PRIMARY KEY,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    nearby_point DOUBLE PRECISION,
    altitude DOUBLE PRECISION,
    speed DOUBLE PRECISION,
    address VARCHAR(255),
    attributes JSONB,
    device_id BIGINT,
    device_time TIMESTAMP,
    fix_time TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);




CREATE TABLE devices (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    unique_id BIGINT,
    position_id BIGINT,
    created_user_id BIGINT,
    company_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update TIMESTAMP DEFAULT null
);




-- Agrego la restricción que vincula positions a devices
ALTER TABLE positions
    ADD CONSTRAINT fk_device_id FOREIGN KEY (device_id) REFERENCES devices(id);

-- Agrego la restricción que vincula devices a positions y otras FK
ALTER TABLE devices
    ADD CONSTRAINT fk_position_id FOREIGN KEY (position_id) REFERENCES positions(id),
    ADD CONSTRAINT fk_created_user_id FOREIGN KEY (created_user_id) REFERENCES users(id),
    ADD CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES companies(id);





CREATE TABLE groups (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    code VARCHAR(255),
    created_user_id BIGINT,
    company_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_created_user_id FOREIGN KEY (created_user_id) REFERENCES users(id),
    CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES companies(id)
);




CREATE TABLE group_devices (
    id BIGSERIAL PRIMARY KEY,
    group_id BIGINT,
    device_id BIGINT,
    created_user_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_group_id FOREIGN KEY (group_id) REFERENCES groups(id),
    CONSTRAINT fk_device_id FOREIGN KEY (device_id) REFERENCES devices(id),
    CONSTRAINT fk_created_user_id FOREIGN KEY (created_user_id) REFERENCES users(id)
);




CREATE TABLE user_group (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    group_id BIGINT,
    created_user_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_group_id FOREIGN KEY (group_id) REFERENCES groups(id),
    CONSTRAINT fk_created_user_id FOREIGN KEY (created_user_id) REFERENCES users(id)
);





CREATE TABLE user_devices (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    device_id BIGINT,
    status_config_detail_id BIGINT,
    created_user_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_device_id FOREIGN KEY (device_id) REFERENCES devices(id),
    CONSTRAINT fk_status_config_detail_id FOREIGN KEY (status_config_detail_id) REFERENCES config_details(id),
    CONSTRAINT fk_created_user_id FOREIGN KEY (created_user_id) REFERENCES users(id)
);




CREATE TABLE company_connection (
    id BIGSERIAL PRIMARY KEY,
    company_id BIGINT,
    ip VARCHAR(255),
    port INTEGER,
    user_name VARCHAR(255),
    password VARCHAR(255),
    created_user_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES companies(id),
    CONSTRAINT fk_created_user_id FOREIGN KEY (created_user_id) REFERENCES users(id)
);