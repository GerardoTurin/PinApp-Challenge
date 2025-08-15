-- Insert trackAdmin user if not exists
INSERT INTO users (username, password, name, lastname, email, status, user_type_config_detail_id, img_url, created_at, updated_at)
SELECT 'trackAdmin',
       '$2a$12$jjgrMmYw7NBmHOY/DZP/.OgCLZMk8b1HH7JdKXe65SCAxS6zpBtee',
       'Super',
       'UsuarioTrack',
       'usuariotrackadmin@gmail.com',
       true,
       1,
       '',
       TIMESTAMP '2025-04-09 10:33:44.951796',
       TIMESTAMP '2025-04-09 10:33:44.951796'
WHERE NOT EXISTS (
  SELECT 1 FROM users WHERE username = 'trackAdmin'
);

-- Insert companies if not exists
INSERT INTO companies (name, address, description, img_url)
SELECT 'S3', 'Ecuador', 'Admin Company', NULL
WHERE NOT EXISTS (
  SELECT 1 FROM companies WHERE name = 'S3'
);

INSERT INTO companies (name, address, description, img_url)
SELECT 'Litransa', 'Ecuador', 'Admin Company', NULL
WHERE NOT EXISTS (
  SELECT 1 FROM companies WHERE name = 'Litransa'
);





INSERT INTO users_companies (user_id, company_id)
SELECT u.id, c.id
FROM users u, companies c
WHERE u.username = 'somasAdmin'
  AND c.name = 'S3'
  AND NOT EXISTS (
    SELECT 1 FROM users_companies
    WHERE user_id = u.id AND company_id = c.id
  );





INSERT INTO users_companies (user_id, company_id)
SELECT u.id, c.id
FROM users u, companies c
WHERE u.username = 'somasAdmin'
  AND c.name = 'Litransa'
  AND NOT EXISTS (
    SELECT 1 FROM users_companies
    WHERE user_id = u.id AND company_id = c.id
  );



INSERT INTO users_companies (user_id, company_id)
SELECT u.id, c.id
FROM users u, companies c
WHERE u.username = 'trackAdmin'
  AND c.name = 'S3'
  AND NOT EXISTS (
    SELECT 1 FROM users_companies
    WHERE user_id = u.id AND company_id = c.id
  );


INSERT INTO users_companies (user_id, company_id)
SELECT u.id, c.id
FROM users u, companies c
WHERE u.username = 'trackAdmin'
  AND c.name = 'Litransa'
  AND NOT EXISTS (
    SELECT 1 FROM users_companies
    WHERE user_id = u.id AND company_id = c.id
  );





INSERT INTO company_connection (company_id, ip, port, user_name, password, created_user_id, created_at, app_url)
SELECT c.id, '35.227.73.112', 5432, 'somas_app', 'L7RF9jzZoAvd6sxHz9W', NULL, TIMESTAMP '2025-05-26 20:19:33.904331', 'http://35.227.73.112:8082'
FROM companies c
WHERE c.name = 'Litransa'
  AND NOT EXISTS (
    SELECT 1 FROM company_connection WHERE company_id = c.id AND ip = '35.227.73.112'
  );






INSERT INTO company_connection (company_id, ip, port, user_name, password, created_user_id, created_at, app_url)
SELECT c.id, '34.69.254.90', 5432, 'somas_app', 'HNoDdcUuRp2jibTRbow6', NULL, TIMESTAMP '2025-05-26 20:19:33.904331', 'http://34.69.254.90:8082'
FROM companies c
WHERE c.name = 'S3'
AND NOT EXISTS (
  SELECT 1 FROM company_connection WHERE company_id = c.id AND ip = '34.69.254.90'
);

