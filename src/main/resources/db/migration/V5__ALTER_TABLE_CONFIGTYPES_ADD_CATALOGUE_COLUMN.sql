ALTER TABLE config_types ADD COLUMN catalog boolean;

UPDATE config_types
SET catalog = false
WHERE name = 'Usuarios';

