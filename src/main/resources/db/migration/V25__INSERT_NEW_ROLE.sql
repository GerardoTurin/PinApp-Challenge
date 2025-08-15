


INSERT INTO roles  (name, company_id) VALUES ('SOMAS_USER', (SELECT id FROM companies WHERE (name = 'SOMAS')));

insert into user_roles (user_id, role_id) values ((select id from users where username = 'trackAdmin'), (select id from roles where name = 'SOMAS_USER'));