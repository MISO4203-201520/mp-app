#Tabla de contenidos
-  [Introducción](#introducción)
-  [API](#api-de-la-aplicación-appmarketplace)
  - [Entidad App](#entidad-app)
  - [Entidad CartItem](#entidad-cartitem)
  - [Entidad Client](#entidad-client)
  - [Entidad Developer](#entidad-developer)

#API Rest
##Introducción
La comunicación entre cliente y servidor se realiza intercambiando objetos JSON. Para cada entidad se hace un mapeo a JSON, donde cada uno de sus atributos se transforma en una propiedad de un objeto JSON. Todos los servicios se generan en la URL /AppMarketPlace.web/webresources/. Por defecto, todas las entidades tienen un atributo `id`, con el cual se identifica cada registro:

```javascript
{
    id: '',
    attribute_1: '',
    attribute_2: '',
    ...
    attribute_n: ''
}
```

###CRUD Básico
Para los servicios de CRUD Básico, Cuando se transmite información sobre un registro específico, se realiza enviando un objeto con la estructura mencionada en la sección anterior.
La única excepción se presenta al solicitar al servidor una lista de los registros en la base de datos, que incluye información adicional para manejar paginación de lado del servidor.

La respuesta del servidor al solicitar una colección presenta el siguiente formato:

```javascript
{
    totalRecords: 0, //cantidad de registros en la base de datos
    records: [] //collección con los datos solicitados. cada objeto tiene la estructura de la entidad.
}
```

##API de la aplicación AppMarketPlace
###Entidad App
####CRUD Básico
En la siguiente tabla se detalla los servicios REST generados para la entidad App, la estructura del objeto que intercambian y sus respectivas funciones.

#####Estructura de objeto App
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    description: '' /*Tipo String*/,
    version: '' /*Tipo String*/,
    picture: '' /*Tipo String*/,
    price: '' /*Tipo Integer*/,
    size: '' /*Tipo Integer*/
}
```
#####Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/apps|Obtener todos los objetos JSON de App (RETRIEVE)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de objetos JSON App y el total de registros en la base de datos en el header X-Total-Count
**GET**|/apps/:id|Obtener los atributos de una instancia de App en formato JSON(RETRIEVE)|**@PathParam id**: Identificador del registro||Objeto JSON con detalle de la instancia de App
**POST**|/apps|Crear una nueva instancia de la entidad App (CREATE)||Objeto JSON de App a crear|Objeto JSON de App creado
**PUT**|/apps/:id|Actualiza una instancia de la entidad App (UPDATE)|**@PathParam id**: Identificador del registro|Objeto JSON de App|Objeto JSON de App actualizado
**DELETE**|/apps/:id|Borra instancia de App en el servidor (DELETE)|<strong>@PathParam id</strong>: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

###Entidad CartItem
####CRUD Básico
En la siguiente tabla se detalla los servicios REST generados para la entidad CartItem, la estructura del objeto que intercambian y sus respectivas funciones.

#####Estructura de objeto CartItem
```javascript
{
    quantity: '' /*Tipo Integer*/,
    name: '' /*Tipo String*/,
    id: '' /*Tipo Long*/,
    app: '' /*Objeto que representa instancia de App*/
}
```
#####Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/cartItems|Obtener todos los objetos JSON de CartItem (RETRIEVE)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de objetos JSON CartItem y el total de registros en la base de datos en el header X-Total-Count
**GET**|/cartItems/:id|Obtener los atributos de una instancia de CartItem en formato JSON(RETRIEVE)|**@PathParam id**: Identificador del registro||Objeto JSON con detalle de la instancia de CartItem
**POST**|/cartItems|Crear una nueva instancia de la entidad CartItem (CREATE)||Objeto JSON de CartItem a crear|Objeto JSON de CartItem creado
**PUT**|/cartItems/:id|Actualiza una instancia de la entidad CartItem (UPDATE)|**@PathParam id**: Identificador del registro|Objeto JSON de CartItem|Objeto JSON de CartItem actualizado
**DELETE**|/cartItems/:id|Borra instancia de CartItem en el servidor (DELETE)|<strong>@PathParam id</strong>: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

###Entidad Client
####CRUD Básico
En la siguiente tabla se detalla los servicios REST generados para la entidad Client, la estructura del objeto que intercambian y sus respectivas funciones.

#####Estructura de objeto Client
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    userId: '' /*Tipo String*/
}
```
#####Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/clients|Obtener todos los objetos JSON de Client (RETRIEVE)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de objetos JSON Client y el total de registros en la base de datos en el header X-Total-Count
**GET**|/clients/:id|Obtener los atributos de una instancia de Client en formato JSON(RETRIEVE)|**@PathParam id**: Identificador del registro||Objeto JSON con detalle de la instancia de Client
**POST**|/clients|Crear una nueva instancia de la entidad Client (CREATE)||Objeto JSON de Client a crear|Objeto JSON de Client creado
**PUT**|/clients/:id|Actualiza una instancia de la entidad Client (UPDATE)|**@PathParam id**: Identificador del registro|Objeto JSON de Client|Objeto JSON de Client actualizado
**DELETE**|/clients/:id|Borra instancia de Client en el servidor (DELETE)|<strong>@PathParam id</strong>: Identificador del registro||

####Maestros Detalle de Client
#####Servicios
A diferencia del API para CRUD Básico, el API de Maestro/Detalle no provee un servicio para listar los registros de la entidad maestra. Los servicios generados para el componente Maestro/Detalle de Client son los siguientes:

######Relaciones Composite

Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|clients/:id/cartItems|Obtener Objetos JSON de cartItems(CartItem) dependientes de Client|**@PathParam id**: `id` de instancia de Client||Colección de objetos JSON de cartItems(CartItem)
**POST**|clients/:id/cartItems|Creación de instancias de cartItems(CartItem) dependientes de Client|**@PathParam id**: `id` de instancia de Client|Colección de objetos JSON de cartItems(CartItem) a crear|Colección de objetos JSON de cartItems(CartItem) creados con sus respectivos ID
**PUT**|clients/:id/cartItems|Actualización de instancias de cartItems(CartItem) dependientes de Client|**@PathParam id**: `id` de instancia de Client|Colección de objetos JSON de cartItems(CartItem) a actualizar|Colección de objetos JSON de cartItems(CartItem) actualizados
**DELETE**|clients/:id/cartItems|Eliminación de instancias de cartItems(CartItem) dependientes de Client|**@PathParam id**: `id` de instancia de Client|Colección de atributo `id` de cartItems(CartItem) a eliminar|

[Volver arriba](#tabla-de-contenidos)

###Entidad Developer
####CRUD Básico
En la siguiente tabla se detalla los servicios REST generados para la entidad Developer, la estructura del objeto que intercambian y sus respectivas funciones.

#####Estructura de objeto Developer
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    userId: '' /*Tipo String*/
}
```
#####Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/developers|Obtener todos los objetos JSON de Developer (RETRIEVE)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de objetos JSON Developer y el total de registros en la base de datos en el header X-Total-Count
**GET**|/developers/:id|Obtener los atributos de una instancia de Developer en formato JSON(RETRIEVE)|**@PathParam id**: Identificador del registro||Objeto JSON con detalle de la instancia de Developer
**POST**|/developers|Crear una nueva instancia de la entidad Developer (CREATE)||Objeto JSON de Developer a crear|Objeto JSON de Developer creado
**PUT**|/developers/:id|Actualiza una instancia de la entidad Developer (UPDATE)|**@PathParam id**: Identificador del registro|Objeto JSON de Developer|Objeto JSON de Developer actualizado
**DELETE**|/developers/:id|Borra instancia de Developer en el servidor (DELETE)|<strong>@PathParam id</strong>: Identificador del registro||

####Maestros Detalle de Developer
#####Servicios
A diferencia del API para CRUD Básico, el API de Maestro/Detalle no provee un servicio para listar los registros de la entidad maestra. Los servicios generados para el componente Maestro/Detalle de Developer son los siguientes:

######Relaciones Composite

Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|developers/:id/apps|Obtener Objetos JSON de apps(App) dependientes de Developer|**@PathParam id**: `id` de instancia de Developer||Colección de objetos JSON de apps(App)
**POST**|developers/:id/apps|Creación de instancias de apps(App) dependientes de Developer|**@PathParam id**: `id` de instancia de Developer|Colección de objetos JSON de apps(App) a crear|Colección de objetos JSON de apps(App) creados con sus respectivos ID
**PUT**|developers/:id/apps|Actualización de instancias de apps(App) dependientes de Developer|**@PathParam id**: `id` de instancia de Developer|Colección de objetos JSON de apps(App) a actualizar|Colección de objetos JSON de apps(App) actualizados
**DELETE**|developers/:id/apps|Eliminación de instancias de apps(App) dependientes de Developer|**@PathParam id**: `id` de instancia de Developer|Colección de atributo `id` de apps(App) a eliminar|

[Volver arriba](#tabla-de-contenidos)

