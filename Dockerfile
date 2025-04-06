# ================================================
# Etapa 1: Construcción de la aplicación con Maven
# ================================================
FROM maven:3.8.7-eclipse-temurin-17 AS build

WORKDIR /app# ================================================
            # Etapa 1: Construcción de la aplicación con Maven
            # ================================================
            FROM maven:3.8.7-eclipse-temurin-17 AS build

            WORKDIR /app


# Copiamos los archivos de configuración (pom.xml) y el código fuente
COPY pom.xml .
COPY src ./src

# Ejecutamos el empaquetado de la aplicación con Maven
RUN mvn clean package -DskipTests

# ============================================
# Etapa 2: Imagen final, solo con JRE y el jar
# ============================================
FROM eclipse-temurin:17-jre

# Definimos el directorio de trabajo dentro de la imagen
WORKDIR /app

# Copiamos el jar resultante de la etapa "build"
# Ajusta el nombre del jar si difiere de "todoapi-0.0.1-SNAPSHOT.jar"
COPY --from=build /app/target/todoapi-0.0.1-SNAPSHOT.jar ./todoapi.jar

# Puerto que expone la aplicación (opcional, para documentación)
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "todoapi.jar"]
