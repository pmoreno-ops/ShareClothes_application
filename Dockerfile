FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar Maven Wrapper
COPY mvnw .
COPY .mvn .mvn

# Copiar proyecto
COPY pom.xml .
COPY src ./src

# Dar permisos y construir
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Exponer puerto (opcional)
EXPOSE 8080

# Ejecutar app
CMD ["java", "-jar", "target/ShareClothes-0.0.1-SNAPSHOT.jar"]
