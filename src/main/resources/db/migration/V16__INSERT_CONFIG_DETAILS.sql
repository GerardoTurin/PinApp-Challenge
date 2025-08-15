INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'ae803474-2af0-4a57-809b-9ad8c50fb726'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Registrado', '12ea6d6c-52a5-41b8-821c-6854988b86c8', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'ae803474-2af0-4a57-809b-9ad8c50fb726'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'En ruta', '28fcca92-385a-4108-947b-1274b5410b3f', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'ae803474-2af0-4a57-809b-9ad8c50fb726'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Finalizado', '9f3491ec-d532-4a87-9ace-4855ade7e6d9', true);


DELETE FROM config_details WHERE code = '7b54358d-281c-40fa-b416-9f07bbcf01bd';
DELETE FROM config_details WHERE code = 'd1668eae-65b7-4050-83e9-8dd41edc53e9';

