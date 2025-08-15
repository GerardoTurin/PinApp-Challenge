INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '693f3612-5b96-4bc1-a394-1882047d6999'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Puerto', '2c92dba0-bc96-4f32-8200-e545da914e13', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '693f3612-5b96-4bc1-a394-1882047d6999'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Finca', '9f2d0c71-f415-499c-af18-a8bab616c2bd', true);
INSERT INTO config_details (config_header_id, company_id, name, code, system_parameter) VALUES ((SELECT id FROM config_headers WHERE code = '693f3612-5b96-4bc1-a394-1882047d6999'), (SELECT id FROM companies WHERE name = 'SOMAS'), 'Destino', 'c3ee58ff-bcb0-4c25-a281-41715036fd77', true);

--INSERT INTO config_detail_hierarchy (config_detail_id, parent_config_detail_id, son_config_detail_id, attribute) VALUES ((SELECT id FROM config_details WHERE code = '6bb48c58-2c80-4c47-a8ae-bf064d8f4291'), (SELECT id FROM config_details WHERE code = 'be2d313e-7ca4-47af-801d-c5e88a739c44'), (SELECT id FROM config_details WHERE code = '2c92dba0-bc96-4f32-8200-e545da914e13'), ('{"default": true, "posicion": 1}'));

--INSERT INTO config_detail_hierarchy (config_detail_id, parent_config_detail_id, son_config_detail_id, attribute) VALUES ((SELECT id FROM config_details WHERE code = '6bb48c58-2c80-4c47-a8ae-bf064d8f4291'), (SELECT id FROM config_details WHERE code = 'be2d313e-7ca4-47af-801d-c5e88a739c44'), (SELECT id FROM config_details WHERE code = '9f2d0c71-f415-499c-af18-a8bab616c2bd'), ('{"default": true, "posicion": 2}'));

--INSERT INTO config_detail_hierarchy (config_detail_id, parent_config_detail_id, son_config_detail_id, attribute) VALUES ((SELECT id FROM config_details WHERE code = '6bb48c58-2c80-4c47-a8ae-bf064d8f4291'), (SELECT id FROM config_details WHERE code = 'be2d313e-7ca4-47af-801d-c5e88a739c44'), (SELECT id FROM config_details WHERE code = 'c3ee58ff-bcb0-4c25-a281-41715036fd77'), ('{"default": true, "posicion": 3}'));

--INSERT INTO config_detail_hierarchy (config_detail_id, parent_config_detail_id, son_config_detail_id, attribute) VALUES ((SELECT id FROM config_details WHERE code = '6bb48c58-2c80-4c47-a8ae-bf064d8f4291'), (SELECT id FROM config_details WHERE code = 'a7a291c3-cef4-48e3-bdab-13abdaf83931'), (SELECT id FROM config_details WHERE code = 'c3ee58ff-bcb0-4c25-a281-41715036fd77'), ('{"default": true, "posicion": 1}'));

--INSERT INTO config_detail_hierarchy (config_detail_id, parent_config_detail_id, son_config_detail_id, attribute) VALUES ((SELECT id FROM config_details WHERE code = '6bb48c58-2c80-4c47-a8ae-bf064d8f4291'), (SELECT id FROM config_details WHERE code = 'a7a291c3-cef4-48e3-bdab-13abdaf83931'), (SELECT id FROM config_details WHERE code = '9f2d0c71-f415-499c-af18-a8bab616c2bd'), ('{"default": true, "posicion": 2}'));

--INSERT INTO config_detail_hierarchy (config_detail_id, parent_config_detail_id, son_config_detail_id, attribute) VALUES ((SELECT id FROM config_details WHERE code = '6bb48c58-2c80-4c47-a8ae-bf064d8f4291'), (SELECT id FROM config_details WHERE code = 'a7a291c3-cef4-48e3-bdab-13abdaf83931'), (SELECT id FROM config_details WHERE code = '2c92dba0-bc96-4f32-8200-e545da914e13'), ('{"default": true, "posicion": 3}'));

--INSERT INTO config_detail_hierarchy (config_detail_id, parent_config_detail_id, son_config_detail_id, attribute) VALUES ((SELECT id FROM config_details WHERE code = '6bb48c58-2c80-4c47-a8ae-bf064d8f4291'), (SELECT id FROM config_details WHERE code = 'f86304de-8648-4fb1-b848-18c057f47130'), (SELECT id FROM config_details WHERE code = '2c92dba0-bc96-4f32-8200-e545da914e13'), ('{"default": true, "posicion": 1}'));

--INSERT INTO config_detail_hierarchy (config_detail_id, parent_config_detail_id, son_config_detail_id, attribute) VALUES ((SELECT id FROM config_details WHERE code = '6bb48c58-2c80-4c47-a8ae-bf064d8f4291'), (SELECT id FROM config_details WHERE code = 'f86304de-8648-4fb1-b848-18c057f47130'), (SELECT id FROM config_details WHERE code = '2c92dba0-bc96-4f32-8200-e545da914e13'), ('{"default": true, "posicion": 2}'));

--INSERT INTO config_detail_hierarchy (config_detail_id, parent_config_detail_id, son_config_detail_id, attribute) VALUES ((SELECT id FROM config_details WHERE code = '6bb48c58-2c80-4c47-a8ae-bf064d8f4291'), (SELECT id FROM config_details WHERE code = 'f86304de-8648-4fb1-b848-18c057f47130'), (SELECT id FROM config_details WHERE code = '2c92dba0-bc96-4f32-8200-e545da914e13'), ('{"default": true, "posicion": 3}'));


 --INSERT INTO config_detail_hierarchy (config_detail_id, parent_config_detail_id, son_config_detail_id, attribute) VALUES ((SELECT id FROM config_details WHERE code = 'b16f3e65-5546-403b-9903-bb590fb7f1a9'), null, (SELECT id FROM config_details WHERE code = '152d958e-80d3-40c3-955d-c9e08fa15332'), ('{"default": true}'));



