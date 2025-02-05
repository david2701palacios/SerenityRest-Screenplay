#Autor: Oscar David Palacios Gonzalez

@detectSongs
Feature: Detectar canciones
  Como usuario de la aplicación
  Quiero detectar canciones por medio de metadatos
  Para poder obtener la información de la canción sin el nombre

  @smokeTest @HappyPath @detectSong
  Scenario: Obtener una canción por medio de metadatos
    When la aplicación consulta el endpoint de detectar una canción
    Then el sistema responde con la información de la canción encontrada según los metadatos
