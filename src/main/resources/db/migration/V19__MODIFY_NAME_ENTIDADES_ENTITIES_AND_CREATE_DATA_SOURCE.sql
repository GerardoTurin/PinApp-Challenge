ALTER TABLE entidades RENAME TO entities;



create table data_source (
  "id" serial PRIMARY KEY,
  "url_connection" varchar(200),
  "user_connection" varchar(200),
  "password_connection" varchar(200),
  "driver_connection" varchar(200),
  "endpoint_connection" varchar(200),
  "entity_id" bigint,
  "company_id" bigint,
  "created_at" timestamp,
  "created_user" bigint
);

alter table data_source add constraint fk_dataSource_entities foreign key (entity_id) references entities(id);

alter table data_source add constraint fk_dataSource_company foreign key (company_id) references companies(id);