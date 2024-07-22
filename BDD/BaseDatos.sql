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
saldo_inicial decimal NOT NULL, 
estado character varying(1) NOT NULL,
clienteid int NOT NULL,
CONSTRAINT pk_cuenta PRIMARY KEY (numero_cuenta),
CONSTRAINT fk_cuenta_cliente FOREIGN KEY (clienteid) REFERENCES CLIENTE(clienteid) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE MOVIMIENTO(
codigo int GENERATED ALWAYS AS IDENTITY,
fecha date NOT NULL,
tipo_movimiento character varying(1) NOT NULL,
valor decimal NOT NULL, 
saldo decimal NOT NULL,
estado character varying(1) NOT NULL,
numero_cuenta character varying(13) NOT NULL,
CONSTRAINT pk_movimiento PRIMARY KEY (codigo),
CONSTRAINT fk_movimiento_cuenta FOREIGN KEY (numero_cuenta) REFERENCES CUENTA(numero_cuenta) ON DELETE RESTRICT ON UPDATE CASCADE
);

/* POBLADO DE TABLAS */
INSERT INTO public.persona(
	identificacion, nombre, genero, edad, direccion, telefono)
	VALUES ('1764325491', 'Jose Lema', 'M', 25, 'Otavalo sn y principal', '098254785');
INSERT INTO public.persona(
	identificacion, nombre, genero, edad, direccion, telefono)
	VALUES ('0804362903', 'Marianela Montalvo', 'F', 34, 'Amazonas y NNUU', '097548965');
INSERT INTO public.persona(
	identificacion, nombre, genero, edad, direccion, telefono)
	VALUES ('1064792534', 'Juan Osorio', 'M', 34, '13 junio y Equinoccial', '098874587');

INSERT INTO public.cliente(
	clienteid, contrasena, estado, identificacion)
	VALUES (1, '1234', 'T', '1764325491');
INSERT INTO public.cliente(
	clienteid, contrasena, estado, identificacion)
	VALUES (2, '1234', 'T', '0804362903');
INSERT INTO public.cliente(
	clienteid, contrasena, estado, identificacion)
	VALUES (3, '1245', 'T', '1064792534');


INSERT INTO public.cuenta(
	numero_cuenta, tipo_cuenta, saldo_inicial, estado, clienteid)
	VALUES ('478758', 'A', 2000, 'T', 1);
INSERT INTO public.cuenta(
	numero_cuenta, tipo_cuenta, saldo_inicial, estado, clienteid)
	VALUES ('225487', 'C', 100, 'T', 2);
INSERT INTO public.cuenta(
	numero_cuenta, tipo_cuenta, saldo_inicial, estado, clienteid)
	VALUES ('495878', 'A', 0, 'T', 3);
INSERT INTO public.cuenta(
	numero_cuenta, tipo_cuenta, saldo_inicial, estado, clienteid)
	VALUES ('496825', 'A', 540, 'T', 2);
INSERT INTO public.cuenta(
	numero_cuenta, tipo_cuenta, saldo_inicial, estado, clienteid)
	VALUES ('585545', 'C', 1000, 'T', 1);


INSERT INTO public.movimiento(
	fecha, tipo_movimiento, valor, saldo, estado, numero_cuenta)
	VALUES ('2024-09-14', 'R', -575, 1425, 'T', '478758');
INSERT INTO public.movimiento(
	fecha, tipo_movimiento, valor, saldo, estado, numero_cuenta)
	VALUES ('2024-09-14', 'D', 600, 700, 'T', '225487');
INSERT INTO public.movimiento(
	fecha, tipo_movimiento, valor, saldo, estado, numero_cuenta)
	VALUES ('2024-09-14', 'D', 150, 150, 'T', '495878');
INSERT INTO public.movimiento(
	fecha, tipo_movimiento, valor, saldo, estado, numero_cuenta)
	VALUES ('2024-09-14', 'R', -540, 0, 'T', '496825');

