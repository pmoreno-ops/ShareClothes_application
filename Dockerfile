# Usar OpenJDK 17
FROM openjdk:17-jdk-slim

# Directorio de la app
WORKDIR /app

# Copiar pom.xml y código
COPY pom.xml .
COPY src ./src

# Construir la app
RUN ./mvnw clean package -DskipTests

# Ejecutar la app
CMD ["java", "-jar", "target/ShareClothes-0.0.1-SNAPSHOT.jar"]
