# language: es
@publicaciones
Característica: Gestión de Publicaciones
  Como usuario de la API
  Quiero obtener, crear y validar publicaciones
  Para verificar que la API de JSONPlaceholder funciona correctamente

  @publicaciones @obtener-listado
  Escenario: Obtener todas las publicaciones de un usuario
    Dado que el usuario desea obtener publicaciones
    Cuando solicito las publicaciones del usuario con id 1
    Entonces la respuesta del servidor debe tener estado OK

  @publicaciones @obtener-por-id
  Escenario: Obtener una publicación específica por su id
    Dado que el usuario desea obtener una publicación específica
    Cuando solicito la publicación con id 1
    Entonces la respuesta debe contener la publicación con id 1

  @publicaciones @crear
  Escenario: Crear una nueva publicación
    Dado que el usuario desea crear una nueva publicación
    Cuando creo una publicación con los siguientes datos:
      | userId | title           | body        |
      | 1      | Mi primer post  | Contenido... |
    Entonces la respuesta del servidor debe tener estado CREATED

  @publicaciones @validar-estructura
  Escenario: Validar estructura de respuesta
    Dado que el usuario desea validar la estructura
    Cuando solicito la publicación con id 1
    Entonces la respuesta debe tener los campos: id, userId, title, body

  @publicaciones @contrato
  Escenario: Validar contrato de listado de publicaciones
    Dado que el usuario desea verificar el contrato de publicaciones
    Cuando solicito las publicaciones del usuario con id 1
    Entonces el contrato de la respuesta debe coincidir con el esquema de listado de publicaciones

  @publicaciones @contrato
  Escenario: Validar contrato de publicación por id
    Dado que el usuario desea verificar el contrato de una publicación
    Cuando solicito la publicación con id 1
    Entonces el contrato de la respuesta debe coincidir con el esquema de publicación

  @publicaciones @contrato
  Escenario: Validar contrato de publicación creada
    Dado que el usuario desea verificar el contrato al crear una publicación
    Cuando creo una publicación con los siguientes datos:
      | userId | title           | body         |
      | 1      | Mi primer post  | Contenido... |
    Entonces el contrato de la respuesta debe coincidir con el esquema de publicación
