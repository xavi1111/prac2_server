CREATE TABLE log.incidencia
(
    idincidencia bigint NOT NULL DEFAULT nextval('incidencia_idincidencia_seq'::regclass),
    datahora timestamp without time zone,
    coditipusincidencia integer,
    idregistre bigint,
    nomtaula character varying COLLATE pg_catalog."default",
    CONSTRAINT "pk_Incidencia" PRIMARY KEY (idincidencia),
    CONSTRAINT "fk_Incidencia_TipusIncidencia" FOREIGN KEY (coditipusincidencia)
        REFERENCES log.tipusincidencia (coditipusincidencia) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE log.incidencia
    OWNER to postgres;

CREATE SEQUENCE public.incidencia_idincidencia_seq
    INCREMENT 1
    START 145
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.incidencia_idincidencia_seq
    OWNER TO postgres;

ALTER TABLE log.incidencia ALTER idregistre SET DEFAULT NEXTVAL('incidencia_idincidencia_seq');