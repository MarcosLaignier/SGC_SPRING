Criando tabela unidade Cemiterios
CREATE TABLE
    SGCUNIDADES
    (
        UNDCODIGO SERIAL PRIMARY KEY,
        UNDNOME VARCHAR(100),
        UNDENDERECO VARCHAR(70),
        UNDNUMERO INTEGER,
        UNDCIDADE VARCHAR(60),
        UNDESTADO VARCHAR(2),
        UNDRESPONSAVEL VARCHAR(18)
    )

insert into SGCUNIDADES(UNDNOME,UNDENDERECO ,UNDNUMERO ,UNDCIDADE ,UNDESTADO ,UNDRESPONSAVEL ) values( 'CEMITERIO NOVO','RUA NOVA',17,'LAGOA SANTA','MG','NOVO')


####################################################################

Criando tabelas Sepulturas

    CREATE TABLE
    SGCSEPULTURAS
    (
        SEPCODIGO SERIAL ,
        SEPDESCRICAO VARCHAR(100),
        SEPIDCEMITERIO INT,
        SEPCEMITERIO VARCHAR(100),
        PRIMARY KEY(SEPCODIGO),
        FOREIGN KEY(SEPIDCEMITERIO) REFERENCES SGCUNIDADES(UNDCODIGO)

        
    );

ALTER TABLE sgcsepulturas RENAME CONSTRAINT "sgcsepulturas_sepidcemiterio_fkey" TO "fk_sepidcemiterio";


insert into SGCSEPULTURAS(SEPDESCRICAO,SEPIDCEMITERIO,SEPCEMITERIO) values ('Sepultura 01.01 XG',1,'CEMITERIO NOVO')

####################################################################

Criando tabela Falecidos

CREATE TABLE
    SGCFALECIDOS
    (
        FALCODIGO SERIAL,
        FALNOME VARCHAR(100),
        FALCPF VARCHAR(11),
        FALSEXO VARCHAR(10),
        FALNASCIMENTO DATE,
        FALNATURALIDADE VARCHAR(30),
        FALFALECIMENTO DATE,
        FALMEDRESP VARCHAR(60),
    );

    
insert into SGCFALECIDOS values ('1','Mortinho da silva','00000000000','Masculino','01-01-2000','Brasileiro','01-01-2020','Dr Joao 22')

####################################################################

     Criando tabelas Funerarias

    CREATE TABLE
    SGCFUNERARIAS
    (
        FUNCODIGO SERIAL,
        FUNDESCRICAO VARCHAR(100),
        FUNCIDADE VARCHAR(100),
        FUNENDERECO VARCHAR(100),
        FUNNUMERO INTEGER
        
    );

    insert into SGCFUNERARIAS VALUES ('1','FUNERARIA DOIS IRMAOS', 'PEDRO LEOPOLDO', 'RUA DIAMANTE', '77')

####################################################################

    Criando Tabela Sepultamentos

    CREATE TABLE
    SGCSEPULTAMENTOS
    (
        SEPULCODIGO SERIAL,
        SEPULIDFALECIDO INTEGER,
        SEPULFALECIDO  VARCHAR(255),
        SEPULCPFFAL VARCHAR(11),
        SEPULIDFUNERARIA INTEGER,
        SEPULFUNERARIA VARCHAR(255),
        SEPULIDCEMITERIO INTEGER,
        SEPULCEMITERIO VARCHAR(255),
        SEPULIDSEPULTURA INTEGER,
        SEPULSEPULTURA VARCHAR(255),
        SEPDATASEPULTAMENTO DATE,
        SEPDATAFALECIMENTO DATE,
        FOREIGN KEY(SEPULIDFALECIDO) REFERENCES SGCFALECIDOS(FALCODIGO),
        FOREIGN KEY(SEPULIDFUNERARIA) REFERENCES SGCFUNERARIAS(FUNCODIGO),
        FOREIGN KEY(SEPULIDCEMITERIO) REFERENCES SGCUNIDADES(UNDCODIGO),
        FOREIGN KEY(SEPULIDSEPULTURA) REFERENCES SGCSEPULTURAS(SEPULCODIGO),

    );

    insert into SGCSEPULTAMENTOS VALUES ('1','Mortinho da Silva','00000000000','FUNERARIA DOIS IRMAOS','CEMITERIO DO MURTAO','SEPULTURA 01.01 P','01-01-2020','30-12-2019')

      ####################################################################
Criando tabela de configuracoes

CREATE TABLE SGCCONFIGURACOES(
       SGCMUNICIPIO VARCHAR(255),
       SGCPATHLOGO VARCHAR(255),
       SGCPATHIMG VARCHAR(255)
)

insert into SGCCONFIGURACOES VALUES('PEDRO LEOPOLDO','','')