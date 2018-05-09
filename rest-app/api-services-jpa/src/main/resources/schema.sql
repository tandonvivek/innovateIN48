DROP TABLE IF EXISTS widget_api, category_widget_rel, widget_ext, widget, category, hello_widget;

-- Sequence: category_cat_id_seq
CREATE SEQUENCE IF NOT EXISTS category_cat_id_seq
    INCREMENT 1
    START 51
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    
-- Table: category
CREATE TABLE IF NOT EXISTS category
(
    cat_id bigint NOT NULL DEFAULT nextval('category_cat_id_seq'::regclass),
    cat_nme character varying(255) COLLATE pg_catalog."default" NOT NULL,
    rout character varying(255) COLLATE pg_catalog."default" NOT NULL,
    is_dactv boolean NOT NULL DEFAULT true,
    CONSTRAINT category_pkey PRIMARY KEY (cat_id),
    CONSTRAINT category_cat_nme_key UNIQUE (cat_nme),
    CONSTRAINT category_rout_key UNIQUE (rout)
)
WITH (
    OIDS = FALSE
);

-- Sequence: widget_wdgt_id_seq
CREATE SEQUENCE IF NOT EXISTS widget_wdgt_id_seq
    INCREMENT 1
    START 101
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- Table: widget
CREATE TABLE IF NOT EXISTS widget
(
    wdgt_id bigint NOT NULL DEFAULT nextval('widget_wdgt_id_seq'::regclass),
    wdgt_nme character varying(255) COLLATE pg_catalog."default" NOT NULL,
    html_loctn character varying(255) COLLATE pg_catalog."default",
    js_loctn character varying(255) COLLATE pg_catalog."default",
    lst_usg_ts timestamp without time zone,
    usg_cnt bigint NOT NULL DEFAULT 0,
    is_dactv boolean NOT NULL DEFAULT true,
    cmpt_nme character varying(255) COLLATE pg_catalog."default" NOT NULL,
    wdgt_desc character varying COLLATE pg_catalog."default",
    wdgt_img bytea,
    CONSTRAINT widget_pkey PRIMARY KEY (wdgt_id),
    CONSTRAINT category_wdgt_nme_key UNIQUE (wdgt_nme),
    CONSTRAINT category_cmpt_nme_key UNIQUE (cmpt_nme)
)
WITH (
    OIDS = FALSE
);

-- Sequence: category_widget_rel_id_seq
CREATE SEQUENCE IF NOT EXISTS category_widget_rel_id_seq
    INCREMENT 1
    START 101
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- Table: category_widget_rel
CREATE TABLE IF NOT EXISTS category_widget_rel
(
    id bigint NOT NULL DEFAULT nextval('category_widget_rel_id_seq'::regclass),
    cat_id bigint NOT NULL DEFAULT 1,
    wdgt_id bigint NOT NULL,
    CONSTRAINT category_widget_rel_pkey PRIMARY KEY (id),
    CONSTRAINT category_widget_rel_ukey UNIQUE (cat_id, wdgt_id),
    CONSTRAINT category_widget_rel_cat_id_fkey FOREIGN KEY (cat_id)
        REFERENCES category (cat_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET DEFAULT,
    CONSTRAINT category_widget_rel_wdgt_id_fkey FOREIGN KEY (wdgt_id)
        REFERENCES widget (wdgt_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
);

-- Sequence: widget_api_id_seq
CREATE SEQUENCE IF NOT EXISTS widget_api_id_seq
    INCREMENT 1
    START 201
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- Table: widget_api
CREATE TABLE IF NOT EXISTS widget_api
(
    id bigint NOT NULL DEFAULT nextval('widget_api_id_seq'::regclass),
    wdgt_id bigint NOT NULL,
    api_oper character varying(255) COLLATE pg_catalog."default" NOT NULL,
    api_nme character varying(255) COLLATE pg_catalog."default" NOT NULL,
    api_end_pt character varying(255) COLLATE pg_catalog."default" NOT NULL,
    api_store character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT widget_api_pkey PRIMARY KEY (id),
    CONSTRAINT widget_api_ukey UNIQUE (wdgt_id, api_oper, api_end_pt),
    CONSTRAINT widget_api_wdgt_id_fkey FOREIGN KEY (wdgt_id)
        REFERENCES widget (wdgt_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
);

-- Table: widget_ext
CREATE TABLE IF NOT EXISTS widget_ext
(
    wdgt_id bigint NOT NULL,
    CONSTRAINT widget_ext_pkey PRIMARY KEY (wdgt_id),
    CONSTRAINT widget_ext_wdgt_id_fkey FOREIGN KEY (wdgt_id)
        REFERENCES widget (wdgt_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
);

-- Sequence: hello_widget_id_seq
CREATE SEQUENCE IF NOT EXISTS hello_widget_id_seq
    INCREMENT 1
    START 201
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- Table: hello_widget
CREATE TABLE IF NOT EXISTS hello_widget
(
    id bigint NOT NULL DEFAULT nextval('hello_widget_id_seq'::regclass),
    msg character varying(255) COLLATE pg_catalog."default" NOT NULL,
    img bytea,
    CONSTRAINT hello_widget_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);