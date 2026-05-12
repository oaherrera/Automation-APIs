# language: es
@usuarios
Característica: Gestión de Usuarios
  Como usuario de la API
  Quiero obtener información de usuarios
  Para verificar que los datos se devuelven correctamente

  @usuarios @obtener-usuario
  Escenario: Obtener información de un usuario
    Dado que el usuario desea obtener información de un usuario específico
    Cuando solicito la información del usuario con id 1
    Entonces la respuesta del servidor debe tener estado OK
    Y la respuesta debe contener los datos del usuario

  @usuarios @validar-email
  Escenario: Validar que el email existe en la respuesta
    Dado que el usuario desea validar información del usuario
    Cuando solicito la información del usuario con id 1
    Entonces la respuesta debe incluir el email del usuario

  @usuarios @eliminar-usuario
  Escenario: Eliminar un usuario
    Dado que el usuario desea eliminar un usuario
    Cuando elimino el usuario con id 1
    Entonces la respuesta del servidor debe tener estado OK

  @usuarios @contrato
  Escenario: Validar contrato de usuario por id
    Dado que el usuario desea verificar el contrato de un usuario
    Cuando solicito la información del usuario con id 1
    Entonces el contrato de la respuesta debe coincidir con el esquema de usuario

  @usuarios @actualizar-usuario
  Escenario: Actualizar un usuario
    Dado que el usuario desea actualizar un usuario
    Cuando actualizo el usuario con id 1 con los siguientes datos:
      | name            | username   | email              |
      | Juan Actualizado | juan123    | juan@nuevo.com     |
    Entonces la respuesta del servidor debe tener estado OK

  @usuarios @contrato
  Escenario: Validar contrato de usuario actualizado
    Dado que el usuario desea verificar el contrato de un usuario actualizado
    Cuando actualizo el usuario con id 1 con los siguientes datos:
      | name            | username   | email              |
      | Juan Actualizado | juan123    | juan@nuevo.com     |
    Entonces el contrato de la respuesta debe coincidir con el esquema de usuario actualizado
