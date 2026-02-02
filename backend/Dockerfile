FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copiar Maven Wrapper
COPY mvnw .
COPY .mvn .mvn

# Copiar proyecto
COPY pom.xml .
COPY src ./src

# Permisos y build
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/ShareClothes-0.0.1-SNAPSHOT.jar"]
