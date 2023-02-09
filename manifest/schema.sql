CREATE ROLE auth_service WITH
    NOSUPERUSER
    NOCREATEDB
    NOCREATEROLE
    INHERIT
    LOGIN
    NOREPLICATION
    NOBYPASSRLS
    CONNECTION LIMIT -1
    PASSWORD 'm6qrT)*Myk';

-- DROP SCHEMA auth_service;

CREATE SCHEMA auth_service AUTHORIZATION auth_service;


-- Permissions

GRANT ALL ON SCHEMA auth_service TO auth_service;

-- auth_service.oauth_access_token definition

-- Drop table

-- DROP TABLE auth_service.oauth_access_token;

CREATE TABLE auth_service.oauth_access_token (
	token_id varchar(256) NULL,
	"token" bytea NULL,
	authentication_id varchar(256) NOT NULL,
	user_name varchar(256) NULL,
	client_id varchar(256) NULL,
	authentication bytea NULL,
	refresh_token varchar(256) NULL,
	CONSTRAINT oauth_access_token_pkey PRIMARY KEY (authentication_id)
);

-- Permissions

ALTER TABLE auth_service.oauth_access_token OWNER TO auth_service;
GRANT ALL ON TABLE auth_service.oauth_access_token TO auth_service;


-- auth_service.oauth_client_details definition

-- Drop table

-- DROP TABLE auth_service.oauth_client_details;

CREATE TABLE auth_service.oauth_client_details (
	client_id varchar(256) NOT NULL,
	client_secret varchar(256) NOT NULL,
	resource_ids varchar(256) NULL DEFAULT NULL::character varying,
	"scope" varchar(256) NULL DEFAULT NULL::character varying,
	authorized_grant_types varchar(256) NULL DEFAULT NULL::character varying,
	web_server_redirect_uri varchar(256) NULL DEFAULT NULL::character varying,
	authorities varchar(256) NULL DEFAULT NULL::character varying,
	access_token_validity int4 NULL,
	refresh_token_validity int4 NULL,
	additional_information varchar(4096) NULL DEFAULT NULL::character varying,
	autoapprove varchar(256) NULL DEFAULT 'TRUE'::character varying,
	CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id)
);

-- Permissions

ALTER TABLE auth_service.oauth_client_details OWNER TO auth_service;
GRANT ALL ON TABLE auth_service.oauth_client_details TO auth_service;


-- auth_service.oauth_refresh_token definition

-- Drop table

-- DROP TABLE auth_service.oauth_refresh_token;

CREATE TABLE auth_service.oauth_refresh_token (
	token_id varchar(255) NULL,
	"token" bytea NULL,
	authentication bytea NULL
);

-- Permissions

ALTER TABLE auth_service.oauth_refresh_token OWNER TO auth_service;
GRANT ALL ON TABLE auth_service.oauth_refresh_token TO auth_service;


-- auth_service."permission" definition

-- Drop table

-- DROP TABLE auth_service."permission";

CREATE TABLE auth_service."permission" (
	id bigserial NOT NULL,
	"name" varchar(60) NULL,
	CONSTRAINT permission_name_key UNIQUE (name),
	CONSTRAINT permission_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE auth_service."permission" OWNER TO auth_service;
GRANT ALL ON TABLE auth_service."permission" TO auth_service;


-- auth_service."role" definition

-- Drop table

-- DROP TABLE auth_service."role";

CREATE TABLE auth_service."role" (
	id bigserial NOT NULL,
	"name" varchar(60) NULL,
	CONSTRAINT role_name_key UNIQUE (name),
	CONSTRAINT role_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE auth_service."role" OWNER TO auth_service;
GRANT ALL ON TABLE auth_service."role" TO auth_service;


-- auth_service.users definition

-- Drop table

-- DROP TABLE auth_service.users;

CREATE TABLE auth_service.users (
	id bigserial NOT NULL,
	username varchar(50) NOT NULL,
	first_name varchar(50) NULL,
	middle_name varchar(50) NULL,
	last_name varchar(50) NULL,
	"password" varchar(255) NOT NULL,
	mobile_phone varchar(30) NULL,
	email varchar(255) NOT NULL,
	enabled bool NOT NULL,
	account_expired bool NOT NULL,
	credentials_expired bool NOT NULL,
	account_locked bool NOT NULL,
	CONSTRAINT users_mobile_phone_key UNIQUE (mobile_phone),
	CONSTRAINT users_pkey PRIMARY KEY (id),
	CONSTRAINT users_username_key UNIQUE (username)
);

-- Permissions

ALTER TABLE auth_service.users OWNER TO auth_service;
GRANT ALL ON TABLE auth_service.users TO auth_service;


-- auth_service.permission_role definition

-- Drop table

-- DROP TABLE auth_service.permission_role;

CREATE TABLE auth_service.permission_role (
	permission_id int8 NULL,
	role_id int8 NULL,
	CONSTRAINT permission_role_permission_id_fkey FOREIGN KEY (permission_id) REFERENCES auth_service."permission"(id),
	CONSTRAINT permission_role_role_id_fkey FOREIGN KEY (role_id) REFERENCES auth_service."role"(id)
);

-- Permissions

ALTER TABLE auth_service.permission_role OWNER TO auth_service;
GRANT ALL ON TABLE auth_service.permission_role TO auth_service;


-- auth_service.role_user definition

-- Drop table

-- DROP TABLE auth_service.role_user;

CREATE TABLE auth_service.role_user (
	role_id int8 NULL,
	user_id int8 NULL,
	CONSTRAINT role_user_role_id_fkey FOREIGN KEY (role_id) REFERENCES auth_service."role"(id),
	CONSTRAINT role_user_user_id_fkey FOREIGN KEY (user_id) REFERENCES auth_service.users(id)
);

-- Permissions

ALTER TABLE auth_service.role_user OWNER TO auth_service;
GRANT ALL ON TABLE auth_service.role_user TO auth_service;

INSERT INTO auth_service.oauth_client_details (client_id,client_secret,resource_ids,"scope",authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove) VALUES
	 ('USER_CLIENT_APP','{bcrypt}$2a$12$Z5sT5OvgmcrK9HhfhuZ4Qu8p2xayCJMAOKxubhR68qcztoSgh.9HC','','read,write','authorization_code,check_token,refresh_token,password',NULL,'',900,3600,'{}','true');

INSERT INTO auth_service."role" ("name") VALUES
	 ('ROLE_ADMIN'),
	 ('ROLE_USER');

INSERT INTO auth_service.users (username,first_name,middle_name,last_name,"password",mobile_phone,email,enabled,account_expired,credentials_expired,account_locked) VALUES
	 ('admin','admin','admin','admin','{bcrypt}$2a$12$Z5sT5OvgmcrK9HhfhuZ4Qu8p2xayCJMAOKxubhR68qcztoSgh.9HC','+1111111111','admin@mail.ru',true,false,false,false);

