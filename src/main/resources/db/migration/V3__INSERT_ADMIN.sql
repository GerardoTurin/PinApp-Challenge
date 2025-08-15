-- INSERT SUPER USER
INSERT INTO users (username, password, name, lastname, email, status, user_type_config_detail_id, img_url, updated_at)
VALUES (
           'somasAdmin',
           '$2a$12$jjgrMmYw7NBmHOY/DZP/.OgCLZMk8b1HH7JdKXe65SCAxS6zpBtee',
           'Super',
           'Usuario',
           'usuariosuperadmin@gmail.com',
           true,
           (SELECT id FROM config_details WHERE code = 'b9fde102-c5bc-4dbc-a4a9-65c6eab2f6aa'),
           '',
           CURRENT_TIMESTAMP
       );

INSERT INTO users_companies (user_id, company_id) VALUES ((SELECT id FROM users WHERE username = 'somasAdmin'), (SELECT id FROM companies WHERE name = 'SOMAS'));

INSERT INTO roles  (name, company_id) VALUES ('SOMAS_SUPER_ADMIN', (SELECT id FROM companies WHERE (name = 'SOMAS')));

insert into user_roles (user_id, role_id) values ((select id from users where username = 'somasAdmin'), (select id from roles where name = 'SOMAS_SUPER_ADMIN'));