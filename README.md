# pruebaNeoris
Prueba técnica Neoris Back End JAVA

INSTRUCCIONES DE INSTALACION Y EJECUCIÓN: 

1. Clonar el repositorio publico en la siguiente URL: https://github.com/cristianmanzano1989/pruebaNeoris.git
2. Ejecutar el Script BaseDatos.sql
3. Ejecutar mvn clean install al proyecto /pruebaTecnica el cual genera el artefacto : pruebaTecnica-0.0.1-SNAPSHOT.jar
4. DESPLIEGUE EN DOCKER:

# Usa una imagen base de Java
FROM openjdk:17-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de la aplicación al contenedor
COPY target/pruebaTecnica-0.0.1-SNAPSHOT.jar .

# Expone el puerto en el que se ejecuta la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación cuando el contenedor se inicia
CMD ["java", "-jar", "pruebaTecnica-0.0.1-SNAPSHOT.jar"]

#Esto construirá una imagen de Docker con el nombre "pruebaTecnica" 
docker build -t pruebaTecnica .

#Después de construir la imagen, puedes ejecutar un contenedor basado en esa imagen usando el siguiente comando:

docker run -p 8080:8080 pruebaTecnica

Esto ejecutará un contenedor a partir de la imagen "pruebaTecnica" y mapeará el puerto 8080 del contenedor al puerto 8080 del host.
Ahora deberías poder acceder a tu aplicación Spring Boot a través de http://localhost:8080 en tu máquina local.
