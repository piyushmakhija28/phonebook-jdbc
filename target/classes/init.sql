# create phonebook database
CREATE DATABASE phonebook
    WITH
    OWNER = postgres
    TEMPLATE = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

# create phonebook schema
CREATE SCHEMA phonebook
    AUTHORIZATION postgres;
 
# create phonebook table        
-- Table: phonebook.phonebook

-- DROP TABLE IF EXISTS phonebook.phonebook;

CREATE TABLE IF NOT EXISTS phonebook.phonebook
(
    id bigint NOT NULL,
    first_name character varying COLLATE pg_catalog."default" NOT NULL,
    last_name character varying COLLATE pg_catalog."default" NOT NULL,
    mobile_number character varying COLLATE pg_catalog."default" NOT NULL,
    email character varying COLLATE pg_catalog."default" NOT NULL,
    dob date NOT NULL,
    country character varying COLLATE pg_catalog."default" NOT NULL,
    city character varying COLLATE pg_catalog."default" NOT NULL,
    state character varying COLLATE pg_catalog."default" NOT NULL,
    district character varying COLLATE pg_catalog."default" NOT NULL,
    address character varying COLLATE pg_catalog."default" NOT NULL,
    nick_name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT phonebook_pkey PRIMARY KEY (id),
    CONSTRAINT email_unique_key UNIQUE (email),
    CONSTRAINT mobile_unique_key UNIQUE (mobile_number)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS phonebook.phonebook
    OWNER to postgres;

# sequence 
create sequence phonebook.phonebook_seq
    start 1
    increment 1
    NO MAXVALUE
    CACHE 1;
