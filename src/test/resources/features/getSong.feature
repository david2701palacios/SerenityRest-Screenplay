#Autor: Oscar David Palacios Gonzalez

@getSongs
Feature: Consultar canciones
  Como usuario de la aplicación
  Quiero consultar canciones por id e idioma
  Para poder obtener la información de la canción

  @smokeTest @HappyPath @getSong
  Scenario Outline: Obtener una canción por id e idioma
    When la aplicación consulta el endpoint de obtener una canción
      | idCancion | idioma |
      | <idCancion> | <idioma> |
    Then el sistema responde con la información de la canción encontrada
    Examples:
      | idCancion | idioma |
        | 1217912247 | en-US |

  @smokeTest @HappyPath @getSongDeleted
  Scenario Outline: Obtener una canción por id e idioma eliminada
    When la aplicación consulta el endpoint de obtener una canción
      | idCancion | idioma |
      | <idCancion> | <idioma> |
    Then el sistema responde con un mensaje de error controlado
    Examples:
      | idCancion | idioma |
      | 12179122437 | en-US |

  @smokeTest @HappyPath @getSongWithoutToken
  Scenario: Obtener una canción sin el token de autenticación
    When la aplicación consulta el endpoint de obtener una canción sin token
    Then el sistema responde con el error de autenticación
