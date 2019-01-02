-- ===========================
-- OAuth2 implementation : tables
-- ===========================
drop table if exists oauth_client_details;
create table oauth_client_details (
  -- client_id : chaîne de caractères générée de façon aléatoire qui identifie une application cliente de manière unique
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  -- client_secret : chaîne de caractères représentant la clé secrète du client 
  -- et qui sera utilisée lors de l’appel à certaines API nécessitant une entête HTTP Authorization
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token LONGVARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

drop table if exists oauth_access_token;
CREATE TABLE oauth_access_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token BLOB,
  authentication_id VARCHAR(256) DEFAULT NULL,
  user_name VARCHAR(256) DEFAULT NULL,
  client_id VARCHAR(256) DEFAULT NULL,
  authentication BLOB,
  refresh_token VARCHAR(256) DEFAULT NULL
);

drop table if exists oauth_refresh_token;
CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token BLOB,
  authentication BLOB
);

drop table if exists oauth_code;
create table oauth_code (
	code VARCHAR(255), authentication LONGVARBINARY
);

drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt DATETIME,
    lastModifiedAt DATETIME
);



-- ===========================
-- Users implementation : tables
-- ===========================
drop table if exists permission;
CREATE TABLE permission (
  	id numeric(20) NOT NULL IDENTITY,
  	name varchar(60) NOT NULL,
  	created_on datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	updated_on datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	version numeric(20) NOT NULL DEFAULT '0',
  	PRIMARY KEY (id),
  	UNIQUE (name)
);

drop table if exists role;
CREATE TABLE role (
  	id numeric(20)  NOT NULL IDENTITY,
  	name varchar(60) NOT NULL,
  	created_on datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	updated_on datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	version numeric(20)  NOT NULL DEFAULT '0',
  	PRIMARY KEY (id),
  	UNIQUE (name)
);

drop table if exists permission_role;
CREATE TABLE permission_role (
  	permission_id numeric(20)  NOT NULL,
  	role_id numeric(20)  NOT NULL,
  	created_on datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	updated_on datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	version numeric(20)  NOT NULL DEFAULT '0',
  	PRIMARY KEY (permission_id,role_id),
  	CONSTRAINT permission_role_fk1 FOREIGN KEY (permission_id) REFERENCES permission (id) ON DELETE CASCADE ON UPDATE CASCADE,
  	CONSTRAINT permission_role_fk2 FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE
);

drop table if exists user_;
CREATE TABLE user_ (
  	id numeric(20)  NOT NULL IDENTITY,
  	username varchar(24) NOT NULL,
  	password varchar(200) NOT NULL,
  	email varchar(255) NOT NULL,
  	enabled int NOT NULL,
  	account_expired int NOT NULL,
  	credentials_expired int NOT NULL,
  	account_locked int NOT NULL,
  	created_on datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	updated_on datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	version numeric(20)  NOT NULL DEFAULT '0',
  	PRIMARY KEY (id),
  	UNIQUE (username),
  	UNIQUE (email)
);

drop table if exists role_user;
CREATE TABLE role_user (
  	role_id numeric(20)  NOT NULL,
  	user_id numeric(20)  NOT NULL,
  	created_on datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	updated_on datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	version numeric(20)  NOT NULL DEFAULT '0',
  	PRIMARY KEY (role_id,user_id),
  	CONSTRAINT role_user_fk1 FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE,
  	CONSTRAINT role_user_fk2 FOREIGN KEY (user_id) REFERENCES user_ (id) ON DELETE CASCADE ON UPDATE CASCADE
);




-- ===========================
-- Data Users implementation
-- ===========================

INSERT INTO oauth_client_details VALUES 
	('adminapp','my_resource_id','$2a$10$03RZZe10pH2BKYNGvW.dG.Qpl3g/4L7mwxc.5FPjjuPncUX983K6G','ROLE_ADMIN','password,refresh_token',NULL,NULL,900,3600,'{}',NULL);

INSERT INTO permission VALUES 
	(1,'DELETE','1970-01-01 00:00:00','1970-01-01 00:00:00',0),
	(2,'CREATE','1970-01-01 00:00:00','1970-01-01 00:00:00',0),
	(3,'UPDATE','1970-01-01 00:00:00','1970-01-01 00:00:00',0),
	(4,'READ','1970-01-01 00:00:00','1970-01-01 00:00:00',0);
	
INSERT INTO role VALUES 
	(1,'ROLE_ADMIN','1970-01-01 00:00:00','1970-01-01 00:00:00',0),
	(2,'ROLE_USER','1970-01-01 00:00:00','1970-01-01 00:00:00',0);
	
INSERT INTO permission_role VALUES 
	-- Admin has all the roles.
	(1,1,'1970-01-01 00:00:00','1970-01-01 00:00:00',0),
	(2,1,'1970-01-01 00:00:00','1970-01-01 00:00:00',0),
	(3,1,'1970-01-01 00:00:00','1970-01-01 00:00:00',0),
	(4,1,'1970-01-01 00:00:00','1970-01-01 00:00:00',0),
	-- User can be only read
	(4,2,'1970-01-01 00:00:00','1970-01-01 00:00:00',0);
	
INSERT INTO user_ VALUES 
	(1,'admin','$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi','admin@example.com',1,0,0,0,'1970-01-01 00:00:00','1970-01-01 00:00:00',0),
	(2,'user','$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi','user@example.com',1,0,0,0,'1970-01-01 00:00:00','1970-01-01 00:00:00',0);
	
INSERT INTO role_user VALUES 
	(1,1,'1970-01-01 00:00:00','1970-01-01 00:00:00',0),
	(2,2,'1970-01-01 00:00:00','1970-01-01 00:00:00',0);