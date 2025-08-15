INSERT INTO companies (name, address, description) VALUES ('SOMAS', 'Ecuador', 'Admin company');


-- USER TYPES CONFIG DETAILS
INSERT INTO config_types (name, code) VALUES ('Usuarios', '7b278aad-79a1-41fd-9a71-447c87d3c88c');

INSERT INTO config_headers (config_type_id, name, code) VALUES ((SELECT id FROM config_types WHERE code = '7b278aad-79a1-41fd-9a71-447c87d3c88c'), 'Tipo de usuario', '7633d204-2365-4f9e-9dbe-e90e17e142d2');

INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '7633d204-2365-4f9e-9dbe-e90e17e142d2'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Super admin', 'b9fde102-c5bc-4dbc-a4a9-65c6eab2f6aa', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '7633d204-2365-4f9e-9dbe-e90e17e142d2'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Admin', 'd3794b9a-d40c-45aa-9153-206b8e1a929b', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '7633d204-2365-4f9e-9dbe-e90e17e142d2'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Empresarial', 'e49b3d2c-6b2c-4f51-a482-3feff5b98e7f', true);



-- Inserción de módulos de la aplicación
INSERT INTO modules_app (label, icon, path, created_at) VALUES ('Orden', 'pi pi-book', '/orden', DEFAULT);
INSERT INTO modules_app (label, icon, path, created_at) VALUES ('Asignación', 'pi pi-file-edit', '/asignacion', DEFAULT);
INSERT INTO modules_app (label, icon, path, created_at) VALUES ('Planificación', 'pi pi-desktop', '/planificacion', DEFAULT);
INSERT INTO modules_app (label, icon, path, created_at) VALUES ('Monitoreo', 'pi pi-map', '/monitoreo', DEFAULT);
INSERT INTO modules_app (label, icon, path, created_at) VALUES ('Monitoreo Alarmas', 'pi pi-megaphone', '/monitoreoAlarmas', DEFAULT);
INSERT INTO modules_app (label, icon, path, created_at) VALUES ('Tarifa', 'pi pi-wallet', '/tarifa', DEFAULT);
INSERT INTO modules_app (label, icon, path, created_at) VALUES ('FeedBack 1', 'pi pi-book', '/feedback1', DEFAULT);
INSERT INTO modules_app (label, icon, path, created_at) VALUES ('Configuracion del sistema', 'pi pi-cog', '/configSistem', DEFAULT);
INSERT INTO modules_app (label, icon, path, created_at) VALUES ('Admin', 'pi pi-lock', '/configAdmin', DEFAULT);



-- Inserción de items de menú
INSERT INTO menu_items (label, icon, path, created_at, position, module_app_id)
VALUES ('Empresa', 'pi pi-building', '/configAdmin/empresas', DEFAULT, 1, (SELECT id FROM modules_app WHERE label = 'Admin'));

INSERT INTO menu_items (label, icon, path, created_at, position, module_app_id)
VALUES ('Usuarios', 'pi pi-user', '/configAdmin/users', DEFAULT, 2, (SELECT id FROM modules_app WHERE label = 'Admin'));

insert into menu_items (label, icon, path, position, module_app_id) VALUES ('Roles', 'pi pi-sitemap', '/configAdmin/roles', 3, 9);



-- Insercion permisos
INSERT INTO permission_types (name) VALUES ('Leer');
INSERT INTO permission_types (name) VALUES ('Crear');
INSERT INTO permission_types (name) VALUES ('Modificar');
INSERT INTO permission_types (name) VALUES ('Eliminar');