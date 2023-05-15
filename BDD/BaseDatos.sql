-- Se debe crear la base de datos nttdata


CREATE TABLE PERSONA(
identificacion character varying(13) NOT NULL,
nombre character varying(200) NOT NULL,
genero character varying(1) NOT NULL,
edad int NOT NULL,
direccion character varying(200) NOT NULL,
telefono character varying(10),
CONSTRAINT pk_persona PRIMARY KEY (identificacion)
);

CREATE TABLE CLIENTE(
clienteid int NOT NULL,
contrasena character varying(200) NOT NULL,
estado character varying(1) NOT NULL,
identificacion character varying(13) NOT NULL,
CONSTRAINT pk_cliente PRIMARY KEY (clienteid),
CONSTRAINT fk_cliente_persona FOREIGN KEY (identificacion) REFERENCES PERSONA(identificacion) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE CUENTA(
numero_cuenta character varying(13) NOT NULL,
tipo_cuenta character varying(1) NOT NULL,
saldo_inicial money NOT NULL, 
estado character varying(1) NOT NULL,
clienteid int NOT NULL,
CONSTRAINT pk_cuenta PRIMARY KEY (numero_cuenta),
CONSTRAINT fk_cuenta_cliente FOREIGN KEY (clienteid) REFERENCES CLIENTE(clienteid) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE MOVIMIENTO(
codigo int GENERATED ALWAYS AS IDENTITY,
fecha date NOT NULL,
tipo_movimiento character varying(1) NOT NULL,
valor money NOT NULL, 
saldo money NOT NULL,
numero_cuenta character varying(13) NOT NULL,
CONSTRAINT pk_movimiento PRIMARY KEY (codigo),
CONSTRAINT fk_movimiento_cuenta FOREIGN KEY (numero_cuenta) REFERENCES CUENTA(numero_cuenta) ON DELETE RESTRICT ON UPDATE CASCADE
);