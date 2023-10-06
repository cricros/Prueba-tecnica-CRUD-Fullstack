
# Prueba Tecnica (CRUD con Login)

Se solicitó desarrollar un CRUD con Login, con motivo del proceso para la vacante de full-stack. 

El CRUD fue solicitado con las operaciones: C(reate), R(ead), U(pdate) y D(elete). Además de contar con un frontend, el cual consume los REST endpoint para que éstos puedan desplegarse de manera satisfactoria. 






## Authors

- [@cricos](https://github.com/cricros)
## Requerimiento técnico

Realizar sistema con los siguientes requerimientos:

-	Java
-	Spring o Spring Boot (MVC)
-	Hibernate / JPA (opcional)
-	Gestor BD (opcional)

Realizar DB con tabla de usuarios con los siguientes campos:

-	id_user
-	name
-	last_name
-	username
-	password
-	creation_date

Operaciones Web Services:

-	Servicio REST para registrar usuario
-	Servicio REST para actualizar usuario
-	Servicio REST para eliminar usuario
-	Servicio REST que solicite usuario y contraseña, retomará la información del mismo
-	Servicio REST que devuelva todos los usuarios registrados



## Clonar repositorio en ambiente local

Para clonar el repositorio, solo es necesario agregar el siguiente comando a una consola de bash y colocarte en la carpeta que mas te sea comoda: 

```bash
  git clone aqui va el url del repo HACER EL REPO
```


    





## Ejecutar desde un localhost

Una vez que tengas descargado el repositorio con el comando anteriormente proporcionado, dirigete a tu IDE y abrelo desde el mismo IDE 

![Logo](https://learn.microsoft.com/es-es/azure/spring-apps/media/quickstart/intellij-new-project.png)

Una vez que se encuentre cargado en el mismo IDE, ahora vamos a requrir utilizar la barra derecha de maven (esta nos ayudara a descargar las dependencies declaras en el POM)

![Logo](https://rdf4j.org/documentation/tutorials/images/eclipse-maven-deps.png)

De igual manera, éstas siempre se descargaran de manera automatica la primera vez que corramos el proyecto

Dentro de la misma columna del lado derecho, podremos apreciar una barra que se despliega, dento de ella podremos encontrar una opción para poder desplegar el proyecto de manera mas rapida y sencilla. Para poderla localizar solo debemos seguir la siguiente ruta: 

- Maven -> Plugins -> spring-boot -> spring-boot: run

O si así lo queremos podemos ejecutarlo con la misma cmd, solo tecleando: 

```bash
  mvnw spring-boot:run
```

## Librerias y frameworks 

Dado el requerimiento descrito en el punto anterior, se enlistaran y describiran de manera breve algunas de las librerías y/o frameworks que se utilizaron para llegar acabo el desarrollo. 

- Spring Boot: se utiliza comúnmente para crear aplicaciones backend robustas y escalables utilizando el lenguaje de programación Java.
- Maven:  herramienta de automatización y gestión de dependencias que simplifica el proceso de compilación, prueba, empaquetado y distribución de aplicaciones.
  
- Hibernate: framework de mapeo objeto-relacional (ORM) para Java que simplifica y agiliza la interacción entre aplicaciones Java y bases de datos relacionales.
    
- MySQL Connector: conjunto de controladores (drivers) y bibliotecas que permiten a las aplicaciones y programas en diferentes lenguajes de programación comunicarse y conectarse a una base de datos MySQL
- Lombok: biblioteca de Java que ayuda a reducir la verbosidad y repetición de código en proyectos de desarrollo. Facilita la creación de clases Java al generar de manera automática métodos y constructores comunes, como getters, setters, constructores, hashCode, equals y toString, basándose en anotaciones.
- Argon2: algoritmo de hash de contraseñas diseñado específicamente para aumentar la seguridad en el almacenamiento y manejo de contraseñas en aplicaciones y sistemas.
- JWT:  se utiliza para tokenizar información que se puede verificar y confiar. Un JWT consta de tres partes separadas por puntos: la cabecera (header), la carga útil (payload) y la firma (signature).



## Variables para conexion a MySQL y JWT

Para poder realizar de manera satisfactoria la conexión entre la BD y el proyecto, es necesario contar con MySQL instalado en nuestro equipo, y posteriormente colocar las siguietes variables en nuestro archivo "application.properties"

`spring.datasource.url` = jdbc:mysql://host/dbName?useSSL=false

`spring.datasource.dbnam` = dbName

`spring.datasource.username` = dbUser
  
`spring.datasource.password` = bdPassword

`spring.datasource.driver-class-name` = com.mysql.cj.jdbc.Driver

Una vez que se terminaron de colocar las variables para poder realizar la conexión a MySQL, ahora debemos configurar las variables de JWT ya que lo utilizarmoes para poder encriptar nuestras contraseñas y poder realizar un login con vigencia de tiempo. 


`security.jwt.secret` = aquí debemos colocar nuestra palabra secreta 

`security.jwt.issuer` = Main

`security.jwt.ttlMillis` = tiempoenMilisegs
  


## API Reference

Cada uno de los siguientes endpoint son consumidos por el frontend, además de solicitar estar con un token generado y logueado. De caso contrario, no se podrán consumir la mayoría de éstos

#### Trae todos los registros de la base de datos

```http
  GET /api/getUsers
```

| Header | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `tokenJTW` | `string` | JTW token con vigencia|

#### Nuevo usuario

```http
  POST /api/newUser
```
Permite guardar nuevos usuarios en la contraseña. Las constraseñas se almancenan cifradas, gracias a la libería Argos2

#### Elimina usuario

```http
  DELETE /api/newUser
```
Permite eliminar usuarios. Se requiere el ID a eliminar, además de encontrase logueado. 

#### Login usuario

```http
  DELETE /api/loginUser
```
Permite iniciar sesión de usuarios registrados. Se requiere el conocer la contraseña, ya que sin ella no se puede acceser de los demás servicios anteriormente descritos. 



## Mejoras

Se agregaron las siguientes mejoras:  

### Registro. Se agregaron las siguientes validaciones:
- Ya no se permiten realizar registros con campos vacios. 
- No se permite crear un usuario con espacios en blanco. 
- No se permite realizar un registro de un nuevo usuario si el registro ya existe. 

### Login. Se agregaron las siguientes validaciones:
- No se puede realizar el login si el usuario no existe, o no está registrado. 
- Ya no se permiten campos en blanco.

### Update
- Permite actualizar el usuario. 
- Se agrego la validación evitar que se puedan mandar campos vacios. 
- La nueva contraseña se hashea. 

Ademas, se agrearon dos nuevos endpoints para poder realizar la actualizacion de la informacion del usuario

```http
  GET api/getUser/{id}
```

Nos permite obtener la información del usuario a validar, teniendo como condición traer unicamente la información del id proporcionado. 


```http
  POST api/updateUser/{id}
```
Nos permite realizar la actualización de la información y posteriormente guardarla en la BD. 

El funcionamiento del update es el siguiente: 

- Se obtiene primeramente el id del usuario, ya que con este valor haremos la consulta a la bd. 
- Se validan que los campos no estén vacios al momento de realizar la actualizacion. 
- Si 
