1. Proyecto Spring Boot compilado con maven
2. Scrip base de datos postgres
CREATE DATABASE minegocio;
CREATE TABLE MNGTCLIENTES(
	ID_CLIENTE SERIAL NOT NULL,
	TIPO_IDENTIFICACION VARCHAR(3) NOT NULL,
	NUMERO_IDENTIFICACION VARCHAR(13) NOT NULL,
	NOMBRE VARCHAR(64) NOT NULL,
	CORREO VARCHAR(64),
	CELULAR VARCHAR(13),
	PRIMARY KEY(ID_CLIENTE)
);

CREATE TABLE MNGTDIRECCIONES(
	ID_DIRECCION SERIAL NOT NULL,
	ID_CLIENTE SERIAL NOT NULL,
	PROVINCIA VARCHAR(32) NOT NULL,
	CIUDAD VARCHAR(32) NOT NULL,
	DIRECCION VARCHAR(256) NOT NULL,
	ESDIRECCIONMATRIZ BOOLEAN NOT NULL,
	PRIMARY KEY(ID_DIRECCION)
);
ALTER TABLE MNGTDIRECCIONES ADD CONSTRAINT IDCLIENTEFKMNGTCLIENTESPKDIRECCIONES FOREIGN KEY(ID_CLIENTE) REFERENCES MNGTCLIENTES(ID_CLIENTE);

3. LISTA DE FUNCIONALIDADAS Y TIPOS DE SERVICIOS
3.1 FUNCIONALIDAD 1 (Funcionalidad para buscar y obtener un listado de clientes)
	- URL: http://localhost:8080/miNegocioWs/api/v1/mng/admin/clientes/listaClientes
	- POST SERVICE
	- JSON entrada: Se puede enviar vacio para consultar todos los clientes o buscar por cualquiera de los dos parametros
		{
			"numeroIdentificacion" : "",
			"nombres": ""
		}
	- JSON Salida:
		{
			"code": 200,
			"message": "La busqueda se realizo correctamente",
			"errors": null,
			"data": [
				{
					"idCliente": 1,
					"tipoIdentificacion": "CED",
					"numeroIdentificacion": "1002966404",
					"nombres": "HENRY ESTEBAN GUDIÑO BARAHONA",
					"correo": "esteban_gb666@hotmail.com",
					"telefono": "0997122133",
					"provincia": "IMBABURA",
					"ciudad": "IBARRA",
					"direccion": "BOLIVAR Y SUCRE"
				},
				{
					"idCliente": 2,
					"tipoIdentificacion": "CED",
					"numeroIdentificacion": "1002937777",
					"nombres": "RICARDO ALEX ANDRADE MARIN",
					"correo": "ricardo@hotmail.com",
					"telefono": "0998542177",
					"provincia": "PICHINCHA",
					"ciudad": "QUITO",
					"direccion": "PORTUGAL Y 6 DE DICIEMBRE"
				}
			]
		}

3.2 FUNCIONALIDAD 2 (Funcionalidad para crear un nuevo cliente con la dirección matriz)
	- URL: http://localhost:8080/miNegocioWs/api/v1/mng/admin/clientes/crearCliente
	- POST SERVICE
	- JSON entrada:
		{
			"tipoIdentificacion": "CED",
			"numeroIdentificacion": "1002961454",
			"nombres": "ROSA ANDREA REA LOZADA",
			"correo": "andrearea87@hotmail.com",
			"telefono": "0989746332",
			"provincia": "IMBABURA",
			"ciudad": "IBARRA",
			"direccion": "HUERTOS FAMILIARES"
		}
	- JSON Salida:
		{
			"code": 200,
			"message": "EL cliente se creo correctamente",
			"errors": null,
			"data": {
				"tipoIdentificacion": "CED",
				"numeroIdentificacion": "1002961454",
				"nombres": "ROSA ANDREA REA LOZADA",
				"correo": "andrearea87@hotmail.com",
				"telefono": "0989746332",
				"provincia": "IMBABURA",
				"ciudad": "IBARRA",
				"direccion": "HUERTOS FAMILIARES"
			}
		}
		
3.3 FUNCIONALIDAD 3 (Funcionalidad para editar los datos del cliente)
	- URL: http://localhost:8080/miNegocioWs/api/v1/mng/admin/clientes/actualizarCliente
	- POST SERVICE
	- JSON entrada:
		{
			"idCliente": 3,
			"tipoIdentificacion": "CED",
			"numeroIdentificacion": "1002961454",
			"nombres": "ROSA ANDREA ANDRADE LOPEZ",
			"correo": "andrearea87@hotmail.com",
			"telefono": "0989746332"
		}
	- JSON Salida:
		{
			"code": 200,
			"message": "EL cliente se actualizo correctamente",
			"errors": null,
			"data": {
				"idCliente": 3,
				"tipoIdentificacion": "CED",
				"numeroIdentificacion": "1002961454",
				"nombres": "ROSA ANDREA ANDRADE LOPEZ",
				"correo": "andrearea87@hotmail.com",
				"telefono": "0989746332"
			}
		}
		
3.4 FUNCIONALIDAD 4 (Funcionalidad para eliminar un cliente)
	- URL: http://localhost:8080/miNegocioWs/api/v1/mng/admin/clientes/eliminarCliente
	- POST SERVICE
	- JSON entrada:
		{
			"idCliente": 4
		}
	- JSON Salida:
		{
			"code": 200,
			"message": "El cliente se elimino correctamente",
			"errors": null,
			"data": {
				"idCliente": 4
			}
		}
		
3.5 FUNCIONALIDAD 5 (Funcionalidad para registrar una nueva dirección por cliente)
	- URL: http://localhost:8080/miNegocioWs/api/v1/mng/admin/direcciones/guardarDireccion
	- POST SERVICE
	- JSON entrada:
		{
			"idCliente": 1,
			"provincia": "PICHINCHA",
			"ciudad": "CAYAMBE",
			"direccion": "ANDRES BENAVIDES Y ANTONIO JOSE DE SUCRE",
			"esDireccionMatriz" : false
		}
	- JSON Salida con direccion matriz en false:
		{
			"code": 200,
			"message": "La direccion se creo correctamente",
			"errors": null,
			"data": {
				"idDireccion": 6,
				"idCliente": 1,
				"provincia": "PICHINCHA",
				"ciudad": "CAYAMBE",
				"direccion": "ANDRES BENAVIDES Y ANTONIO JOSE DE SUCRE",
				"esDireccionMatriz": false
			}
		}
	- JSON Salida con direccion matriz en true, se valida si ya existe una direccion matriz:
		{
			"code": 200,
			"message": "No se puede crear una direccion matriz porque ya existe una para el cliente con id 1.",
			"errors": null,
			"data": null
		}

3.6 FUNCIONALIDAD 6 (Funcionalidad para listar las direcciones adicionales del cliente)
	- URL: http://localhost:8080/miNegocioWs/api/v1/mng/admin/direcciones/listaDireccionesCliente
	- POST SERVICE
	- JSON entrada:
		{
			"codigoCliente": 1
		}
	- JSON Salida:
		{
			"code": 200,
			"message": "La busqueda se realizo correctamente",
			"errors": null,
			"data": [
				{
					"idCliente": 1,
					"provincia": "IMBABURA",
					"ciudad": "IBARRA",
					"direccion": "BOLIVAR Y SUCRE",
					"esDireccionMatriz": true
				},
				{
					"idCliente": 1,
					"provincia": "CARCHI",
					"ciudad": "TULCAN",
					"direccion": "MOTUFAR Y ELOY ALFARO",
					"esDireccionMatriz": false
				},
				{
					"idCliente": 1,
					"provincia": "PICHINCHA",
					"ciudad": "CAYAMBE",
					"direccion": "ANDRES BENAVIDES Y ANTONIO JOSE DE SUCRE",
					"esDireccionMatriz": false
				}
			]
		}