# Tweets Sin Bulos - Trabajo de Fin de Grado 🚀
## Helena Sánchez Jiménez. Universidad de Alicante.
## Contacto: helenasj09@gmail.com
## Info importante: En virtud de esta declaración afirmo que este trabajo es inédito y de mi autoría, por lo que me declaro propietaria del contenido, veracidad y alcance de este proyecto.
![alt text](https://github.com/xHelenA7X/tfg/blob/master/src/main/webapp/img/pinocho-logo.png)

## ¿Qué es Tweets Sin Bulos?
TweetsSinBulos se trata de una plataforma de verificación de hechos automatizada. Se trata de un
Trabajo de Fin de Grado realizado por Helena Sánchez Jiménez, estudiante en la Escuela Politécnica Superior, del grado Ingeniería Informática en la Universidad de Alicante. El objetivo de esta plataforma es el desmantelamiento de bulos existentes en la red a través de tweets, procedentes de la plataforma y red social 'Twitter'. De esta forma, cualquier tweet pueda ser analizado y contrastado mediante Procesamiento del Lenguaje Natural, verificando si dicha afirmación se trata de un bulo o no.

Este proyecto tiene una serie de dependencias 🔧:
* [Freeling versión 4.0](https://talp-upc.gitbook.io/freeling-4-0-user-manual/)
* [Googler versión 3.8](https://github.com/jarun/googler/releases/tag/v3.8)

Funcionamiento bajo servidor web [Jetty 9.4.15.v20190215](https://github.com/eclipse/jetty.project/releases/tag/jetty-9.4.15.v20190215)
La configuración java que se utiliza es la siguiente:
* java version "1.8.0_201"
* Java(TM) SE Runtime Environment (build 1.8.0_201-b09)
* Java HotSpot(TM) 64-Bit Server VM (build 25.201-b09, mixed mode)

## Antes de usar
El siguiente proyecto se proporciona como prueba del proyecto, de ninguna forma está permitido el uso del mismo con fines benéficos o lucrativos. Todos los derechos reservados.

## ¿Cómo puedo probar Tweets Sin Bulos?
La aplicación web se trata de un proyecto Maven, para generar el .war asociado se requiere de [Apache Maven 3.3.9](https://maven.apache.org/docs/3.3.9/release-notes.html) una vez instalado desde la terminal y raíz del proyecto ejecutamos:
```
mvn package
```

Nota: El servidor funciona bajo el sistema operativo Linux y distribución Ubuntu, se recomienda seguir la misma metodologíá.
Una vez hemos instalado freeling en nuestra máquina, lo ponemos desde la terminal en modo servidor y el puerto 5000 en escucha, con el siguiente comando:
```
analyze -f es.cfg --outlv parsed --output xml --server --port 5000
```

Para la base de datos requerimos de MySQL Server (cualquier versión sería válida). En mi caso utilizo en la configuración un usuario llamado 'helena' con contraseña '1234' y privilegios de root. La base de datos ha de llamarse factchecking
Podéis importar la base de datos, os dejo el siguiente enlace con los distintos archivos sql para importar las tablas y su contenido, además del corpus necesario:

* [Base de datos y corpus](https://drive.google.com/file/d/1dcISfxWrE8eGFOp1JITH889Go69Jiqma/view?usp=sharing)


## ¿Cómo despliego el servidor? - Sin SSL
Una vez maven nos ha generado el .war asociado en /raiz_proyecto/target movemos este archivo  al directorio webapps del servidor jetty. Nos posicionamos desde la terminal en la raíz del servidor jetty y ejecutamos:
```
java -jar start.jar
```
Abrimos un navegador y ejecutamos localhost:8080

El despliegue con SSL lo realizo con mi propio certificado desde mi máquina, desde aquí unicamente suministro el proyecto.

## Información importante
La estructura de directorios que ha de crearse es la siguiente:
Crear directorio /etc/tweets (aquí irán los archivos .xml y .txt generados por freeling).
Crear directorio /etc/tweets/corpus e introducir CorpusFakeNews2.xls (en este directorio irá el corpus).
