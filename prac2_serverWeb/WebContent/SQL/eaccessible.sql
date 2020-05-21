CREATE OR REPLACE FUNCTION public.get_accessibilitat_local(
	codacc bigint,
	codloc bigint,
	codcarac bigint,
	ver character)
    RETURNS TABLE(codiaccessibilitat bigint, codilocal bigint, codicaracteristica bigint, valor bigint, verificat character, nomcaracteristicaca character varying, nomcaracteristicaes character varying, nomcaracteristicaen character varying, tipo integer) 
    LANGUAGE 'sql'

    COST 100
    VOLATILE 
    ROWS 1000
    
AS $BODY$
    Select eaccessible.accessibilitat.*,
           eaccessible.caracteristica.nomcaracteristicaca,
           eaccessible.caracteristica.nomcaracteristicaes,
           eaccessible.caracteristica.nomcaracteristicaen,
           eaccessible.caracteristica.tipo
    from   eaccessible.accessibilitat inner join 
           eaccessible.caracteristica on eaccessible.accessibilitat.codicaracteristica = eaccessible.caracteristica.codicaracteristica
    where (codAcc=0 or eaccessible.accessibilitat.codiaccessibilitat = codAcc) and
          (codLoc=0 or eaccessible.accessibilitat.codilocal = codLoc) and
          (codCarac=0 or eaccessible.accessibilitat.codicaracteristica = codCarac) and 
          (ver='' or eaccessible.accessibilitat.verificat = ver)
$BODY$;

ALTER FUNCTION public.get_accessibilitat_local(bigint, bigint, bigint, character)
    OWNER TO postgres;
    
    
    
CREATE OR REPLACE FUNCTION public.get_locals(
	cod bigint,
	codtip bigint,
	codcar bigint,
	nomc character varying,
	nomv character varying,
	num bigint,
	noml character varying,
	obs character varying,
	ver character,
	codcarac bigint)
    RETURNS TABLE(codilocal bigint, coditipolocal bigint, codicarrer bigint, nomcarrer character varying, nomvia character varying, numero bigint, nomlocal character varying, observacions character varying, verificat character, nomcaracteristicaca character varying, nomcaracteristicaes character varying, nomcaracteristicaen character varying) 
    LANGUAGE 'sql'

    COST 100
    VOLATILE 
    ROWS 10000
    
AS $BODY$
    Select MIN(eaccessible.local.codilocal) ,
		   MAX(eaccessible.local.coditipolocal) ,
		   MAX(eaccessible.local.codicarrer) ,
		   MAX(eaccessible.local.nomcarrer) ,
		   MAX(eaccessible.local.nomvia) ,
		   MAX(eaccessible.local.numero) ,
		   MAX(eaccessible.local.nomlocal) ,
		   MAX(eaccessible.local.observacions) ,
		   MAX(eaccessible.local.verificat) ,
		   MAX(eaccessible.caracteristica.nomcaracteristicaca),
		   MAX(eaccessible.caracteristica.nomcaracteristicaes),
		   MAX(eaccessible.caracteristica.nomcaracteristicaen)
		   from eaccessible.local 
		   left outer join eaccessible.accessibilitat 
		  		on eaccessible.accessibilitat.codilocal = eaccessible.local.codilocal
		   left outer join eaccessible.caracteristica
		   		on eaccessible.accessibilitat.codicaracteristica = eaccessible.caracteristica.codicaracteristica
	where (cod=0 or eaccessible.local.codilocal = cod) and
		  (codtip=0 or eaccessible.local.coditipolocal = codtip) and
		  (codcar=0 or eaccessible.local.codicarrer = codcar) and
		  (nomc='' or eaccessible.local.nomcarrer like '%'||UPPER (nomc)||'%') and
		  (nomv='' or eaccessible.local.nomvia = UPPER (nomv)) and
		  (num=0 or eaccessible.local.numero = num) and
		  (noml='' or eaccessible.local.nomlocal like '%' || UPPER (noml) || '%') and
		  (obs='' or eaccessible.local.observacions like '%'|| UPPER (obs) || '%') and
		  (ver='' or eaccessible.local.verificat = ver) and
		  (codcarac = 0 or eaccessible.caracteristica.codicaracteristica = codcarac)
	group by eaccessible.local.nomlocal
	order by Max(eaccessible.local.codilocal);
$BODY$;

ALTER FUNCTION public.get_locals(bigint, bigint, bigint, character varying, character varying, bigint, character varying, character varying, character, bigint)
    OWNER TO postgres;
    
    
CREATE SEQUENCE public.local_codilocal_seq
		INCREMENT 1
		START 5162
		MINVALUE 1
		MAXVALUE 9223372036854775807
		CACHE 1;

		ALTER SEQUENCE public.local_codilocal_seq
			OWNER TO postgres;
			
ALTER TABLE eaccessible.local
		ALTER COLUMN  codilocal SET DEFAULT nextval('local_codilocal_seq')
		
CREATE SEQUENCE public.caracteristicatipolocal_codicaracteristicatipolocal_seq
    INCREMENT 1
    START 5150
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.caracteristicatipolocal_codicaracteristicatipolocal_seq
    OWNER TO postgres;

ALTER TABLE eaccessible.caracteristicatipolocal ALTER codicaracteristicatipolocal SET DEFAULT NEXTVAL('caracteristicatipolocal_codicaracteristicatipolocal_seq');

CREATE SEQUENCE public.accessibilitat_codiaccessibilitat_seq
    INCREMENT 1
    START 5150
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.accessibilitat_codiaccessibilitat_seq
    OWNER TO postgres;

ALTER TABLE eaccessible.accessibilitat ALTER codiaccessibilitat SET DEFAULT NEXTVAL('accessibilitat_codiaccessibilitat_seq');