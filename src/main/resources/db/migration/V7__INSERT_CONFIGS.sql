INSERT INTO config_types (name, code, catalog) VALUES ('Operación', '542aff63-92e0-4772-b9ce-19e2893b9ffd' , true);
INSERT INTO config_headers (config_type_id, name, code) VALUES ((SELECT id FROM config_types WHERE code = '542aff63-92e0-4772-b9ce-19e2893b9ffd'), 'Tipo de operación', '153b272d-5ac2-4843-8d21-feea5a538816');
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '153b272d-5ac2-4843-8d21-feea5a538816'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Importación', 'be2d313e-7ca4-47af-801d-c5e88a739c44', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '153b272d-5ac2-4843-8d21-feea5a538816'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Exportación', 'a7a291c3-cef4-48e3-bdab-13abdaf83931', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '153b272d-5ac2-4843-8d21-feea5a538816'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Ninguno', 'f86304de-8648-4fb1-b848-18c057f47130', true);



INSERT INTO config_types (name, code, catalog) VALUES ('Ubicación', 'cec0f628-43f1-438f-9756-b8366afcbc29', true);
INSERT INTO config_headers (config_type_id, name, code) VALUES ((SELECT id FROM config_types WHERE code = 'cec0f628-43f1-438f-9756-b8366afcbc29'), 'Tipo de ubicación', '693f3612-5b96-4bc1-a394-1882047d6999');
INSERT INTO config_headers (config_type_id, name, code) VALUES ((SELECT id FROM config_types WHERE code = 'cec0f628-43f1-438f-9756-b8366afcbc29'), 'Tipo de coordenadas', 'dce67113-ba6c-45f0-aaa2-2584d64785e2');
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'dce67113-ba6c-45f0-aaa2-2584d64785e2'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Point', '9557e8c5-4c16-4f5e-afe6-1adf354e3682', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'dce67113-ba6c-45f0-aaa2-2584d64785e2'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Circle', 'a126e034-e90a-4d6d-ae69-a993f9344249', true);



INSERT INTO config_headers (config_type_id, name, code) VALUES ((SELECT id FROM config_types WHERE code = 'cec0f628-43f1-438f-9756-b8366afcbc29'), 'Categoria', 'db3a4a18-0195-467b-bd93-a4d3c25a0fc0');
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'db3a4a18-0195-467b-bd93-a4d3c25a0fc0'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Categoria 1', '381e7d06-82b8-4119-89c0-5d48b38ae9a9', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'db3a4a18-0195-467b-bd93-a4d3c25a0fc0'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Categoria 2', '87612ea0-979d-4e46-bda1-66e7cad3c4ca', true);



INSERT INTO config_types (name, code, catalog) VALUES ('Entidad', '3601ea90-5920-41ca-a098-f13c8df5f0c9', true);
INSERT INTO config_headers (config_type_id, name, code) VALUES ((SELECT id FROM config_types WHERE code = '3601ea90-5920-41ca-a098-f13c8df5f0c9'), 'Tipo de entidad', '060e6e46-af3c-4ddd-b79f-0bb9e3a75d14');
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '060e6e46-af3c-4ddd-b79f-0bb9e3a75d14'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Cliente', '152d958e-80d3-40c3-955d-c9e08fa15332', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '060e6e46-af3c-4ddd-b79f-0bb9e3a75d14'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Proveedor', '28ff2c3d-88cb-4cd2-8b88-84e31aa337a1', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '060e6e46-af3c-4ddd-b79f-0bb9e3a75d14'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Chofer', '7406ab4d-8fed-4099-b9fb-dfd4487b9f16', true);



INSERT INTO config_headers (config_type_id, name, code) VALUES ((SELECT id FROM config_types WHERE code = '3601ea90-5920-41ca-a098-f13c8df5f0c9'), 'Tipo de identificación', '85a59806-6e74-4a11-9d79-446cd1830038');
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '85a59806-6e74-4a11-9d79-446cd1830038'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'RUC', '1ac0b422-316f-48a7-845d-947e916bee6b', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '85a59806-6e74-4a11-9d79-446cd1830038'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Pasaporte', '16b6f3a7-4dd8-4a75-84c0-2723573eb732', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '85a59806-6e74-4a11-9d79-446cd1830038'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Cédula', '22148074-6d6c-4d74-acc0-659a54ad74c6', true);


INSERT INTO config_types (name, code, catalog) VALUES ('Orden', '367c076d-55ac-4eb9-983c-8777130f8b1b', true);
INSERT INTO config_headers (config_type_id, name, code) VALUES ((SELECT id FROM config_types WHERE code = '367c076d-55ac-4eb9-983c-8777130f8b1b'), 'Configuración', 'dc1fc281-8f6a-4fac-b513-a2b2b49ef705');
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'dc1fc281-8f6a-4fac-b513-a2b2b49ef705'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Ubicación', '6bb48c58-2c80-4c47-a8ae-bf064d8f4291', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'dc1fc281-8f6a-4fac-b513-a2b2b49ef705'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Tipo de entidad', 'b16f3e65-5546-403b-9903-bb590fb7f1a9', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'dc1fc281-8f6a-4fac-b513-a2b2b49ef705'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Estado', 'a4ed6b06-e6cc-4cb5-90c0-8800c213ef6c', true);

INSERT INTO config_headers (config_type_id, name, code) VALUES ((SELECT id FROM config_types WHERE code = '367c076d-55ac-4eb9-983c-8777130f8b1b'), 'Estado', '45a91b5a-fd60-4bae-9162-e2a09143e83a');
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '45a91b5a-fd60-4bae-9162-e2a09143e83a'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Creado', '455a8400-4cd0-462f-8324-443f756760b7', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '45a91b5a-fd60-4bae-9162-e2a09143e83a'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Despachado', '6e89493a-40cc-42b3-acf9-cc55f7f96fd2', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '45a91b5a-fd60-4bae-9162-e2a09143e83a'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Planificado', 'ed59749f-46e8-47a3-b367-4e7356507097', true);

INSERT INTO config_headers (config_type_id, name, code) VALUES ((SELECT id FROM config_types WHERE code = '367c076d-55ac-4eb9-983c-8777130f8b1b'), 'Proceso', '18a0a3cc-5773-43e7-9e3a-452875a5dbbc');
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '18a0a3cc-5773-43e7-9e3a-452875a5dbbc'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Orden', '505d93d5-ffb0-4274-a94f-bf6f93d598b3', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '18a0a3cc-5773-43e7-9e3a-452875a5dbbc'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Despacho', '6e89493a-40cc-42b3-acf9-cc55f7f96fd2', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '18a0a3cc-5773-43e7-9e3a-452875a5dbbc'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Planificación', 'ed59749f-46e8-47a3-b367-4e7356507097', true);


INSERT INTO config_types (name, code, catalog) VALUES ('Estados', 'a45a9b7f-6139-463a-bd11-278f8338c520', true);

INSERT INTO config_headers (config_type_id, name, code) VALUES ((SELECT id FROM config_types WHERE code = 'a45a9b7f-6139-463a-bd11-278f8338c520'), 'Entidad', '54002c25-d0aa-4655-9566-1a771df353d1');
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '54002c25-d0aa-4655-9566-1a771df353d1'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Creado', '0538c21f-850e-4bd1-b76e-c543b31d88c3', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '54002c25-d0aa-4655-9566-1a771df353d1'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Deshabilitado', 'd10a5956-8c7f-448f-92da-ec5589c9b7f0', true);


INSERT INTO config_headers (config_type_id, name, code) VALUES ((SELECT id FROM config_types WHERE code = 'a45a9b7f-6139-463a-bd11-278f8338c520'), 'Ubicación', 'a18ea49b-b4ac-41c8-86ec-5a7e6cf0a6ec');
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'a18ea49b-b4ac-41c8-86ec-5a7e6cf0a6ec'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Creado', '1bbabaec-c6ab-4856-8a5d-00ac9d1f2d4d', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'a18ea49b-b4ac-41c8-86ec-5a7e6cf0a6ec'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Deshabilitado', '930a8285-2cff-40df-bf40-5cb7648d2280', true);


INSERT INTO config_headers (config_type_id, name, code) VALUES ((SELECT id FROM config_types WHERE code = 'a45a9b7f-6139-463a-bd11-278f8338c520'), 'Booking', 'd473a5fe-e07e-4274-bd22-e5471b11aa40');
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'd473a5fe-e07e-4274-bd22-e5471b11aa40'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Creado', 'a08f739e-27b3-457e-aa96-d0b167b193f5', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'd473a5fe-e07e-4274-bd22-e5471b11aa40'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Deshabilitado', 'ddfee8e2-3512-4686-a01e-2367ee06c6e1', true);


INSERT INTO config_headers (config_type_id, name, code) VALUES ((SELECT id FROM config_types WHERE code = 'a45a9b7f-6139-463a-bd11-278f8338c520'), 'Ubicación x tipo de ubicación', 'e4194d8d-ba9e-4957-9107-b0c5cddd1fad');
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'e4194d8d-ba9e-4957-9107-b0c5cddd1fad'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Creado', 'db17a084-f3ee-4928-8ba5-24dbd7ea3127', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'e4194d8d-ba9e-4957-9107-b0c5cddd1fad'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Deshabilitado', 'fb56afc4-caeb-41e0-97fd-dd47595d56dc', true);


INSERT INTO config_headers (config_type_id, name, code) VALUES ((SELECT id FROM config_types WHERE code = 'a45a9b7f-6139-463a-bd11-278f8338c520'), 'Orden x ubicación', 'ae803474-2af0-4a57-809b-9ad8c50fb726');
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'ae803474-2af0-4a57-809b-9ad8c50fb726'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Creado', '7b54358d-281c-40fa-b416-9f07bbcf01bd', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = 'ae803474-2af0-4a57-809b-9ad8c50fb726'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Deshabilitado', 'd1668eae-65b7-4050-83e9-8dd41edc53e9', true);




