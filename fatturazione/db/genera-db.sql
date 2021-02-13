CREATE TABLE FATTURAZIONE.attivita (
    id_attivita  INT NOT NULL,
    id_fattura   INT NOT NULL,
    descrizione  VARCHAR(500) NOT NULL,
    importo_netto DECIMAL(10,2)
);

ALTER TABLE FATTURAZIONE.attivita ADD CONSTRAINT attivita_pk PRIMARY KEY ( id_attivita );

CREATE TABLE FATTURAZIONE.c_comune (
    cod_com      VARCHAR(8) NOT NULL,
    cod_prov     VARCHAR(8 ) NOT NULL,
    descrizione  VARCHAR(200)
);

ALTER TABLE FATTURAZIONE.c_comune ADD CONSTRAINT c_comune_pk PRIMARY KEY ( cod_com,
                                                                           cod_prov );
                                                                           

CREATE TABLE FATTURAZIONE.c_provincia (
    cod_prov     VARCHAR(8) NOT NULL,
    descrizione  VARCHAR(200),
    sigla        VARCHAR(2)
);

ALTER TABLE FATTURAZIONE.c_provincia ADD CONSTRAINT c_provincia_pk PRIMARY KEY ( cod_prov );


CREATE TABLE FATTURAZIONE.cliente (
    id_cliente     INT NOT NULL,
    denominazione   VARCHAR(500) NOT NULL,
    nickname        VARCHAR(100),
    piva            VARCHAR(16),
    codice_fiscale  VARCHAR(16),
    cod_prov        VARCHAR(8) NOT NULL,
    cod_com         VARCHAR(8) NOT NULL,
    indirizzo       VARCHAR(500) NOT NULL,
    cap             VARCHAR(5),
    telefono        VARCHAR(20),
    email           VARCHAR(50)
);

ALTER TABLE fatturazione.cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( id_cliente );

ALTER TABLE fatturazione.cliente ADD CONSTRAINT cliente__un UNIQUE ( piva );

ALTER TABLE fatturazione.cliente ADD CONSTRAINT cliente__unv1 UNIQUE ( codice_fiscale );


CREATE TABLE fatturazione.fattura (
    id_fattura      INT NOT NULL,
    id_cliente      INT NOT NULL,
    tipo            VARCHAR(8) NOT NULL,
    numero_fattura  VARCHAR(20),
    dt_fattura      DATE,
    id_bollo        VARCHAR(14),
    importo_netto   DECIMAL(10, 2) NOT NULL,
    iva             DECIMAL(10, 2) NOT NULL,
    oggetto         VARCHAR(500) NOT NULL,
    pagata          VARCHAR(1)
);

ALTER TABLE fatturazione.fattura ADD CONSTRAINT fattura_pk PRIMARY KEY ( id_fattura );

CREATE TABLE fatturazione.tipo_fattura (
    cod          VARCHAR(8) NOT NULL,
    descrizione  VARCHAR(100) NOT NULL
);

ALTER TABLE fatturazione.tipo_fattura ADD CONSTRAINT tipo_fattura_pk PRIMARY KEY ( cod );


ALTER TABLE fatturazione.attivita
    ADD CONSTRAINT attivita_fattura_fk FOREIGN KEY ( id_fattura )
        REFERENCES fatturazione.fattura ( id_fattura );

ALTER TABLE fatturazione.c_comune
    ADD CONSTRAINT c_comune_c_provincia_fk FOREIGN KEY ( cod_prov )
        REFERENCES fatturazione.c_provincia ( cod_prov );

ALTER TABLE fatturazione.cliente
    ADD CONSTRAINT cliente_c_comune_fk FOREIGN KEY ( cod_com,
                                                     cod_prov )
        REFERENCES fatturazione.c_comune ( cod_com,
                                           cod_prov );

ALTER TABLE fatturazione.fattura
    ADD CONSTRAINT fattura_cliente_fk FOREIGN KEY ( id_cliente )
        REFERENCES fatturazione.cliente ( id_cliente );

ALTER TABLE fatturazione.fattura
    ADD CONSTRAINT fattura_tipo_fattura_fk FOREIGN KEY ( tipo )
        REFERENCES fatturazione.tipo_fattura ( cod );
