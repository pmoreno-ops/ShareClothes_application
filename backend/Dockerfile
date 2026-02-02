# Usar JDK 17 oficial (Eclipse Temurin)
FROM eclipse-temurin:17-jdk-jammy

# Crear directorio de la app
WORKDIR /app

# Copiar Maven Wrapper y archivos de configuración
COPY mvnw .
COPY .mvn .mvn

# Copiar el proyecto completo
COPY pom.xml .
COPY src ./src

# Dar permisos y construir el JAR
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Renombrar el JAR para que siempre tenga el mismo nombre
RUN cp target/backend-0.0.1-SNAPSHOT.jar target/ShareClothes-0.0.1-SNAPSHOT.jar

# Exponer puerto 8080
EXPOSE 8080

# Comando para ejecutar la app
CMD ["java", "-jar", "target/ShareClothes-0.0.1-SNAPSHOT.jar"]
