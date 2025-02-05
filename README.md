Automatización API Rapid
![image](https://github.com/user-attachments/assets/9ebc2884-ab1f-4937-8d53-cda06e1aeedf)

El framework utilizado para la automatización de las pruebas es Serenity BDD utilizando el patron de diseño Screenplay  
Link de referencia: https://serenity-bdd.github.io/theserenitybook/latest/index.html

Requisito para ejecutar:
+ **Java 21**
+ **gradle 8**

Ejecución de pruebas
---  

Para ejecutar el proyecto completo utilizar el comando:
```
 gradle test   
```
Para ejecutar un solo tag según las features
```
 gradle test -DTags="@detectSong"
```  
Para ejecutar un runner específico :

Para Generar la evidencia de pruebas:
```  gradle aggregate  ```
Aunque el proyecto por si solo genera la evidencia automaticamente una vez finalice la ejecución.
