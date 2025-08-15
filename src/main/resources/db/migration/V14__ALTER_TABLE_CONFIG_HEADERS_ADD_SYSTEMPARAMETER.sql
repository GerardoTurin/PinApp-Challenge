ALTER TABLE config_headers ADD COLUMN system_parameter boolean default false;

UPDATE config_headers SET system_parameter = true WHERE code = '7633d204-2365-4f9e-9dbe-e90e17e142d2';
UPDATE config_headers SET system_parameter = false WHERE code = '153b272d-5ac2-4843-8d21-feea5a538816';
UPDATE config_headers SET system_parameter = false WHERE code = '693f3612-5b96-4bc1-a394-1882047d6999';
UPDATE config_headers SET system_parameter = true WHERE code = 'dce67113-ba6c-45f0-aaa2-2584d64785e2';
UPDATE config_headers SET system_parameter = false WHERE code = 'db3a4a18-0195-467b-bd93-a4d3c25a0fc0';
UPDATE config_headers SET system_parameter = true WHERE code = '060e6e46-af3c-4ddd-b79f-0bb9e3a75d14';
UPDATE config_headers SET system_parameter = true WHERE code = '85a59806-6e74-4a11-9d79-446cd1830038';
UPDATE config_headers SET system_parameter = true WHERE code = 'dc1fc281-8f6a-4fac-b513-a2b2b49ef705';
UPDATE config_headers SET system_parameter = false WHERE code = '45a91b5a-fd60-4bae-9162-e2a09143e83a';
UPDATE config_headers SET system_parameter = false WHERE code = '18a0a3cc-5773-43e7-9e3a-452875a5dbbc';
UPDATE config_headers SET system_parameter = false WHERE code = '54002c25-d0aa-4655-9566-1a771df353d1';
UPDATE config_headers SET system_parameter = false WHERE code = 'a18ea49b-b4ac-41c8-86ec-5a7e6cf0a6ec';
UPDATE config_headers SET system_parameter = false WHERE code = 'd473a5fe-e07e-4274-bd22-e5471b11aa40';
UPDATE config_headers SET system_parameter = false WHERE code = 'e4194d8d-ba9e-4957-9107-b0c5cddd1fad';
UPDATE config_headers SET system_parameter = false WHERE code = 'ae803474-2af0-4a57-809b-9ad8c50fb726';
